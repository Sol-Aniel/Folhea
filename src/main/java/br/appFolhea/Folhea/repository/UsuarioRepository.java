package br.appFolhea.Folhea.repository;

import br.appFolhea.Folhea.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findById(long id);

}

