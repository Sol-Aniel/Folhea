package br.folhea.folhea.service;

import br.folhea.folhea.repository.StatusLeituraRepository;

import br.folhea.folhea.model.StatusLeitura; // Sua nova entidade
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeituraService {

    @Autowired
    private StatusLeituraRepository statusLeituraRepository;

    public void marcarComoLida(Long usuarioId, Long historiaId) {
        StatusLeitura status = statusLeituraRepository.findByUsuarioIdAndHistoriaId(usuarioId, historiaId)
                .orElse(new StatusLeitura()); // Se não existir, cria um novo

        // Define os dados chave
        status.setUsuarioId(usuarioId);
        status.setHistoriaId(historiaId);

        status.setLida(true);
        status.setDataConclusao(LocalDateTime.now());

        statusLeituraRepository.save(status);
    }
}