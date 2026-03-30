package br.com.alisson.ordem_servico.controller;

import br.com.alisson.ordem_servico.dto.OrdemServicoDTO;
import br.com.alisson.ordem_servico.model.OrdemServico;
import br.com.alisson.ordem_servico.model.Usuario;
import br.com.alisson.ordem_servico.service.OrdemServicoService;
import br.com.alisson.ordem_servico.service.UsuarioService; // Importante para o mock do usuário
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemdeServicoController {

    private final OrdemServicoService service;
    private final UsuarioService usuarioService; // Injetado para buscar quem está operando

    public OrdemdeServicoController(OrdemServicoService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdemServico(
            @RequestBody OrdemServicoDTO dto, 
            @RequestHeader("usuario-id") Long usuarioId) { // Recebemos o ID via Header temporariamente
        
        // Buscamos o usuário que está tentando criar a OS
        Usuario executor = usuarioService.buscarPorId(usuarioId);
        
        OrdemServico ordemCriada = service.criarOrdemServico(dto, executor);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemCriada);
    }

    @GetMapping
    public List<OrdemServico> buscarTodasOrdens(@RequestHeader("usuario-id") Long usuarioId) {
        // Agora o Service usa o perfil do usuário para decidir o que retornar
        Usuario executor = usuarioService.buscarPorId(usuarioId);
        return service.buscarTodasOrdens(executor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarOrdemPorId(id));
    }    

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizarOrdem(@PathVariable Long id) {
        // A lógica de data e status já está automatizada no Service/Model
        OrdemServico ordemFinalizada = service.finalizarOrdemServicoPorId(id);
        return ResponseEntity.ok(ordemFinalizada);
    }
}