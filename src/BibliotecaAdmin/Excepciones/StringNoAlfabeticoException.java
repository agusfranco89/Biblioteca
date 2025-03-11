package BibliotecaAdmin.Excepciones;

public class StringNoAlfabeticoException extends Exception{

    public StringNoAlfabeticoException() {
    }

    public StringNoAlfabeticoException(String message) {
        super(message);
    }
}
