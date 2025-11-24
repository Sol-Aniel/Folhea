package br.folhea.folhea.service;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.repository.HistoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository historiaRepository;

    public Historia salvarHistoria(Historia historia) {
        return historiaRepository.save(historia);
    }

    public List<Historia> listarHistorias() {
        return historiaRepository.findAll();
    }

    public Optional<Historia> buscarPorId(Long id) {
        return historiaRepository.findById(id);
    }

    public Historia atualizarHistoria(Long id, Historia historiaAtualizada) {
        return historiaRepository.findById(id).map(historia -> {
            historia.setTitulo(historiaAtualizada.getTitulo());
            historia.setConteudo(historiaAtualizada.getConteudo());
            return historiaRepository.save(historia);
        }).orElse(null);
    }

    public void deletarHistoria(Long id) {
        historiaRepository.deleteById(id);
    }

    public List<Historia> buscarPorTitulo(String titulo) {
        return historiaRepository.findByTitulo(titulo);
    }
}
