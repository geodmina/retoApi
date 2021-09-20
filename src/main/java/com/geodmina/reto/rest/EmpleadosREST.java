package com.geodmina.reto.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.geodmina.reto.dao.EmpleadoDAO;
import com.geodmina.reto.dao.UsuarioDAO;
import com.geodmina.reto.entidad.Empleado;
import com.geodmina.reto.entidad.Usuario;
import com.geodmina.reto.utils.Funciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleado")
public class EmpleadosREST {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {

        List<Empleado> lstEmpleados = empleadoDAO.findAll();

        if (lstEmpleados.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lstEmpleados);
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable("id") int id) {

        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(id);

        if (optionalEmpleado.isPresent()) {
            return ResponseEntity.ok(optionalEmpleado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearEmpleado(@Valid @RequestBody Empleado empleado) {

        Funciones funciones = new Funciones();
        
        String strUsuario = funciones.generateUsername(empleado);
        String password = funciones.generateRandomPassword(10);
        Usuario usuario = new Usuario();
        usuario.setUsuario(strUsuario);
        usuario.setClave(password);

        usuario.setEmpleado(empleado);
        empleado.setUsuario(usuario);

        Usuario newUsuario = usuarioDAO.save(usuario);
        
/*         empleado.setUsuario(newUsuario);
        Empleado newEmpleado = empleadoDAO.save(empleado); */

        return ResponseEntity.ok(newUsuario);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> borrarEmpleado(@PathVariable("id") int id) {
        empleadoDAO.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    public ResponseEntity<Empleado> modificarEmpleado(@RequestBody Empleado empleado) {

        Optional<Empleado> optionalEmpleado = empleadoDAO.findById(empleado.getId());

        if (optionalEmpleado.isPresent()) {

            Empleado updateEmpleado = optionalEmpleado.get();

            updateEmpleado.setDireccion(empleado.getDireccion());
            updateEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
            updateEmpleado.setEstadoVacuna(empleado.getEstadoVacuna());
            updateEmpleado.setCelular(empleado.getCelular());

            Empleado saveEmpleado = empleadoDAO.save(updateEmpleado);

            return ResponseEntity.ok(saveEmpleado);
        }

        return ResponseEntity.notFound().build();
    }

}
