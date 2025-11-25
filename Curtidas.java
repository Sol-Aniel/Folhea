package br.folhea.folhea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table( name = "curtidas", schema = "folhea")
@NoArgsConstructor
public class Curtidas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curtida_sq")
    @SequenceGenerator(schema = "folhea", name = "curtida_sq", sequenceName ="curtida_sq", initialValue = 1) // valores em sequencia
    private Long id;


    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;



    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "historia_id")
    private Historia historia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}

