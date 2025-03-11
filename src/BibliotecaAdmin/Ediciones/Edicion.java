package BibliotecaAdmin.Ediciones;

import java.io.Serializable;

import BibliotecaAdmin.Persona;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
property = "type")

@JsonSubTypes({
        @Type(value = Libro.class),
        @Type(value = Revista.class)
})

public abstract class Edicion implements IVendible, Serializable{
    private String id;
    private String titulo;
    private String fechaPublicacion;
    private int stock;
    private float precio;
    private String imagen;

    public Edicion() {
    }

    public Edicion(String id, String titulo, String fechaPublicacion, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.imagen = imagen;
    }

    public Edicion(String id, String titulo, String fechaPublicacion, int stock, float precio, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.stock = stock;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean vender() {
        if (stock > 0) {
            stock--;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edicion) {
            return id.equals(((Edicion) obj).getId());
        }
        return false;
    }
}
