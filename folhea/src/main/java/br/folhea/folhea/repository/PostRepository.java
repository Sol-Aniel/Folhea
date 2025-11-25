package br.folhea.folhea.repository;

import br.folhea.folhea.model.Post;
import br.folhea.folhea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByUsuario(Usuario usuario);

}
