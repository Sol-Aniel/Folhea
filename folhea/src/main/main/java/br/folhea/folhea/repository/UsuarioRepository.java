
package br.folhea.folhea.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.folhea.folhea.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



    Optional<Usuario> findByEmail(String email);


    Usuario findUsuarioById(Long id);
}

