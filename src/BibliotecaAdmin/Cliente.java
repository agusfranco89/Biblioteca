package BibliotecaAdmin;

import BibliotecaAdmin.Ediciones.Edicion;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class Cliente extends Persona {
    private LinkedList<Edicion> historialCompras;

    public Cliente() {
        this.historialCompras = new LinkedList<>();
    }

    @JsonCreator
    public Cliente(@JsonProperty("idDni")String idDni, @JsonProperty("apellido")String apellido,
                   @JsonProperty("nombre")String nombre, @JsonProperty("edad")int edad,
                   @JsonProperty("tel")String tel, @JsonProperty("genero")GeneroPersona genero) {
        super(idDni, apellido, nombre, edad, tel, genero);
        historialCompras = new LinkedList<>();
    }

    public LinkedList<Edicion> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(LinkedList<Edicion> historialCompras) {
        this.historialCompras = historialCompras;
    }


    public String toString() {
        return "\nDNI: " + getIdDni() + '\n' +
                "Nombre Completo: " + getNombre() + " " +  getApellido()+ '\n' +
                "Edad: " + getEdad() + '\n' +
                "Telefono: " + getTel() + '\n' +
                "Genero: " + getGenero() + '\n' +
                "Tipo: " + "Cliente" + '\n' + "\n";
    }

    @Override
    public boolean esSocio() {
        return false;
    }
}
