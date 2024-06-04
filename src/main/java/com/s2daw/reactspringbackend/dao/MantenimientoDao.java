package com.s2daw.reactspringbackend.dao;

import com.s2daw.reactspringbackend.models.Mantenimiento;

import java.util.List;

public interface MantenimientoDao {
    List<Mantenimiento> getMantenimientos();
    void eliminar(Long id);
    void registrar(Mantenimiento mantenimiento);
}
