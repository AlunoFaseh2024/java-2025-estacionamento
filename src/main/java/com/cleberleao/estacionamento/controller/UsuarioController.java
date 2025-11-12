package com.cleberleao.estacionamento.controller;

import com.cleberleao.estacionamento.entity.Usuario;
import com.cleberleao.estacionamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.registrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
