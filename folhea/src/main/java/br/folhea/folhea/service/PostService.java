package br.folhea.folhea.service;


import br.folhea.folhea.model.Post;
import br.folhea.folhea.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostService {
    private PostRepository postRepository;


    public Optional<Post> search(Long id){
        return postRepository.findById(id);
    }
    public Post save(Post post){
        return postRepository.save(post);
    }
    @Transactional
    public void delete(long post_id){
        // Usando o método padrão deleteById do CrudRepository
        postRepository.deleteById(post_id);
    }
}
