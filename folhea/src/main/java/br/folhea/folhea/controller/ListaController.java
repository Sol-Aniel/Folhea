package br.folhea.folhea.controller;

import br.folhea.folhea.model.Listaleitura;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.HistoriaRepository;
import br.folhea.folhea.repository.UsuarioRepository;
import br.folhea.folhea.service.CookieService;
import br.folhea.folhea.service.ListaService;
import br.folhea.folhea.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HistoriaRepository historiaRepository;

    private Usuario getUsuarioLogado(HttpServletRequest request) {
        try {
            String valor = CookieService.getCookie(request, "UsuarioId");
            if (valor == null) return null;

            return usuarioRepository.findById(Long.parseLong(valor)).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public String listarListas(Model model, HttpServletRequest request) {
        Usuario usuarioLogado = getUsuarioLogado(request);
        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        List<Listaleitura> listas = listaService.listarPorUsuario(usuarioLogado.getId());
        model.addAttribute("usuario", usuarioLogado);
        model.addAttribute("listas", listas);
        return "listasLeitura";
    }

    @GetMapping("/nova")
    public String novaListaForm(Model model) {
        model.addAttribute("historias", historiaRepository.findAll());
        model.addAttribute("lista", new Listaleitura());
        return "criar_lista_nova"; //
    }

    @PostMapping("/salvar")
    public String salvarLista(@ModelAttribute("lista") Listaleitura lista,
                              HttpServletRequest request) {
        Usuario usuarioLogado = getUsuarioLogado(request);
        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        listaService.criarLista(usuarioLogado.getId(), lista.getNome(), lista.getDescricao());
        return "redirect:/listas";
    }

    @GetMapping("/{id}")
    public String detalhesLista(@PathVariable Long id, Model model, HttpServletRequest request) {
        Usuario usuarioLogado = getUsuarioLogado(request);
        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        Listaleitura lista = listaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada."));

        model.addAttribute("lista", lista);
        model.addAttribute("usuario", usuarioLogado);
        return "lista"; //
    }

    @PostMapping("/{listaId}/remover/{historiaId}")
    public String removerHistoria(@PathVariable Long listaId,
                                  @PathVariable Long historiaId) {
        listaService.removerHistoria(listaId, historiaId);
        return "redirect:/listas/" + listaId;
    }

    @PostMapping("/deletar/{id}")
    public String deletarLista(@PathVariable Long id) {
        listaService.deletarLista(id);
        return "redirect:/listas";
    }
}