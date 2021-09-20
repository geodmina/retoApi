package com.geodmina.reto.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.geodmina.reto.utils.TipoVacuna;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dosis")
public class Dosis {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fechavacuna", nullable = false)
    @NotNull(message = "Fecha de vacuna es requerido")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-5")
    private Date fechavacuna;

    @Column(name = "numerodosis", nullable = false)
    @NotNull(message = "Numero de dosis es requerido")
    private int numerodosis;

    @Column(name = "tipovacuna", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoVacuna tipovacuna;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechavacuna() {
        return this.fechavacuna;
    }

    public void setFechavacuna(Date fechavacuna) {
        this.fechavacuna = fechavacuna;
    }

    public int getNumerodosis() {
        return this.numerodosis;
    }

    public void setNumerodosis(int numerodosis) {
        this.numerodosis = numerodosis;
    }

    public TipoVacuna getTipovacuna() {
        return this.tipovacuna;
    }

    public void setTipovacuna(TipoVacuna tipovacuna) {
        this.tipovacuna = tipovacuna;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
