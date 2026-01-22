package com.estacionamento.repository;

import com.estacionamento.entity.Cliente;
import com.estacionamento.repository.projection.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c")
    Page<ClienteProjection> findAllPageble(Pageable pageable);

    Cliente findByUsuarioId(Long idUsuario);
}
