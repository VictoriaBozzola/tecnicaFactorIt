package com.example.Backendtecnica.entities;

import javax.persistence.*;

import javax.persistence.*;


public class UsuarioOutput {
    private Long idUsuario;
    private boolean vip;

    public UsuarioOutput() {
    }

    public UsuarioOutput(Long idUsuario, boolean vip) {
        this.idUsuario = idUsuario;
        this.vip = vip;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public boolean isVip() {
        return vip;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
