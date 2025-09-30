package br.folhea.folhea.repository;


import org.springframework.data.repository.CrudRepository;

import br.folhea.folhea.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findById(long id);

}