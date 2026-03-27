package br.com.alisson.ordem_servico.controller;

import br.com.alisson.ordem_servico.dto.RepresentanteDTO;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.service.RepresentanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/representantes")
public class RepresentanteController {
    
    private final RepresentanteService representanteService;

    public RepresentanteController(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @PostMapping
    public ResponseEntity<Representante> criarRepresentante(@RequestBody RepresentanteDTO representanteDTO) {
        Representante representanteCriado = representanteService.criarRepresentante(representanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(representanteCriado);

    }

    @GetMapping
    public List<Representante> buscarTodosClientes() {
        return representanteService.buscarTodosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Representante> buscarRepresentantePorId(@PathVariable Long id) {
        Representante representante = representanteService.buscarRepresentantePorId(id);
        return ResponseEntity.ok(representante);
    }
}


