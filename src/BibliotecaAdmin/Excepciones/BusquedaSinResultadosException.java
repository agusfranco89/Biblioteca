package BibliotecaAdmin.Excepciones;

public class BusquedaSinResultadosException extends Exception{
    public BusquedaSinResultadosException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
