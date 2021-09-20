package com.geodmina.reto.dao;

import java.util.List;

import com.geodmina.reto.entidad.Dosis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DosisDAO extends JpaRepository<Dosis, Integer> {
    
    List<Dosis> findByEmpleadoId(int empleado);

}
