package com.example.Backendtecnica.repository;

import com.example.Backendtecnica.entities.FechaEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaRepository extends JpaRepository<FechaEspecial, Long> {


}
