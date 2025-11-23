package br.folhea.folhea.repository;

import br.folhea.folhea.model.Historia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoriaRepository extends CrudRepository <Historia, Long>{

    List<Historia> findByTitulo(String titulo);
}
