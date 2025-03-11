package BibliotecaAdmin.Ediciones;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Libro extends Edicion implements IAdquirible, Serializable {
    private String editorial;
    private String autor;
    private String isbn;
    private String descripcion;
    private GeneroEdicion genero;
    private int prestados;

    public Libro() {
    }

    public Libro(String id, String titulo, String editorial, String fechaPublicacion, String autor, String descripcion, String isbn, String imagen) {
        super(id, titulo, fechaPublicacion, imagen);
        this.editorial = editorial;
        this.autor = autor;
        this.isbn = isbn;
        this.descripcion = descripcion;
        genero = GeneroEdicion.OTROS;
    }

    @JsonCreator
    public Libro(@JsonProperty("id")String id, @JsonProperty("titulo")String titulo,
                 @JsonProperty("editorial")String editorial, @JsonProperty("fechaPublicacion")String fechaPublicacion,
                 @JsonProperty("autor")String autor, @JsonProperty("descripcion")String descripcion,
                 @JsonProperty("isbn")String isbn, @JsonProperty("stock")int stock,
                 @JsonProperty("precio")float precio, @JsonProperty("imagen")String imagen,
                 @JsonProperty("genero")GeneroEdicion genero) {
        super(id, titulo, fechaPublicacion, stock, precio, imagen);
        this.editorial = editorial;
        this.autor = autor;
        this.isbn = isbn;
        this.descripcion = descripcion;
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GeneroEdicion getGenero() {
        return genero;
    }

    public void setGenero(GeneroEdicion genero) {
        this.genero = genero;
    }

    public int getPrestados() {
        return prestados;
    }

    public void setPrestados(int prestados) {
        this.prestados = prestados;
    }

    @Override
    public String toString() {
        return "\nLibro" + '\n' +
                "ID: " + getId() + '\n' +
                "Titulo: " + getTitulo() + '\n' +
                "Autor(es): " + autor + '\n' +
                "Editorial: " + editorial + '\n' +
                "Fecha de publicacion: " + getFechaPublicacion() + '\n' +
                "Genero: " + getGenero().getGenero() + '\n' +
                "ISBN: " + isbn + '\n' +
                "Descripcion: " + descripcion + '\n' +
                "Stock: " + getStock() + '\n' +
                "Precio: $" + String.format("%.2f", getPrecio()) + '\n' + "\n";
    }

    @Override
    public boolean prestar() {
        int stock = getStock();
        if (getStock() > 0) {
            prestados++;
            setStock(stock - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver() {
        int stock = getStock();
        if (prestados > 0) {
            prestados--;
            setStock(stock + 1);
            return true;
        }
        return false;
    }
}
