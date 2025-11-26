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

        String usuarioId = CookieService.getCookie(request, "UsuarioId");

        if (usuarioId == null) {
            model.addAttribute("erro", "Você precisa estar logado para criar um post.");
            return "login";
        }

        model.addAttribute("post", new Post());
        return "posts/novo"; // thymeleaf: templates/posts/novo.html
    }

    @PostMapping("/salvar")
    public String salvarPost(
            @ModelAttribute("post") Post post,
            HttpServletRequest request,
            Model model
    ) throws UnsupportedEncodingException {

        String usuarioId = CookieService.getCookie(request, "UsuarioId");

        if (usuarioId == null) {
            model.addAttribute("erro", "Usuário não logado.");
            return "login";
        }

        try {
            Usuario u = new Usuario();
            u.setId(Long.valueOf(usuarioId));

            post.setUsuario(u);

            postService.save(post);

            // redireciona para visualizar o post criado
            return "redirect:/posts/" + post.getId();

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao salvar post: " + e.getMessage());
            return "posts/novo";
        }
    }

    @GetMapping("/deletar/{id}")
    public String confirmarDelete(@PathVariable Long id, Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        String usuarioId = CookieService.getCookie(request, "UsuarioId");

        if (usuarioId == null) {
            model.addAttribute("erro", "Você precisa estar logado.");
            return "login";
        }

        Post post = postService.search(id).orElse(null);

        if (post == null) {
            model.addAttribute("erro", "Post não encontrado.");
            return "erro";
        }

        model.addAttribute("post", post);
        return "posts/confirmarDelete"; // thymeleaf: templates/posts/confirmarDelete.html
    }

    @PostMapping("/deletar/{id}")
    public String deletar(
            @PathVariable Long id,
            HttpServletRequest request,
            Model model) throws UnsupportedEncodingException {

        String usuarioId = CookieService.getCookie(request, "UsuarioId");

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

        // segurança: só autor pode excluir
        if (idAtual != usuarioId) {
            model.addAttribute("erro", "Você não tem permissão para excluir este post.");
            return "erro";
        }

        postService.delete(id);
        return "redirect:/"; // redireciona para home
    }
}
