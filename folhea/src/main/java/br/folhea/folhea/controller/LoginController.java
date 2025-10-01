package br.folhea.folhea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BiningResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String dashboard(Model model, HttpServeletRequest request) {
        model.addAtribute("nome", CookieService.getCookie(request, "nomeUsuario"))
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {

            CookieService.setCookie(response, "UsuarioId", String.valueOf(usuarioLogado.getId()), 10000)
            CookieService.setCookie(response, "UsuarioNome", String.valueOf(usuarioLogado.getNome()), 10000)

            return "redirect:/"

        }

        model.addAttribute("erro", "Usuario invalido!");
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestM < ethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/cadastroUsuario";
        }

        ur.save(usuario);
        return "redirect:/login";
    }
}
