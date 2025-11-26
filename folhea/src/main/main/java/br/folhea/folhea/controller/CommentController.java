package br.folhea.folhea.controller;

import br.folhea.folhea.model.Comment;
import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;
import br.folhea.folhea.service.CommentService;
import br.folhea.folhea.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comentarios")
public class CommentController {

    private final CommentService commentService;
    private final UsuarioRepository usuarioRepository;
    private static final String COOKIE_USUARIO_ID = "usuarioId";

    public CommentController(CommentService commentService, UsuarioRepository usuarioRepository) {
        this.commentService = commentService;
        this.usuarioRepository = usuarioRepository;
    }

    // Utilitário de segurança (pegar usuário logado via cookie)
    private Usuario getUsuarioLogado(HttpServletRequest request) {
        try {
            String idStr = CookieService.getCookie(request, COOKIE_USUARIO_ID);
            if (idStr == null) return null;
            return usuarioRepository.findById(Long.parseLong(idStr)).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/novo")
    public String salvarComentario(@RequestParam String comentario, @RequestParam Long idHistoria, HttpServletRequest request) {
        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";
        try {
            commentService.SalvarnovoComentario(comentario, logado.getId(), idHistoria);
        } catch (RuntimeException e) {
            return "redirect:/comentarios/historia/" + idHistoria + "?erro=" + e.getMessage();
        }
        return "redirect:/comentarios/historia/" + idHistoria;
    }


    @GetMapping("/historia/{idHistoria}")
    public String listarPorHistoria(
            @PathVariable Long idHistoria,
            Model model,
            HttpServletRequest request
    ) {

        Usuario logado = getUsuarioLogado(request);

        model.addAttribute("usuarioLogado", logado);
        model.addAttribute("idHistoria", idHistoria);
        model.addAttribute("comentarios", commentService.mostrarComentariosHistoria(idHistoria));

        return "comentarios"; // templates/comentarios/lista.html
    }

    @GetMapping("/editar/{id}")
    public String paginaEditar(
            @PathVariable Long id,
            HttpServletRequest request,
            Model model
    ) {

        Usuario logado = getUsuarioLogado(request);
        if (logado == null) {
            return "redirect:/login";
        }

        Comment comentario = commentService.buscarComentario(id);
        if (comentario == null) {
            return "redirect:/?erro=Comentário não encontrado";
        }

        // Segurança: só o dono pode editar
        if (comentario.getUsuario().getId() != logado.getId()) {
            return "redirect:/?erro=Você não tem permissão";
        }

        model.addAttribute("comentario", comentario);

        return "comentarios/editar"; // templates/comentarios/editar.html
    }

    @PostMapping("/editar/{id}")
    public String atualizarComentario(
            @PathVariable Long id,
            @RequestParam String comentario,
            HttpServletRequest request
    ) {

        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";

        try {
            Comment novo = new Comment();
            novo.setComment(comentario);
            commentService.atualizarComentario(id, novo);
        } catch (RuntimeException e) {
            return "redirect:/?erro=" + e.getMessage();
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteComentario(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        Usuario logado = getUsuarioLogado(request);
        if (logado == null) return "redirect:/login";

        try {
            commentService.deletarComentario(id);

        } catch (RuntimeException e) {
            return "redirect:/?erro=" + e.getMessage();
        }

        return "redirect:/";
    }
}
