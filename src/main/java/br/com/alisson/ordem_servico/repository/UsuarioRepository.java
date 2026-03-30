package br.com.alisson.ordem_servico.repository;

import br.com.alisson.ordem_servico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método vital para o Login 
    Optional<Usuario> findByEmail(String email);

    //listar todos os usuários de um representante no Dashboard
    java.util.List<Usuario> findByRepresentanteId(Long representanteId);
}