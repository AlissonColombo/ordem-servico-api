package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.dto.ClienteDTO;
import br.com.alisson.ordem_servico.model.Cliente;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.alisson.ordem_servico.repository.RepresentanteRepository;
import java.util.List;
import org.springframework.stereotype.Service;  
import br.com.alisson.ordem_servico.mapper.EnderecoMapper;
import br.com.alisson.ordem_servico.exeption.EntidadeNaoEncontradaException;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final EnderecoMapper enderecoMapper;
    private final RepresentanteRepository representanteRepository;

    public ClienteService(ClienteRepository clienteRepository, EnderecoMapper enderecoMapper, RepresentanteRepository representanteRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoMapper = enderecoMapper;
        this.representanteRepository = representanteRepository;
    }

    public Cliente criarCliente(ClienteDTO dtoCliente) {
        
        Representante rep = representanteRepository.findById(dtoCliente.getRepresentanteId())
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Representante não encontrado!"));

        Cliente cliente = new Cliente();
        cliente.setNome(dtoCliente.getNome());
        cliente.setCnpj(dtoCliente.getCnpj());
        cliente.setTelefone(dtoCliente.getTelefone());
        cliente.setEndereco(enderecoMapper.converterEnderecoDTOParaEndereco(dtoCliente.getEndereco()));
        cliente.setRepresentante(rep);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com o ID "
         + id + " não encontrado"));
    }
}
