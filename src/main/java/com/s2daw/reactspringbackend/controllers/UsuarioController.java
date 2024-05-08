package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class UsuarioController {
    @RequestMapping (value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id ){
        Usuario usuario =new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("876543234");
        return usuario;
    }

    @RequestMapping (value = "usuarios")
    public List <Usuario> getUsuarios(){
        List <Usuario> usuarios = new ArrayList<>();

        Usuario usuario =new Usuario();
        usuario.setId(234L);
        usuario.setNombre("Maria");
        usuario.setApellido("MARTIN");
        usuario.setEmail("maria@gmail.com");
        usuario.setTelefono("876543234");

        Usuario usuario2 =new Usuario();
        usuario2.setId(345L);
        usuario2.setNombre("Alfonso");
        usuario2.setApellido("Valiente");
        usuario2.setEmail("alfonso@gmail.com");
        usuario2.setTelefono("876543234");


        Usuario usuario3 =new Usuario();
        usuario3.setId(11L);
        usuario3.setNombre("Ariadna");
        usuario3.setApellido("Martin");
        usuario3.setEmail("Ariadna@gmail.com");
        usuario3.setTelefono("876543234");



        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        return usuarios;
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


    @RequestMapping (value = "usuario343")
    public Usuario eliminar(){
        Usuario usuario =new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("876543234");
        return usuario;
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
