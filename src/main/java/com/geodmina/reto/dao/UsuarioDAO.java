package com.geodmina.reto.dao;

import com.geodmina.reto.entidad.Empleado;
import com.geodmina.reto.entidad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    Empleado findByUsuario(int usuario);

}
