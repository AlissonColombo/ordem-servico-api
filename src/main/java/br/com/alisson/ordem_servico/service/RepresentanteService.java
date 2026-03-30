package br.com.alisson.ordem_servico.service;

import br.com.alisson.ordem_servico.dto.RepresentanteDTO;
import br.com.alisson.ordem_servico.model.Representante;
import br.com.alisson.ordem_servico.repository.RepresentanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import br.com.alisson.ordem_servico.mapper.EnderecoMapper;
import br.com.alisson.ordem_servico.exeption.*;

@Service
public class RepresentanteService {
    
    private final RepresentanteRepository repository;
    private final EnderecoMapper enderecoMapper;

    public RepresentanteService(RepresentanteRepository representanteRepository, EnderecoMapper enderecoMapper) {
        this.repository = representanteRepository;
        this.enderecoMapper = enderecoMapper;
    }


    public Representante criarRepresentante(RepresentanteDTO representanteDTO) {

        Representante representante = new Representante();
        representante.setNome(representanteDTO.getNome());
        representante.setCodigoSAP(representanteDTO.getCodigoSAP());
        if (representanteDTO.getEndereco() != null) {
            representante.setEndereco(enderecoMapper.converterEnderecoDTOParaEndereco(representanteDTO.getEndereco()));
        }

        return repository.save(representante);

    }   

    public List<Representante> buscarTodosRepresentantes() {
        return repository.findAll();
    }

    public Representante buscarRepresentantePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Representante " + id + " não encontrado"));
    }

}
