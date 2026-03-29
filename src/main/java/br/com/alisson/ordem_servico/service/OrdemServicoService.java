package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.model.Cliente;
import br.com.alisson.ordem_servico.model.ItemOS;
import br.com.alisson.ordem_servico.model.OrdemServico;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.repository.OrdemServicoRepository;
import br.com.alisson.ordem_servico.model.StatusOrdem;
import br.com.alisson.ordem_servico.dto.OrdemServicoDTO;
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

    public OrdemServico criarOrdemServico(OrdemServicoDTO dto) {

        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente " + dto.getClienteId() + " não encontrado"));
        Representante representante = representanteRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Representante " + dto.getRepresentanteId() + " não encontrado"));    
        
        OrdemServico novaOS = new OrdemServico();
        novaOS.setCliente(cliente);
        novaOS.setRepresentante(representante);

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


    public List<OrdemServico> buscarTodasOrdens() {
        return repository.findAll();
    }

    public OrdemServico buscarOrdemPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço com o ID " + id + " não encontrada"));
    }


    public OrdemServico finalizarOrdemServicoPorId(Long id) {
       
        //1. validar se a ordem de serviço existe
        OrdemServico os = buscarOrdemPorId(id);

       //2. validar se a ordem de serviço já está finalizada
       if (os.getStatus().equals(StatusOrdem.CONCLUIDA) || os.getStatus().equals(StatusOrdem.CANCELADA)) {
           throw new NegocioException("Ordem de serviço já está" + os.getStatus());
       }

       //3. atualizar o status da ordem de serviço para "finalizada"
       os.setStatus(StatusOrdem.CONCLUIDA);
       os.setDataConclusao(LocalDateTime.now());
         return repository.save(os);
    }

    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico) {
        return repository.save(ordemServico);
    }

    public void deletarOrdemServico(Long id) {
        repository.deleteById(id);
    }
}