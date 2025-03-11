package BibliotecaAdmin.Excepciones;

public class StockNoDisponibleException extends Exception{
    public StockNoDisponibleException() {
    }

    public StockNoDisponibleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
