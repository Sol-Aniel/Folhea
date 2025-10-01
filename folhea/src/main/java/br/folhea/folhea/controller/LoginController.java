package br.folhea.folhea.controller;

import br.folhea.folhea.service.CookieService;
//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;
import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Qualifier("usuarioRepository")
    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String nomeUsuario = CookieService.getCookie(request, "UsuarioNome");
        model.addAttribute("nome",nomeUsuario);
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {

            CookieService.setCookie(response, "UsuarioId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response, "UsuarioNome", String.valueOf(usuarioLogado.getNome()), 10000);

            return "redirect:/";

        }

        model.addAttribute("erro", "Usuario invalido!");
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/cadastroUsuario";
        }

        ur.save(usuario);
        return "redirect:/login";
    }
}
