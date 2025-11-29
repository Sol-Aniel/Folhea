package br.folhea.folhea.repository;

import br.folhea.folhea.model.Lista_leitura;
import br.folhea.folhea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepository extends JpaRepository<String, Long> {
    List<Lista_leitura> findByIdUsuario(Usuario usuario);
}
