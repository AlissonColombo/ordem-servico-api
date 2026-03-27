package br.com.alisson.ordem_servico.controller;

import br.com.alisson.ordem_servico.dto.OrdemServicoDTO;
import br.com.alisson.ordem_servico.model.OrdemServico;
import br.com.alisson.ordem_servico.service.OrdemServicoService;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemdeServicoController {
    private final OrdemServicoService service;

    public OrdemdeServicoController(OrdemServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemCriada = service.criarOrdemServico(ordemServicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemCriada);
    }

    @GetMapping
    public List<OrdemServico> buscarTodasOrdens() {
        return service.buscarTodasOrdens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarOrdemPorId(id));
    }    

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizarOrdem(@PathVariable Long id) {
        OrdemServico ordemFinalizada = service.finalizarOrdemServicoPorId(id);
        return ResponseEntity.ok(ordemFinalizada);
    }


}
