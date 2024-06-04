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

    @Override
    public List<Mantenimiento> getMantenimientos() {
        String query = "FROM Mantenimiento";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Mantenimiento mantenimiento = entityManager.find(Mantenimiento.class, id);
        if (mantenimiento != null) {
            entityManager.remove(mantenimiento);
        }
    }

    @Override
    public void registrar(Mantenimiento mantenimiento) {
        entityManager.persist(mantenimiento);
    }
}
