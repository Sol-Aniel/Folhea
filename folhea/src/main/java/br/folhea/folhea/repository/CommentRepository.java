package br.folhea.folhea.repository;

import br.folhea.folhea.model.Comment;
import br.folhea.folhea.model.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
/*
salvar comentario ja vem no crud
editar ja vem no crud
excluir por id ja vem no crud
mostrar todos os comentarios de uma publicação ja vem no crud
 */


}
