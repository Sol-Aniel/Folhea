package br.folhea.folhea.controller;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.service.CookieService;
import br.folhea.folhea.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        String nomeUsuario = CookieService.getCookie(request, "UsuarioNome");

        // Se não tiver cookie = visitante
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            nomeUsuario = "Visitante";
        }

        model.addAttribute("nome", nomeUsuario);
        return "index";
    }

    @PostMapping("/logar")
    public String logar(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            Model model,
            HttpServletResponse response
    ) throws UnsupportedEncodingException {

        if (result.hasErrors()) {
            return "login";
        }

        // Tenta login
        Usuario usuarioLogado = usuarioService.login(usuario.getEmail(), usuario.getSenha());

        if (usuarioLogado == null) {
            model.addAttribute("error", "Usuário ou senha inválidos.");
            return "login";
        }

        // Criar cookies do usuário
        CookieService.setCookie(response, "UsuarioId", String.valueOf(usuarioLogado.getId()), 86400);
        CookieService.setCookie(response, "UsuarioNome", usuarioLogado.getNome(), 86400);

        return "redirect:/";
    }

    @GetMapping("cadastroUsuario")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("cadastroUsuario")
    public String cadastrarUsuario(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            @RequestParam("confirmSenha") String confirmSenha,
            Model model
    ) {

        if (result.hasErrors()) {
            return "cadastro";
        }

        if (!usuario.getSenha().equals(confirmSenha)) {
            model.addAttribute("error", "As senhas não coincidem!");
            return "cadastro";
        }

        usuarioService.cadastrar(usuario);
        return "redirect:/login";
    }


    @GetMapping("logout")
    public String logout(HttpServletResponse response) throws UnsupportedEncodingException {

        CookieService.setCookie(response, "UsuarioId", "", 0);
        CookieService.setCookie(response, "UsuarioNome", "", 0);

        return "redirect:/login";
    }
}
