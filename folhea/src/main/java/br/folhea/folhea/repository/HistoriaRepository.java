package br.folhea.folhea.repository;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoriaRepository extends JpaRepository<Historia, Long> {

    List<Historia> findByTitulo(String titulo);

    List<Historia> findAllByUsuario(Usuario usuario);
}
