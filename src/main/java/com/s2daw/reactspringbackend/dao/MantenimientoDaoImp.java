package com.s2daw.reactspringbackend.dao;

import com.s2daw.reactspringbackend.models.Mantenimiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MantenimientoDaoImp implements MantenimientoDao {

    @PersistenceContext
    EntityManager entityManager;

    // Método para obtener la lista de mantenimientos
    @Override
    public List<Mantenimiento> getMantenimientos() {
        String query = "FROM Mantenimiento";
        return entityManager.createQuery(query).getResultList();
    }

    // Método para eliminar un mantenimiento por id
    @Override
    public void eliminar(Long id) {
        Mantenimiento mantenimiento = entityManager.find(Mantenimiento.class, id);
        if (mantenimiento != null) {
            entityManager.remove(mantenimiento);
        }
    }

    // Método para registrar un nuevo mantenimiento
    @Override
    public void registrar(Mantenimiento mantenimiento) {
        entityManager.persist(mantenimiento);
    }
}
