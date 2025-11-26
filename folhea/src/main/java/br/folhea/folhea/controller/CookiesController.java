package br.folhea.folhea.controller;

import br.folhea.folhea.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/cookies")
public class CookiesController {

    @GetMapping("")
    public String paginaCookies() {
        return "cookies/index"; // templates/cookies/index.html
    }

    @PostMapping("/criar")
    public String criarCookie(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(defaultValue = "3600") Integer segundos,
            HttpServletResponse response,
            Model model
    ) {

        try {
            CookieService.setCookie(response, key, value, segundos);
            model.addAttribute("mensagem", "Cookie criado com sucesso!");

        } catch (UnsupportedEncodingException e) {
            model.addAttribute("erro", "Erro ao codificar cookie.");
        }

        return "index";
    }

    @GetMapping("/ler")
    public String lerCookie(
            @RequestParam String key,
            HttpServletRequest request,
            Model model
    ) {
        try {
            String valor = CookieService.getCookie(request, key);

            if (valor == null) {
                model.addAttribute("erro", "Cookie não encontrado.");
            } else {
                model.addAttribute("mensagem", "Valor do cookie: " + valor);
            }

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao ler cookie.");
        }

        return "index";
    }

    @GetMapping("/delete")
    public String deletarCookie(
            @RequestParam String key,
            HttpServletResponse response,
            Model model) {

        try {
            CookieService.setCookie(response, key, "", 0);
            model.addAttribute("mensagem", "Cookie deletado com sucesso.");

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao deletar cookie.");
        }

        return "index";
    }
}
