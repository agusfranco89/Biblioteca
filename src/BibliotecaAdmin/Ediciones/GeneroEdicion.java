package BibliotecaAdmin.Ediciones;

import java.io.Serializable;

public enum GeneroEdicion implements Serializable {
    OTROS("otros"), POESIA("poesia"), CIENCIA_FICCION("ciencia ficcion"), FANTASIA("fantasia"),
    INFANTIL("infantil"), ADULTO("adulto"), TERROR("terror"), SUSPENSO("suspenso"), POLITICA("politica"),
    POLICIAL("policial"), BIBLIOGRAFIA("bibliografia"), ENCICLOPEDIA("enciclopedia"), COMEDIA("comedia"),
    ARTE("arte");

    private final String genero;

    GeneroEdicion(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
