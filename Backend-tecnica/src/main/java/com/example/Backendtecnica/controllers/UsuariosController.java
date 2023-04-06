package com.example.Backendtecnica.controllers;

import com.example.Backendtecnica.entities.UsuarioGenerate;
import com.example.Backendtecnica.repository.UsuariosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuariosController {

    private UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping("/login")
    public ResponseEntity<UsuarioGenerate> login(@RequestParam String nombre){

        UsuarioGenerate usuario = usuariosRepository.findByUsuario(nombre);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/generarUsuario")
    public ResponseEntity<UsuarioGenerate> generarUsuario(@RequestBody UsuarioGenerate usuario){
        UsuarioGenerate usuarioGenerado = usuariosRepository.save(usuario);
        return ResponseEntity.ok(usuarioGenerado);
    }
}
