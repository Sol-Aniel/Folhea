package br.folhea.folhea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Setter
    @NotEmpty
    private String nome;
    @Setter
    private String email;
    @Setter
    private String senha;

    @Column(nullable = true, length = 500)
    private String biografia;

    @Column(nullable = true)
    private String urlFotoPerfil;

    public void setId(long id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public long getId() {
        return id;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    public String getBiografia() {return this.biografia;}

    public void seturlFotoPerfil(String urlFotoPerfil){this.urlFotoPerfil = urlFotoPerfil;}

    public String getUrlFotoPerfil() {return this.urlFotoPerfil;}
}
