package br.folhea.folhea.repository;

import br.folhea.folhea.model.Listaleitura;
import br.folhea.folhea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<Listaleitura, Long> {

    // 🔍 Buscar todas as listas de leitura de um usuário específico
    List<Listaleitura> findByUsuario(Usuario usuario);

    // (Opcional) Buscar uma lista específica de um usuário por nome
    // — útil se quiser evitar duplicidade de nomes por usuário
    Listaleitura findByUsuarioAndNome(Usuario usuario, String nome);

}
