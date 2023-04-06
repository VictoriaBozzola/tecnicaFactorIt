package com.example.Backendtecnica.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table( name = "ComprasRealizadas")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer idUsuario;
    private Tipo tipo;
    @Convert(converter = JpaConverterJson.class)
    private ArrayList<Producto> productos;
    private Integer total;
    private LocalDate fechaCompra;


    public enum Tipo{
        COMUN,
        PROMOCION,
        VIP
    }

    // constructor


    public Carrito() {
    }

    public Carrito(Long id, Integer idUsuario, Tipo tipo,  ArrayList<Producto> productos, Integer total, LocalDate fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.productos = productos;
        this.total = total;
        this.fechaCompra = fechaCompra;
    }

    // Getter y Setter


    public Long getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public  ArrayList<Producto> getProductos() {
        return productos;
    }

    public Integer getTotal() {
        return total;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setProductos( ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}