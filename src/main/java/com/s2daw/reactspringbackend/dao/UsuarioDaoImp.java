package com.s2daw.reactspringbackend.dao;

import com.s2daw.reactspringbackend.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    // Método para obtener todos los usuarios de la base de datos
    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    // Método para eliminar un usuario por su ID
    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    // Método para registrar un nuevo usuario en la base de datos
    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    // Método para obtener un usuario por sus credenciales (email y contraseña)
    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        // Verificar la contraseña utilizando Argon2 para hashing seguro
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
