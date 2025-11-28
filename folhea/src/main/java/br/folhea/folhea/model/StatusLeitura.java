package br.folhea.folhea.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_leitura")
@Getter
@Setter
public class StatusLeitura {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "historia_id", nullable = false)
    private Long historiaId;


    @Column(name = "lida", nullable = false)
    private boolean lida = false;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;


    public StatusLeitura() {
    }



}