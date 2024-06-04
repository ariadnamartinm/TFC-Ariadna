package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.dao.MantenimientoDao;
import com.s2daw.reactspringbackend.models.Mantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
public class MantenimientoController {

    // Inyección de dependencias del MantenimientoDao
    private final MantenimientoDao mantenimientoDao;

    @Autowired
    // Constructor del controlador que recibe el MantenimientoDao
    public MantenimientoController(MantenimientoDao mantenimientoDao) {
        this.mantenimientoDao = mantenimientoDao;
    }

    @GetMapping
    // Endpoint para obtener todos los mantenimientos
    public List<Mantenimiento> obtenerMantenimientos() {
        return mantenimientoDao.getMantenimientos();
    }

    @PostMapping
    // Endpoint para registrar un nuevo mantenimiento
    public void registrarMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        mantenimientoDao.registrar(mantenimiento);
    }

    @DeleteMapping("/{id}")
    // Endpoint para eliminar un mantenimiento por su ID
    public void eliminarMantenimiento(@PathVariable Long id) {
        mantenimientoDao.eliminar(id);
    }
}