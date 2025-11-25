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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historia_sq")
    @SequenceGenerator(schema = "folhea", name = "historia_sq", sequenceName ="historia_sq", initialValue = 1) // valores em sequencia
    private Long id;

    @Column(name="status")
    private StatusHistoria status;

    @Column(name ="titulo")
    private String titulo;

    @Column(name ="avaliacao")
    private int avaliacao;

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



}

