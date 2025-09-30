package br.appFolhea.Folhea.controller;

import br.appFolhea.Folhea.model.Usuario;
import br.appFolhea.Folhea.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastro(){
        return "cadastro";
    }

    @RequestMapping (value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result){

        if (result.hasErrors()){
            return "redirect:/cadastroUsuario";
        }

        ur.save(usuario);

        return "";
    }

}