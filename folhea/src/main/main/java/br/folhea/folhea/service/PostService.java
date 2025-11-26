package br.folhea.folhea.service;

import br.folhea.folhea.model.Post;
import br.folhea.folhea.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    // 🔹 Injeção do repository (necessário para funcionar)
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> search(Long id){
        return postRepository.findById(id);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    @Transactional
    public void delete(long post_id){
        postRepository.deleteById(post_id);
    }
}
