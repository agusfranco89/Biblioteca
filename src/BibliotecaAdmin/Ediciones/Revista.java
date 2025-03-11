package BibliotecaAdmin.Ediciones;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Revista extends Edicion{
    private boolean edicionDigital;

    public Revista() {
    }

    public Revista(String id, String titulo, String fechaPublicacion, boolean edicionDigital, String imagen) {
        super(id, titulo, fechaPublicacion, imagen);
        this.edicionDigital = edicionDigital;
    }

    @JsonCreator
    public Revista(@JsonProperty("id")String id, @JsonProperty("titulo")String titulo,
                   @JsonProperty("fechaPublicacion")String fechaPublicacion, @JsonProperty("stock")int stock,
                   @JsonProperty("precio")float precio, @JsonProperty("edicionDigital")boolean edicionDigital,
                   @JsonProperty("imagen")String imagen) {
        super(id, titulo, fechaPublicacion, stock, precio, imagen);
        this.edicionDigital = edicionDigital;
    }

    public boolean isEdicionDigital() {
        return edicionDigital;
    }

    public void setEdicionDigital(boolean edicionDigital) {
        this.edicionDigital = edicionDigital;
    }

    private String isEdicionDigitalString() {
        if (isEdicionDigital()) return "si";
        else return "no";
    }

    @Override
    public String toString() {
        return "\nRevista" + '\n' +
                "ID: " + getId() + '\n' +
                "Titulo: " + getTitulo() + '\n' +
                "Fecha de publicacion: " + getFechaPublicacion() + '\n' +
                "Edicion digital: " + isEdicionDigitalString() + '\n' +
                "Stock: " + getStock() + '\n' +
                "Precio: $" + String.format("%.2f", getPrecio()) + '\n' + '\n';
    }
}
