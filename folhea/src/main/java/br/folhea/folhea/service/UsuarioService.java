package br.folhea.folhea.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.folhea.folhea.model.Usuario;
import br.folhea.folhea.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // ✔ Injeção via construtor (forma correta)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ✔ Gera hash da senha com SHA-256
    private String hashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    // ✔ Cadastro com verificação de e-mail duplicado
    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        usuario.setSenha(hashSenha(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    // ✔ Login comparando hash da senha
    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getSenha().equals(hashSenha(senha))) {
                return usuario;
            }
        }

        throw new RuntimeException("Email ou senha inválidos.");
    }
}
