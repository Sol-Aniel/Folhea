package br.folhea.folhea.controller;

import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.StatusHistoria;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.service.CookieService;
import br.folhea.folhea.service.HistoriaService;
import br.folhea.folhea.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historias")
public class HistoriaController {

    private final HistoriaService historiaService;
    private final UsuarioRepository usuarioRepository;

    public HistoriaController(HistoriaService historiaService, UsuarioRepository usuarioRepository) {
        this.historiaService = historiaService;
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario getUsuarioLogado(HttpServletRequest request) {
        try {
            String valor = CookieService.getCookie(request, "UsuarioId");
            if (valor == null) return null;

            return usuarioRepository.findById(Long.parseLong(valor)).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("")
    public String listarHistorias(Model model, HttpServletRequest request) {

        model.addAttribute("listas", historiaService.listarHistorias()); // <-- Mude o nome da variável
        model.addAttribute("usuarioLogado", getUsuarioLogado(request));

        return "biblioteca";
    }

    @GetMapping("/{id}")
    public String visualizarHistoria(@PathVariable Long id, Model model, HttpServletRequest request) {

        Historia historia = historiaService.buscarPorId(id).orElse(null);


        model.addAttribute("historia", historia);

        return "detalhes"; // templates/historias/detalhes.html
    }

    @GetMapping("/nova")
    public String paginaNovaHistoria(HttpServletRequest request, Model model) {

        Usuario logado = getUsuarioLogado(request);
        //if (logado == null)
          //  return "redirect:/login";

        model.addAttribute("historia", new Historia());
        model.addAttribute("usuarioLogado", logado);
        return "criar"; // templates/historias/form.html
    }

    @PostMapping("/nova")
    public String salvarHistoria(
            @RequestParam String titulo,
            @RequestParam String sinopse,
            @RequestParam String tag,
            @RequestParam String conteudoCapitulo,
            HttpServletRequest request,
            Model model
    ) {
        Usuario logado = getUsuarioLogado(request);
        //if (logado == null)
        //  return "redirect:/login";

        try {
            Historia h = new Historia();
            h.setTitulo(titulo);
            h.setTag(tag);
            h.setTextContent(conteudoCapitulo);
            h.setUsuario(logado);
            h.setSinopse(sinopse);
            h.setTag(tag);
            System.out.println("titulo: '" + titulo + "'");
            System.out.println("sinopse: '" + sinopse + "'");
            System.out.println("tag: '" + tag + "'");
            System.out.println("conteudo: '" + conteudoCapitulo + "'");
            System.out.println("usuario: " + logado);

            h.setStatus(StatusHistoria.ANDAMENTO);



            historiaService.salvarHistoria(h);

            return "redirect:/historias?sucesso=História criada";

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("usuarioLogado", logado);
            System.out.println(e.getMessage());
            return "redirect:/historias/nova";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarHistoria(
            @PathVariable Long id,
            HttpServletRequest request,
            Model model
    ) {
        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";

        Historia historia = historiaService.buscarPorId(id).orElse(null);
        if (historia == null) return "redirect:/historias?erro=História não encontrada";

        // Permissão
        if (historia.getUsuario().getId() != logado.getId())
            return "redirect:/historias?erro=Sem permissão";

        model.addAttribute("historia", historia);
        model.addAttribute("usuarioLogado", logado);
        return "historias/editar"; // templates/historias/editar.html
    }

    @PostMapping("/editar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String conteudoCapitulo,
            HttpServletRequest request) {

        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";

        Historia historia = historiaService.buscarPorId(id).orElse(null);
        if (historia == null) return "redirect:/historias?erro=História não encontrada";

        if (historia.getUsuario().getId() != logado.getId())
            return "redirect:/historias?erro=Sem permissão";

        Historia nova = new Historia();
        nova.setTitulo(titulo);
        nova.setTextContent(conteudoCapitulo);
        nova.setUsuario(logado);

        historiaService.atualizarHistoria(id, nova);
        return "redirect:/historias/" + id + "?sucesso=Atualizada";
    }

    @GetMapping("/delete/{id}")
    public String deletar(
            @PathVariable Long id,
            HttpServletRequest request) {

        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";

        Historia historia = historiaService.buscarPorId(id).orElse(null);
        if (historia == null)
            return "redirect:/historias?erro=História não encontrada";

        if (historia.getUsuario().getId() != logado.getId())
            return "redirect:/historias?erro=Sem permissão";

        historiaService.deletarHistoria(id);

        return "redirect:/historias?sucesso=História deletada";
    }

    @GetMapping("/biblioteca")
    public String listarHistoriasPorBusca(
            @RequestParam(value = "busca", required = false) String termoBusca,
            Model model,
            HttpServletRequest request) {

        // Lógica para filtrar histórias baseada no termoBusca
        if (termoBusca != null && !termoBusca.isEmpty()) {
            List listaHistorias = historiaService.buscarPorTermo(termoBusca);


            model.addAttribute("listas", listaHistorias); // Substitua com sua busca real
        } else {
            model.addAttribute("listas", historiaService.listarHistorias());
        }

        model.addAttribute("usuarioLogado", getUsuarioLogado(request));

        // Supondo que a página de biblioteca se chame "biblioteca"
        return "biblioteca";
    }
    @GetMapping("/listasLeitura")
    public String irPraListaDeLeitura() {

    return "listasLeitura";
    }

    }

