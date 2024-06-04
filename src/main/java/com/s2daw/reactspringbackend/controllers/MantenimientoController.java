package com.s2daw.reactspringbackend.controllers;

import com.s2daw.reactspringbackend.dao.MantenimientoDao;
import com.s2daw.reactspringbackend.models.Mantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
public class MantenimientoController {

    private final MantenimientoDao mantenimientoDao;

    @Autowired
    public MantenimientoController(MantenimientoDao mantenimientoDao) {
        this.mantenimientoDao = mantenimientoDao;
    }

    @GetMapping
    public List<Mantenimiento> obtenerMantenimientos() {
        return mantenimientoDao.getMantenimientos();
    }

    @PostMapping
    public void registrarMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        mantenimientoDao.registrar(mantenimiento);
    }

    @DeleteMapping("/{id}")
    public void eliminarMantenimiento(@PathVariable Long id) {
        mantenimientoDao.eliminar(id);
    }
}
