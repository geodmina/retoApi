package com.geodmina.reto.entidad;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.geodmina.reto.utils.EstadoVacuna;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cedula", nullable = false, length = 10, unique = true)
    @Pattern(regexp = "[0-9]{10,10}", message = "Cedula debe contener 10 números")
    @NotBlank(message = "Cedula es requerida")
    // @UniqueElements(message = "Cedula ya se encuentra registrada")
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "Nombres son requeridos")
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "Nombre solo puede contener letras")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @NotBlank(message = "Apeliidos son requeridos")
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "Apellido solo puede contener letras")
    private String apellido;

    @Column(name = "correo", nullable = false, length = 50)
    @Email(message = "No es un formato de email valido")
    @NotBlank(message = "Correo electronico es requerido")
    private String correo;

    /*
     * @OneToOne
     * 
     * @JoinColumn(name = "usuario_id", nullable = false)
     */
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fechanacimiento")
    private Date fechanacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "celular")
    private String celular;

    @Column(name = "estadovacuna")
    @Enumerated(value = EnumType.STRING)
    private EstadoVacuna estadovacuna;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empleado")
    private Set<Dosis> dosis = new HashSet<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public EstadoVacuna getEstadovacuna() {
        return this.estadovacuna;
    }

    public void setEstadovacuna(EstadoVacuna estadovacuna) {
        this.estadovacuna = estadovacuna;
    }

    public Date getFechaNacimiento() {
        return this.fechanacimiento;
    }

    public void setFechaNacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public EstadoVacuna getEstadoVacuna() {
        return this.estadovacuna;
    }

    public void setEstadoVacuna(EstadoVacuna estadovacuna) {
        this.estadovacuna = estadovacuna;
    }

    public Set<Dosis> getDosis() {
        return this.dosis;
    }

    public void setDosis(Set<Dosis> dosis) {
        this.dosis = dosis;
    }

}