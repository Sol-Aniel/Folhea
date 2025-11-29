package br.folhea.folhea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Usuario {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Getter
    @Setter
    @NotEmpty
    private String nome;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String senha;


    @ElementCollection
    @CollectionTable(name = "usuario_lista_leitura", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "nome_historia")
    private List<Long> historia;

}
