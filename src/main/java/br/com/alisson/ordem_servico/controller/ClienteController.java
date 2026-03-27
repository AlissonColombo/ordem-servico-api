package br.com.alisson.ordem_servico.controller;

import br.com.alisson.ordem_servico.dto.ClienteDTO;
import br.com.alisson.ordem_servico.model.Cliente;
import br.com.alisson.ordem_servico.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
// aqui estamos avisando ao Spring que essa classe vai retornar dados diretamente no corpo da resposta (JSON), 
// sem precisar de uma view (como em aplicações MVC tradicionais)
@RequestMapping("/clientes") // Define o caminho base para as requisições relacionadas a clientes
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping // Define que este método irá responder a requisições POST
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO dtoCliente) {
        Cliente clienteCriado = clienteService.criarCliente(dtoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado); // Retorna o cliente criado com status 201
    }

    @GetMapping // Define que este método irá responder a requisições GET
    public List<Cliente> buscarTodosClientes() {
        return clienteService.buscarTodosClientes(); // Retorna a lista de clientes encontrados
    }

    @GetMapping("/{id}") // Define que este método irá responder a requisições GET com um ID específico
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente); // Retorna o cliente encontrado com status 200
    }

    
}
