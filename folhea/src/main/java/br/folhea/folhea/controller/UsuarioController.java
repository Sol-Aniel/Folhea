package br.folhea.folhea.controller;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;
import br.folhea.folhea.service.CookieService;
import br.folhea.folhea.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }



//    @GetMapping("/registrar")
    //public String telaRegistrar(Model model) {
//        model.addAttribute("usuario", new Usuario());
//        return "usuarios/registrar"; // thymeleaf
//    }
//
//    @PostMapping("/registrar")
//    public String registrar(@ModelAttribute Usuario usuario, Model model) {
//
//        try {
//            usuarioService.cadastrar(usuario);
//            model.addAttribute("msg", "Usuário cadastrado com sucesso!");
//            return "redirect:/usuarios/login";
//
//        } catch (RuntimeException e) {
//            model.addAttribute("erro", e.getMessage());
//            return "usuarios/registrar";
//        }
//    }
//
//    @GetMapping("/login")
//    public String telaLogin(Model model) {
//        model.addAttribute("usuario", new Usuario());
//        return "usuarios/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email,
//                        @RequestParam String senha,
//                        Model model,
//                        HttpServletResponse response) {
//
//        try {
//            Usuario usuario = usuarioService.login(email, senha);
//
//            // Armazena cookie com ID do usuário logado
//            CookieService.setCookie(response, "usuarioId", String.valueOf(usuario.getId()), 3600);
//            return "redirect:/home";
//
//        } catch (RuntimeException e) {
//            model.addAttribute("erro", e.getMessage());
//            return "usuarios/login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletResponse response) {
//        CookieService.setCookie(response, "usuarioId", "", 0); // apaga cookie
//        return "redirect:/usuarios/login";
//    }


}
