package BibliotecaAdmin;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"LIMITE_ALQUILER"})

public class Socio extends Persona implements Serializable {
    private List<Venta> historialCompras;
    private List <Prestamo> historialAlquilados;
    private String direccion;
    private int alquilados;
    @JsonIgnore
    private int limite_ALQUILER;
    private transient final int LIMITE_ALQUILER= 3;



    public Socio() {
        super();
        this.historialCompras = new ArrayList<>();
        this.historialAlquilados = new ArrayList<>();
        this.direccion = null;
        this.alquilados = 0;
    }

    @JsonCreator
    public Socio(@JsonProperty("idDni")String idDni, @JsonProperty("apellido")String apellido,
                 @JsonProperty("nombre")String nombre, @JsonProperty("edad")int edad,
                 @JsonProperty("tel")String tel, @JsonProperty("genero")GeneroPersona genero,
                 @JsonProperty("direccion")String direccion) {
        super(idDni, apellido, nombre, edad, tel, genero);
        this.direccion = direccion;
        this.historialAlquilados = new ArrayList<>();
        this.historialCompras = new ArrayList<>();
    }

    public List <Venta> getHistorialCompras() {
        return historialCompras;
    }

    public void addHistorialCompras(Venta v) {
        this.historialCompras.add(v);
    }
    public void addHistorialPrestamos(Prestamo p) {
        alquilados++;
        this.historialAlquilados.add(p);
    }

    public int getLIMITE_ALQUILER() {
        return LIMITE_ALQUILER;
    }

    public List<Prestamo> getHistorialAlquilados() {
        return historialAlquilados;
    }

    public void setHistorialAlquilados(List<Prestamo> historialAlquilados) {
        this.historialAlquilados = historialAlquilados;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAlquilados() {
        return alquilados;
    }

    public void setAlquilados(int alquilados) {
        this.alquilados = alquilados;
    }

    @Override
    public String toString() {
        return "\nDNI: " + getIdDni() + '\n' +
                "Nombre Completo: " + getNombre() + " " +  getApellido()+ '\n' +
                "Edad: " + getEdad() + '\n' +
                "Telefono: " + getTel() + '\n' +
                "Genero: " + getGenero() + '\n' +
                "Direccion: " + direccion + '\n' +
                "Tipo: " + "Socio" + '\n' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Socio socio)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(direccion, socio.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direccion);
    }

    @Override
    public boolean esSocio() {
        return true;
    }
}
