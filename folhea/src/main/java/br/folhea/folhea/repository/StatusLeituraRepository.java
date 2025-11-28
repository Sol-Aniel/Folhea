package br.folhea.folhea.repository;

import br.folhea.folhea.model.StatusLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StatusLeituraRepository extends JpaRepository<StatusLeitura, Long> {
    Optional<StatusLeitura> findByUsuarioIdAndHistoriaId(Long usuarioId, Long historiaId);
}