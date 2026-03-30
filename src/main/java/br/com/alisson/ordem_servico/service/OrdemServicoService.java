package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.model.Cliente;
import br.com.alisson.ordem_servico.model.ItemOS;
import br.com.alisson.ordem_servico.model.OrdemServico;
import br.com.alisson.ordem_servico.model.PerfilUsuario;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.repository.OrdemServicoRepository;
import br.com.alisson.ordem_servico.model.StatusOrdem;
import br.com.alisson.ordem_servico.model.Usuario;
import br.com.alisson.ordem_servico.dto.OrdemServicoDTO;
import br.com.alisson.ordem_servico.dto.UsuarioDTO;
import br.com.alisson.ordem_servico.repository.ClienteRepository;
import br.com.alisson.ordem_servico.repository.RepresentanteRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import br.com.alisson.ordem_servico.exeption.*;
import java.util.List;


@Service
public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;  
    private final RepresentanteRepository representanteRepository;

    public OrdemServicoService(OrdemServicoRepository repository, ClienteRepository clienteRepository, RepresentanteRepository representanteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.representanteRepository = representanteRepository;
    }

    public OrdemServico criarOrdemServico(OrdemServicoDTO dto, Usuario usuarioLogado) {
        
        if (usuarioLogado == null) {
            throw new NegocioException("Usuário não identificado.");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente " + dto.getClienteId() + " não encontrado"));

        // CORREÇÃO AQUI: Só valida a "casa" se o usuário NÃO for ADMIN
        if (usuarioLogado.getPerfil() != PerfilUsuario.ADMIN) {
            if (usuarioLogado.getRepresentante() == null || 
                !cliente.getRepresentante().getId().equals(usuarioLogado.getRepresentante().getId())) {
                throw new NegocioException("Este cliente não pertence à carteira do seu representante.");
            }
        }

        OrdemServico novaOS = new OrdemServico();
        novaOS.setCliente(cliente);
        
        // MELHORIA: Use o representante do CLIENTE, assim funciona para Admin e Gerente
        novaOS.setRepresentante(cliente.getRepresentante());
        
        novaOS.setUsuarioResponsavel(usuarioLogado);
        novaOS.setStatus(StatusOrdem.ABERTA);
        // Mapeando a lista de itens do DTO para a Entidade
        if( dto.getItens() != null ) {
            dto.getItens().forEach(itemDTO -> {
                ItemOS item = new ItemOS();
                item.setDescricaoProblema(itemDTO.getDescricaoProblema());
                item.setDescricaoSolucao(itemDTO.getDescricaoSolucao());
                novaOS.adicionarItemOS(item);
            });
        }else {
            throw new NegocioException("A ordem de serviço deve conter pelo menos um item");
        }
        return repository.save(novaOS);
    }

    public List<OrdemServico> buscarTodasOrdens(Usuario executor) {
        // Se for ADMIN, vê tudo (Dashboard Global)
        if (executor.getPerfil() == PerfilUsuario.ADMIN) {
            return repository.findAll();
        }
        // Se for GERENTE ou ANALISTA, vê apenas da sua "casa"
        return repository.findByRepresentanteId(executor.getRepresentante().getId());
    }

    public OrdemServico buscarOrdemPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço com o ID " + id + " não encontrada"));
    }


    public OrdemServico finalizarOrdemServicoPorId(Long id) {
        //1. validar se a ordem de serviço existe
        OrdemServico os = buscarOrdemPorId(id);
       //2. validar se a ordem de serviço já está finalizada
        if (os.getStatus() == StatusOrdem.CONCLUIDA || os.getStatus() == StatusOrdem.CANCELADA) {
            throw new NegocioException("Não é possível finalizar uma ordem que já está " + os.getStatus());
        }
       //3. atualizar o status da ordem de serviço para "finalizada"
        os.setStatus(StatusOrdem.CONCLUIDA);
        os.setDataConclusao(LocalDateTime.now());
        return repository.save(os);
    }

    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico) {
        return repository.save(ordemServico);
    }

    public void cancelarOrdemServico(Long id) {
        OrdemServico os = buscarOrdemPorId(id);
        os.setStatus(StatusOrdem.CANCELADA);
        repository.save(os);
    }
}