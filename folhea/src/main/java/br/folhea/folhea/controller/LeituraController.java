package br.folhea.folhea.controller;


import br.folhea.folhea.service.LeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leitura")
public class LeituraController {

    private final LeituraService leituraService;

    @Autowired
    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }



    @PostMapping("/marcar-lida/{historiaId}")
    public ResponseEntity<Void> marcarHistoriaComoLida(@PathVariable Long historiaId) {


        Long usuarioId = obterUsuarioAutenticado();

        leituraService.marcarComoLida(usuarioId, historiaId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long obterUsuarioAutenticado() {
        return 5L;
    }
}