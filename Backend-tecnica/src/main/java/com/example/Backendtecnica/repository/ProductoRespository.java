package com.example.Backendtecnica.repository;

import com.example.Backendtecnica.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRespository extends JpaRepository<Producto, Long> {

}
