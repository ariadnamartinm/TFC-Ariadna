package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.dao.UsuarioDao;
import com.s2daw.reactspringbackend.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;


    @RequestMapping (value = "api/usuarios/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@PathVariable Long id ){
        Usuario usuario =new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("876543234");
        return usuario;
    }

    @RequestMapping (value = "api/usuarios", method = RequestMethod.GET)
    public List <Usuario> getUsuarios(){
        List <Usuario> usuarios = new ArrayList<>();
        return usuarioDao.getUsuarios();

    }

    @RequestMapping (value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        usuarioDao.registrar(usuario);
    }

    @RequestMapping (value = "usuario45")
    public Usuario editar(){
        Usuario usuario =new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("876543234");
        return usuario;
    }


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        usuarioDao.eliminar(id);
    }


    @RequestMapping (value = "usuario123")
    public Usuario buscar(){
        Usuario usuario =new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("876543234");
        return usuario;
    }



}
