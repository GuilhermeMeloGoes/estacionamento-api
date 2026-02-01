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
    public Usuario criarUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("O e-mail %s informado, já está cadastrado no banco de dados.", usuario.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário de id=%s não encontrado.", idUsuario))
        );
    }

    @Transactional
    public void atualizarSenhaUsuario(Long idUsuario, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Nova senha não confere com a confirmação de senha!");
        }

        Usuario usuario = this.buscarUsuarioPorId(idUsuario);

        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new PasswordInvalidException("A senha atual não confere!");
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);


    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }
}
