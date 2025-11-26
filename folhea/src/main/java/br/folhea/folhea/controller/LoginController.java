package br.folhea.folhea.controller;

import br.folhea.folhea.service.CookieService;
//import ch.qos.logback.core.model.Model;
import br.folhea.folhea.service.UsuarioService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;
import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Qualifier("usuarioService")

    @Autowired
    private UsuarioService uservice;

    @GetMapping("/login")
    public String login(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "login";
    }

    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String nomeUsuario = CookieService.getCookie(request, "UsuarioNome");
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            nomeUsuario = "Visitante";
        }
        model.addAttribute("nome",nomeUsuario);
        return "index";
    }

    @RequestMapping(value = "/logar", method = RequestMethod.POST)
    public String loginUsuario(Model model, @Valid Usuario usuario, BindingResult result, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario usuarioLogado = uservice.login(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {

            CookieService.setCookie(response, "UsuarioId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response, "UsuarioNome", String.valueOf(usuarioLogado.getNome()), 10000);

            return "redirect:/";

        }

        model.addAttribute("error", "Usuario invalido!");
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(Model model, @Valid Usuario usuario, BindingResult result, @RequestParam("confirmSenha")String confirmSenha) {

        if (result.hasErrors()) {
            return "cadastro";
        }

        if (!usuario.getSenha().equals(confirmSenha)) {
            model.addAttribute("error", "As senhas não coincidem!");
            return "cadastro";
        }

        uservice.cadastrar(usuario);
        return "redirect:/login";
    }
}