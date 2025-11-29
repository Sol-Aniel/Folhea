package br.folhea.folhea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table( name = "historia", schema = "folhea")
@NoArgsConstructor
public class Historia  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="titulo")
    private String titulo;

    @Column(name ="avaliacao")
    private Integer avaliacao;

    @Column(name ="text_content")
    private String textContent;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name="tag")
    private String tag;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name="num_leituras")
    private Long leituras ; // Use Long para estatísticas grandes


    @Column(name="num_curtidas")
    private Integer curtidas ;

    @Column(name="num_salvamentos")
    private Integer salvamentos;

}

