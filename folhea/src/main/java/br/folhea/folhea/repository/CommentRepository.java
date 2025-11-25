package br.folhea.folhea.repository;

import br.folhea.folhea.model.Comment;
import br.folhea.folhea.model.Historia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByHistoria(Optional<Historia> historia);
/*
salvar comentario ja vem no crud
editar ja vem no crud
excluir por id ja vem no crud
mostrar todos os comentarios de uma publicação ja vem no crud
 */


}
