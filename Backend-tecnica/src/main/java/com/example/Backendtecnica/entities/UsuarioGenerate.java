package com.example.Backendtecnica.entities;

import javax.persistence.*;

@Entity
@Table( name = "Usuarios")
public class UsuarioGenerate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private boolean vip;

    public UsuarioGenerate() {
    }

    public UsuarioGenerate(Long idUsuario, String usuario, boolean vip) {
        this.id = idUsuario;
        this.usuario = usuario;
        this.vip = vip;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }


    public boolean isVip() {
        return vip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
