package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.dao.UsuarioDao;
import com.s2daw.reactspringbackend.models.Usuario;
import com.s2daw.reactspringbackend.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
            if (usuarioLogueado != null) {
                String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
                return ResponseEntity.ok(tokenJwt);
            }
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
