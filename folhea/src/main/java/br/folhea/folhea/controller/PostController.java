package br.folhea.folhea.controller;

import br.folhea.folhea.model.Post;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.service.CookieService;
import br.folhea.folhea.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/posts")
public class PostController {

    // colocar em uma classe util ou no topo de cada controller que use cookies
    private static final String COOKIE_USUARIO_ID = "usuarioId";

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {

        Post post = postService.search(id)
                .orElse(null);

        if (post == null) {
            model.addAttribute("erro", "Post não encontrado.");
            return "erro";
        }

        model.addAttribute("post", post);
        return "posts/visualizar"; // thymeleaf: templates/posts/visualizar.html
    }

    @GetMapping("/novo")
    public String novoPost(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String usuarioId = CookieService.getCookie(request, COOKIE_USUARIO_ID);
        if (usuarioId == null) {
            model.addAttribute("erro", "Você precisa estar logado para criar um post.");
            return "login"; // existe login.html
        }
        model.addAttribute("post", new Post());
        return "escrever"; // usar escrever.html (editor)
    }

    @PostMapping("/salvar")
    public String salvarPost(@ModelAttribute("post") Post post, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException {
        String usuarioId = CookieService.getCookie(request, COOKIE_USUARIO_ID);
        if (usuarioId == null) {
            model.addAttribute("erro", "Usuário não logado.");
            return "login";
        }
        try {
            Usuario u = new Usuario();
            u.setId(Long.valueOf(usuarioId));
            post.setUsuario(u);
            postService.save(post);
            return "redirect:/post/" + post.getId(); // combine com rota /post/{id}
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao salvar post: " + e.getMessage());
            return "escrever";
        }
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String usuarioId = CookieService.getCookie(request, COOKIE_USUARIO_ID);
        if (usuarioId == null) {
            model.addAttribute("erro", "Você precisa estar logado.");
            return "login";
        }
        Post post = postService.search(id).orElse(null);
        if (post == null) {
            model.addAttribute("erro", "Post não encontrado.");
            return "erro";
        }
        String idAtual = String.valueOf(post.getUsuario().getId());
        if (!idAtual.equals(usuarioId)) {
            model.addAttribute("erro", "Você não tem permissão para excluir este post.");
            return "erro";
        }
        postService.delete(id);
        return "redirect:/"; // home
    }

}
