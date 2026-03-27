package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.dto.ClienteDTO;
import br.com.alisson.ordem_servico.model.Cliente;
import br.com.alisson.ordem_servico.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;  
import br.com.alisson.ordem_servico.mapper.EnderecoMapper;
import br.com.alisson.ordem_servico.exeption.EntidadeNaoEncontradaException;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final EnderecoMapper enderecoMapper;
    
    public ClienteService(ClienteRepository clienteRepository, EnderecoMapper enderecoMapper) {
        this.clienteRepository = clienteRepository;
        this.enderecoMapper = enderecoMapper;
    }

    public Cliente criarCliente(ClienteDTO dtoCliente) {
        
        Cliente cliente = new Cliente();
        cliente.setNome(dtoCliente.getNome());
        cliente.setCnpj(dtoCliente.getCnpj());
        cliente.setTelefone(dtoCliente.getTelefone());
        cliente.setEndereco(enderecoMapper.converterEnderecoDTOParaEndereco(dtoCliente.getEndereco()));

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
