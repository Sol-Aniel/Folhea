package br.folhea.folhea.service;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.Listaleitura;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.HistoriaRepository;
import br.folhea.folhea.repository.ListaRepository;
import br.folhea.folhea.repository.ListaRepository;
import br.folhea.folhea.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    private final ListaRepository listaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoriaRepository historiaRepository;

    public ListaService(ListaRepository listaRepository,
                        UsuarioRepository usuarioRepository,
                        HistoriaRepository historiaRepository) {
        this.listaRepository = listaRepository;
        this.usuarioRepository = usuarioRepository;
        this.historiaRepository = historiaRepository;
    }

    public Listaleitura criarLista(Long usuarioId, String nome, String descricao) {
        if (!StringUtils.hasText(nome)) {
            throw new IllegalArgumentException("O nome da lista é obrigatório.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Listaleitura lista = new Listaleitura();
        lista.setNome(nome);
        lista.setDescricao(descricao);
        lista.setUsuario(usuario);

        return listaRepository.save(lista);
    }

    public List<Listaleitura> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        return listaRepository.findByUsuario(usuario);
    }

    public Optional<Listaleitura> buscarPorId(Long id) {
        return listaRepository.findById(id);
    }

    public void adicionarHistoria(Long listaId, Long historiaId) {
        Listaleitura lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new IllegalArgumentException("Lista de leitura não encontrada."));
        Historia historia = historiaRepository.findById(historiaId)
                .orElseThrow(() -> new IllegalArgumentException("História não encontrada."));

        if (!lista.getHistorias().contains(historia)) {
            lista.getHistorias().add(historia);
            listaRepository.save(lista);
        }
    }

    public void removerHistoria(Long listaId, Long historiaId) {
        Listaleitura lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new IllegalArgumentException("Lista de leitura não encontrada."));
        Historia historia = historiaRepository.findById(historiaId)
                .orElseThrow(() -> new IllegalArgumentException("História não encontrada."));

        if (lista.getHistorias().remove(historia)) {
            listaRepository.save(lista);
        }
    }

    public Listaleitura atualizarLista(Long id, String novoNome, String novaDescricao) {
        return listaRepository.findById(id).map(lista -> {
            if (StringUtils.hasText(novoNome)) lista.setNome(novoNome);
            if (novaDescricao != null) lista.setDescricao(novaDescricao);
            return listaRepository.save(lista);
        }).orElseThrow(() -> new IllegalArgumentException("Lista não encontrada."));
    }

    public void deletarLista(Long id) {
        listaRepository.deleteById(id);
    }
}