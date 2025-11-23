package br.folhea.folhea.repository;

import br.folhea.folhea.model.Post;
import br.folhea.folhea.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findPostByUsuario(Usuario usuario);

}
