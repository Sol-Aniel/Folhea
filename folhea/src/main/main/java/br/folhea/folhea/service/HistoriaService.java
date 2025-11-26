package br.folhea.folhea.service;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.HistoriaRepository;
import br.folhea.folhea.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository historiaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarHistoria(Historia historia){
        if (historia.getUsuario() == null || historia.getTitulo().trim().isEmpty() ||historia.getTextContent().trim().isEmpty() || historia.getSinopse() == null|| historia.getTag() == null){
            throw new IllegalArgumentException("Usuário, título e conteúdo são obrigatórios.");
        }
        historiaRepository.save(historia);
    }

    public List<Historia> listarHistorias(){

        return historiaRepository.findAll();
    }
    public List<Historia> buscarPorUsuario(Long id_usuario) {
       Usuario usuario = usuarioRepository.findUsuarioById(id_usuario);

        return historiaRepository.findAllByUsuario(usuario);
    }
    public Optional<Historia> buscarPorId(Long id) {
        return historiaRepository.findById(id);
    }
    public Historia atualizarHistoria(Long id, Historia historiaAtualizada) {
        return historiaRepository.findById(id).map(historia -> {
            historia.setTitulo(historiaAtualizada.getTitulo());
            historia.setTextContent(historiaAtualizada.getTextContent());
            return historiaRepository.save(historia);
        }).orElse(null);

    }

    public void deletarHistoria(Long id) {
        historiaRepository.deleteById(id);
    }

    public List<Historia> buscarPorTermo(String titulo) {
        return historiaRepository.findByTituloContainingIgnoreCase(titulo);
    }
}