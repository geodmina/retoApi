package com.geodmina.reto.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.geodmina.reto.dao.DosisDAO;
import com.geodmina.reto.dao.EmpleadoDAO;
import com.geodmina.reto.entidad.Dosis;
import com.geodmina.reto.entidad.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DosisREST {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private DosisDAO dosiDao;

    @GetMapping("/empleado/{empleadoId}/dosis")
    public ResponseEntity<List<Dosis>> getDosis(@PathVariable("empleadoId") int empleadoId) {

        List<Dosis> lstDosis = dosiDao.findByEmpleadoId(empleadoId);

        if (lstDosis.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lstDosis);
    }

    @PostMapping("/empleado/{empleadoId}/dosis")
    public ResponseEntity<Dosis> crearEmpleado(@Valid @PathVariable("empleadoId") int empleadoId,
            @RequestBody Dosis dosis) {

        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(empleadoId);

        if (!optionalEmpleado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        dosis.setEmpleado(optionalEmpleado.get());
        Dosis newDosis = dosiDao.save(dosis);
        return ResponseEntity.ok(newDosis);

    }

    @DeleteMapping("/empleado/{empleadoId}/dosis/{id}")
    public ResponseEntity<Void> borrarDosis(@PathVariable("empleadoId") int empleadoId, @PathVariable("id") int id) {

        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(empleadoId);

        if (!optionalEmpleado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        dosiDao.deleteById(id);
        return ResponseEntity.ok(null);
    }

}
