package com.example.Backendtecnica.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name = "FechaEspecial")
public class FechaEspecial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEspecial;

    public FechaEspecial() {
    }

    public FechaEspecial(Long id, LocalDate fechaEspecial) {
        this.id = id;
        this.fechaEspecial = fechaEspecial;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaEspecial() {
        return fechaEspecial;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaEspecial(LocalDate fechaEspecial) {
        this.fechaEspecial = fechaEspecial;
    }
}
