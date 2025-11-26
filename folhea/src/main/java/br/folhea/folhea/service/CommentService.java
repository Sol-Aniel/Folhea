package br.folhea.folhea.service;

import br.folhea.folhea.model.Comment;
import br.folhea.folhea.model.Historia;
import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.CommentRepository;
import br.folhea.folhea.repository.HistoriaRepository;
import br.folhea.folhea.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    HistoriaRepository historiaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public void deletarComentario( Long id_comentario) { //precisa de alguma medida de segurança pro dado nn ser excluido user logado

        commentRepository.deleteById(id_comentario);
    }

    public void SalvarnovoComentario(String textoComentario, Long id_usuario, Long id_historia) { //salvar novo comentario
        if (textoComentario.trim().isEmpty()) {
            System.out.println("comentario Vazio");
            throw new IllegalArgumentException("O comentário não pode estar vazio.");
        } else {
            Historia historia = historiaRepository.findById(id_historia)
                    .orElseThrow(() -> new RuntimeException("História não encontrada com id: " + id_historia));

            Usuario usuario = usuarioRepository.findById(id_usuario)
                    .orElseThrow(() -> new RuntimeException("Usuario não encontrado com id: " + id_usuario));

            Comment novoComentario = new Comment();

            novoComentario.setComment(textoComentario);
            novoComentario.setUsuario(usuario);
            novoComentario.setHistoria(historia);


            commentRepository.save(novoComentario);

        }
    }

    public Comment buscarComentario(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado com id: " + id));
    }

    public void atualizarComentario(Long id, Comment novoComentario){

        if (novoComentario.getComment().trim().isEmpty()){
            throw new RuntimeException("comentario vazio");

        }
            commentRepository.findById(id).map( comentario ->{
                comentario.setComment(novoComentario.getComment());
                return commentRepository.save(comentario);
            } );
    }

    public List<Comment> mostrarComentariosHistoria(Long id_historia){
       Optional<Historia> historia = historiaRepository.findById(id_historia);
       if( historia.isEmpty() ) {
           System.out.println("historia não encontrada");
       }
        return commentRepository.findAllByHistoria( historia);

       }

    }






