package br.folhea.folhea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table( name = "lista_leitura", schema = "folhea")
@NoArgsConstructor
public class Listaleitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(schema = "folhea", name = "listaLeitura_sq", sequenceName = "listaLeitura_sq", initialValue = 1)
    private Long id;


    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "lista_leitura_historia",
            joinColumns = @JoinColumn(name = "lista_leitura_id"),
            inverseJoinColumns = @JoinColumn(name = "historia_id")
    )
    private List<Historia> historias = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public void setUsuario(Usuario id_usuario) {}

}