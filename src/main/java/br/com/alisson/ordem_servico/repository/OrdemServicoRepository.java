package br.com.alisson.ordem_servico.repository;

/*Duvidas ficu anotacao na interface ClienteRepository */

import br.com.alisson.ordem_servico.model.OrdemServico;
import br.com.alisson.ordem_servico.model.StatusOrdem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByStatus(StatusOrdem status); 
    /* Método para buscar ordens de serviço por status */
}
