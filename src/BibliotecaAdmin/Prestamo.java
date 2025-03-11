package BibliotecaAdmin;

import BibliotecaAdmin.Ediciones.Libro;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Prestamo implements Serializable {
    private String dniSocio;
    private Date fechaInicio;
    private Date fechaDevolucion;
    private Libro libro;


    public Prestamo() {
        this.dniSocio = null;
        this.fechaInicio = null;
        this.fechaDevolucion = null;
        this.libro = null;
    }
    public Prestamo(String dniSocio, Libro libro) {
        this.dniSocio = dniSocio;
        this.fechaInicio = new Date();
        this.fechaDevolucion = null;
        this.libro = libro;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\nPrestamo: " + '\n' +
                "Fecha de Prestamo: " + getFechaInicio() + '\n' +
                "Fecha de Devolucion: " + getFechaDevolucion() + '\n' +
                "DNI Comprador: " + getDniSocio() + '\n' +
                "Libro prestado: " + libro.toString() + '\n' +
                "----------------------------------------------------------------" + '\n' + '\n' ;
    }
}
