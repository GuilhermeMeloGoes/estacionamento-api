package com.estacionamento.service;

import com.estacionamento.entity.Usuario;
import com.estacionamento.exception.EntityNotFoundException;
import com.estacionamento.exception.PasswordInvalidException;
import com.estacionamento.exception.UsernameUniqueViolationException;
import com.estacionamento.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario createUser(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException("O e-mail informado, já está cadastrado no banco de dados.");
        }

    }

    @Transactional(readOnly = true)
    public Usuario findByIdUser(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário de id=%s não encontrado.", idUsuario))
        );
    }

    @Transactional
    public void updatePasswordUser(Long idUsuario, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Nova senha não confere com a confirmação de senha!");
        }

        Usuario user = this.findByIdUser(idUsuario);

        if (!user.getPassword().equals(senhaAtual)) {
            throw new PasswordInvalidException("A senha atual não confere!");
        }

        user.setPassword(novaSenha);
        usuarioRepository.save(user);


    }


    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }
}
