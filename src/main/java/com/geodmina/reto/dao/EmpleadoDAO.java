package com.geodmina.reto.dao;

import com.geodmina.reto.entidad.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDAO extends JpaRepository<Empleado, Integer> {
 

    
}
