
package br.folhea.folhea.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.folhea.folhea.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Optional<Usuario> findById(long id);

    Optional<Usuario> findByEmail(String email);

//    @Query(value="select * from folhea.usuario where email = :email and senha = :senha", nativeQuery = true)
//    public Usuario login(String email, String senha);



}

