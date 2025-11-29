// br.folhea.folhea.service.ListaLeituraService.java

package br.folhea.folhea.service;

import br.folhea.folhea.model.Listaleitura;
import br.folhea.folhea.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;


}