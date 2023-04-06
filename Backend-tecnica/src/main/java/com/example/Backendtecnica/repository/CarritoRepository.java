package com.example.Backendtecnica.repository;

import com.example.Backendtecnica.entities.Carrito;
import com.example.Backendtecnica.entities.UsuarioGenerate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarritoRepository  extends JpaRepository<Carrito, Long> {

    @Query("SELECT c FROM Carrito c WHERE c.idUsuario = :idUsuario AND MONTH(c.fechaCompra) = MONTH(:fechaCompra) AND c.total >= 10000")
    List<Carrito> findComprasVip(@Param("idUsuario") Integer idUsuario, @Param("fechaCompra") LocalDate fechaCompra);

    @Query("SELECT c FROM Carrito c WHERE c.idUsuario = :idUsuario AND MONTH(c.fechaCompra) = MONTH(:fechaCompra)")
    List<Carrito> findCompras(@Param("idUsuario") Integer idUsuario, @Param("fechaCompra") LocalDate fechaCompra);
}
