package BibliotecaAdmin;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
        property = "type")

@JsonSubTypes({
        @Type(value = Cliente.class),
        @Type(value = Socio.class)
})

public abstract class Persona implements Comparable <Persona> {
    private String idDni;
    private String apellido;
    private String nombre;
    private int edad;
    private String tel;
    private GeneroPersona genero;
    private boolean activo;

    public Persona() {
    }

    public Persona(String idDni, String apellido, String nombre, int edad, String tel, GeneroPersona genero) {
        this.idDni = idDni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.tel = tel;
        this.genero = genero;
        this.activo=true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdDni() {
        return idDni;
    }

    public void setIdDni(String idDni) {
        this.idDni = idDni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public GeneroPersona getGenero() {
        return genero;
    }

    public void setGenero(GeneroPersona genero) {
        this.genero = genero;
    }

    public abstract boolean esSocio();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return edad == persona.edad && Objects.equals(idDni, persona.idDni) && Objects.equals(apellido, persona.apellido) && Objects.equals(nombre, persona.nombre) && Objects.equals(tel, persona.tel) && genero == persona.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDni, apellido, nombre, edad, tel, genero);
    }

    @Override
    public int compareTo(Persona o) {
        return this.idDni.compareTo(o.getIdDni());
    }
}
