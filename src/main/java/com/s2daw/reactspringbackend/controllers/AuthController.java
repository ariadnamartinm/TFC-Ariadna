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
            // Obtenemos el usuario mediante las credenciales proporcionadas
            Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
            // Comprobamos si el usuario obtenido no es nulo
            if (usuarioLogueado != null) {
                // Creamos un token JWT con el id del usuario y su email
                String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
                // Retornamos un ResponseEntity con el token JWT
                return ResponseEntity.ok(tokenJwt);
            }
            // Si el usuario es nulo, se retornará un ResponseEntity con un mensaje de credenciales incorrectas y estado UNAUTHORIZED (401)
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // En caso de error, se imprimirá el stack trace y se retornará un ResponseEntity con un mensaje de error en el servidor y estado INTERNAL_SERVER_ERROR (500)
            e.printStackTrace();
            return new ResponseEntity<>("Error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}