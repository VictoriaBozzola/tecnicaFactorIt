package com.example.Backendtecnica.repository;

import com.example.Backendtecnica.entities.UsuarioGenerate;
import com.example.Backendtecnica.entities.UsuarioOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioGenerate, Long> {

    @Query("SELECT u FROM UsuarioGenerate u WHERE u.usuario LIKE %?1%")
    UsuarioGenerate findByUsuario(String usuario);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Usuarios u SET u.vip = :vip WHERE u.id = :idUsuario", nativeQuery = true)
    int modificarTipoUsuario(@Param("idUsuario") Long idUsuario, @Param("vip") boolean vip);
}
