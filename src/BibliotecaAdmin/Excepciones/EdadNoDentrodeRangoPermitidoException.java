package BibliotecaAdmin.Excepciones;

// Se invoca cuando la edad no esta dentro del rango deseado (13 a 100 años)

public class EdadNoDentrodeRangoPermitidoException extends Exception {

    public EdadNoDentrodeRangoPermitidoException(String message) {
        super(message);
    }

    public EdadNoDentrodeRangoPermitidoException() {
    }
}
