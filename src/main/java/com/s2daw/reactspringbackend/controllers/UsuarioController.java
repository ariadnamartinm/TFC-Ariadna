package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.dao.UsuarioDao;
import com.s2daw.reactspringbackend.models.Usuario;
import com.s2daw.reactspringbackend.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestHeader(value = "Authorization") String token) {
        if (!validarToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Usuario> usuarios = usuarioDao.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    private boolean validarToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }

        try {
            String jwt = token.replace("Bearer ", "").trim();
            String usuarioId = jwtUtil.getKey(jwt);
            return usuarioId != null;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Void> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
            usuario.setPassword(hash);
            usuarioDao.registrar(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if (!validarToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            usuarioDao.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
