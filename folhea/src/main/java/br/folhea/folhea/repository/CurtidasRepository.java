package br.folhea.folhea.repository;

import br.folhea.folhea.model.Curtidas;
import br.folhea.folhea.model.Seguidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CurtidasRepository extends CrudRepository<Curtidas,Long> {
  /*
salvar curtida
excluir
mostrar numero de curtidas
 */

}
