package br.folhea.folhea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table( name = "seguidores", schema = "folhea")
@NoArgsConstructor
public class Seguidores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(schema = "folhea", name = "seguidores_sq", sequenceName ="seguidores_sq", initialValue = 1) // valores em sequencia
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seguidores_id")
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
