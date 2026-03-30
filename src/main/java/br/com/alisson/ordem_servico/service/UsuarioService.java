package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.dto.UsuarioDTO;
import br.com.alisson.ordem_servico.model.PerfilUsuario;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.model.Usuario;
import br.com.alisson.ordem_servico.repository.UsuarioRepository;
import br.com.alisson.ordem_servico.repository.RepresentanteRepository;
import br.com.alisson.ordem_servico.exeption.NegocioException;
import br.com.alisson.ordem_servico.exeption.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final RepresentanteRepository representanteRepository;

    public UsuarioService(UsuarioRepository repository, RepresentanteRepository representanteRepository) {
        this.repository = repository;
        this.representanteRepository = representanteRepository;
    }

    public Usuario criarUsuario(UsuarioDTO dto, Usuario executor) {
        
        long totalUsuarios = repository.count();

        // 1. LÓGICA DE BOOTSTRAP (Primeiro Usuário)
        if (totalUsuarios == 0) {
            Usuario primeiroAdmin = new Usuario();
            primeiroAdmin.setNome(dto.getNome());
            primeiroAdmin.setEmail(dto.getEmail());
            primeiroAdmin.setSenha(dto.getSenha());
            primeiroAdmin.setPerfil(PerfilUsuario.ADMIN); // Força ser ADMIN
            primeiroAdmin.setAtivo(true);
            // O primeiro ADMIN pode ficar sem representante (Super User) ou você vincula ao ID 1
            return repository.save(primeiroAdmin);
        }

        // 2. VALIDAÇÃO DE SEGURANÇA (Para todos os outros usuários)
        if (executor == null) {
            throw new NegocioException("É necessário um usuário autenticado para realizar esta operação.");
        }

        if (executor.getPerfil() == PerfilUsuario.ANALISTA) {
            throw new NegocioException("Analistas não têm permissão para criar novos usuários.");
        }

        // 3. PREPARAÇÃO DO NOVO USUÁRIO
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(dto.getSenha());
        novoUsuario.setPerfil(dto.getPerfil());
        novoUsuario.setAtivo(true);

        // 4. REGRAS DE HIERARQUIA
        if (executor.getPerfil() == PerfilUsuario.ADMIN) {
            if (dto.getRepresentanteId() == null) {
                throw new NegocioException("Para um administrador criar um usuário, deve informar o ID do Representante.");
            }
            Representante rep = representanteRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Representante não encontrado"));
            novoUsuario.setRepresentante(rep);

        } else if (executor.getPerfil() == PerfilUsuario.GERENTE) {
            // Gerente só cria para a própria casa e nunca cria outro ADMIN
            novoUsuario.setRepresentante(executor.getRepresentante());
            
            if (dto.getPerfil() == PerfilUsuario.ADMIN) {
                throw new NegocioException("Um gerente não pode criar um usuário com perfil Administrador.");
            }
        }

        return repository.save(novoUsuario);
    }

    // 1. Buscar usuário específico (Usado pelo Controller para validar quem está logado)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com ID " + id + " não encontrado."));
    }

    // 2. Listar Usuários (Com filtro de hierarquia para o Dashboard)
    public java.util.List<Usuario> listarUsuariosPorContexto(Usuario executor) {
        // ADMIN: Vê todos os usuários de todos os representantes do sistema
        if (executor.getPerfil() == PerfilUsuario.ADMIN) {
            return repository.findAll();
        }

        // GERENTE: Vê apenas os usuários (Analistas e outros Gerentes) da sua própria casa
        // ANALISTA: Por segurança, se um analista tentar listar, ele só vê a si mesmo ou nada (aqui filtramos pela casa)
        return repository.findByRepresentanteId(executor.getRepresentante().getId());
    }
    
    // 3. Buscar por Email  Login/Security
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com email " + email + " não encontrado."));
    }
}