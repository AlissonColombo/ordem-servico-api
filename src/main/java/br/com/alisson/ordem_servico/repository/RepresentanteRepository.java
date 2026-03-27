package br.com.alisson.ordem_servico.repository;

import br.com.alisson.ordem_servico.model.Representante;
import org.springframework.data.jpa.repository.JpaRepository;   
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    
    List<Representante> findByNomeContainingIgnoreCase(String nome);
    // Método para buscar representantes por nome, ignorando maiúsculas e minúsculas.     
    List<Representante> findByCodigoSAP(int codigoSAP);
    // Método para buscar representantes por código SAP.
    List<Representante> findByEnderecoUfContainingIgnoreCase(String uf);  
    // Método para buscar representantes por estado (UF) do endereço, ignorando maiúsculas e minúsculas.

}
