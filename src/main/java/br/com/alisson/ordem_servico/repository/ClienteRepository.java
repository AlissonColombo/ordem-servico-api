package br.com.alisson.ordem_servico.repository;

import br.com.alisson.ordem_servico.model.Cliente;

//biblioteca com os comandos para acessar o banco de dados, usando o spring data jpa
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


//Essa anotação indica que essa interface é um repositório do Spring, responsável por acessar os dados do cliente no banco de dados

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 
    /*  Interface que estende JpaRepository, que é uma interface do Spring Data JPA que fornece métodos para realizar operações de CRUD 
    (Create, Read, Update, Delete) e outras operações de acesso a dados. O primeiro parâmetro é a entidade que o repositório irá gerenciar
    (Cliente)e o segundo parâmetro é o tipo do identificador da entidade (Long). */

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    // Método para buscar clientes por nome, ignorando maiúsculas e minúsculas.
    
}
