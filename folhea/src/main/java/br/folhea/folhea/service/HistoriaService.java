package br.folhea.folhea.service;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.repository.HistoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaService {
    @Autowired
    private HistoriaRepository historiaRepository; // Injeta a interface

    public Historia salvarHistoria(Historia historia) {
        return historiaRepository.save(historia);
    }
    @Transactional
    public void  deletarHistoria(Historia historia){
          historiaRepository.delete(historia);
    }

}
