package com.s2daw.reactspringbackend.dao;

import com.s2daw.reactspringbackend.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario>getUsuarios();


    void eliminar(Long id);

    void registrar(Usuario usuario);

    boolean verificarCredenciales(Usuario usuario);
}
