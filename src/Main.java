import BibliotecaAdmin.BibliotecaAdmin;
import BibliotecaAdmin.GeneroPersona;
import Utilidades.Utilidades;
import BibliotecaAdmin.Socio;
import BibliotecaAdmin.Cliente;

import java.util.ArrayList;
import java.util.List;


public class    Main {
    public static void main(String[] args) {


        // Si existe el json de biblioteca, lo lee. Sino crea una nueva.
        BibliotecaAdmin b = Utilidades.leerBiblioteca();

        //cargarJson(b);

        // Se lanza el menu de inicio de sesion.
        GUI.logeo(b);
    }

    public static void cargarJson(BibliotecaAdmin b) //Carga socios y clientes para testeo
    {
        Socio s = new Socio("47068044","Mango","Eduardo",26,"2235320089", GeneroPersona.MASCULINO,"Pigue 1921");
        b.addMiembros(s.getIdDni(),s);
        List<Socio> socios = new ArrayList<>();

        // Instanciar 25 objetos Socio
        socios.add(new Socio("47068001", "Gomez", "Juan", 30, "2235320001", GeneroPersona.MASCULINO, "Calle 1"));
        socios.add(new Socio("47068002", "Perez", "Maria", 28, "2235320002", GeneroPersona.FEMENINO, "Calle 2"));
        socios.add(new Socio("47068003", "Lopez", "Carlos", 35, "2235320003", GeneroPersona.MASCULINO, "Calle 3"));
        socios.add(new Socio("47068004", "Sanchez", "Ana", 24, "2235320004", GeneroPersona.FEMENINO, "Calle 4"));
        socios.add(new Socio("47068005", "Martinez", "Luis", 40, "2235320005", GeneroPersona.MASCULINO, "Calle 5"));
        socios.add(new Socio("47068006", "Garcia", "Sofia", 32, "2235320006", GeneroPersona.FEMENINO, "Calle 6"));
        socios.add(new Socio("47068007", "Rodriguez", "Miguel", 27, "2235320007", GeneroPersona.MASCULINO, "Calle 7"));
        socios.add(new Socio("47068008", "Fernandez", "Laura", 29, "2235320008", GeneroPersona.FEMENINO, "Calle 8"));
        socios.add(new Socio("47068009", "Hernandez", "Jorge", 31, "2235320009", GeneroPersona.MASCULINO, "Calle 9"));
        socios.add(new Socio("47068010", "Ruiz", "Lucia", 26, "2235320010", GeneroPersona.FEMENINO, "Calle 10"));
        socios.add(new Socio("47068011", "Diaz", "Diego", 33, "2235320011", GeneroPersona.MASCULINO, "Calle 11"));
        socios.add(new Socio("47068012", "Torres", "Carla", 25, "2235320012", GeneroPersona.FEMENINO, "Calle 12"));
        socios.add(new Socio("47068013", "Ramirez", "Fernando", 36, "2235320013", GeneroPersona.MASCULINO, "Calle 13"));
        socios.add(new Socio("47068014", "Flores", "Elena", 34, "2235320014", GeneroPersona.FEMENINO, "Calle 14"));
        socios.add(new Socio("47068015", "Gonzalez", "Roberto", 28, "2235320015", GeneroPersona.MASCULINO, "Calle 15"));
        socios.add(new Socio("47068016", "Alvarez", "Paula", 30, "2235320016", GeneroPersona.FEMENINO, "Calle 16"));
        socios.add(new Socio("47068017", "Gutierrez", "Raul", 27, "2235320017", GeneroPersona.MASCULINO, "Calle 17"));
        socios.add(new Socio("47068018", "Mendoza", "Clara", 29, "2235320018", GeneroPersona.FEMENINO, "Calle 18"));
        socios.add(new Socio("47068019", "Castro", "Martin", 31, "2235320019", GeneroPersona.MASCULINO, "Calle 19"));
        socios.add(new Socio("47068020", "Vega", "Laura", 26, "2235320020", GeneroPersona.FEMENINO, "Calle 20"));
        socios.add(new Socio("47068021", "Ramos", "Antonio", 33, "2235320021", GeneroPersona.MASCULINO, "Calle 21"));
        socios.add(new Socio("47068022", "Sosa", "Natalia", 25, "2235320022", GeneroPersona.FEMENINO, "Calle 22"));
        socios.add(new Socio("47068023", "Cruz", "Pedro", 36, "2235320023", GeneroPersona.MASCULINO, "Calle 23"));
        socios.add(new Socio("47068024", "Morales", "Mariana", 34, "2235320024", GeneroPersona.FEMENINO, "Calle 24"));
        socios.add(new Socio("47068025", "Ortiz", "Alberto", 28, "2235320025", GeneroPersona.MASCULINO, "Calle 25"));

        // Imprimir los datos de los socios para verificar
        for (Socio socio : socios) {
            b.addMiembros(socio.getIdDni(),socio);
        }

        Cliente c = new Cliente("17178809","Cabrera","Myriam",59,"2236886740",GeneroPersona.FEMENINO);
        b.addMiembros(c.getIdDni(),c);

        List<Cliente> clientes = new ArrayList<>();

        // Instanciar 25 objetos Cliente
        clientes.add(new Cliente("17178802", "Martinez", "Juan", 45, "2236886741", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178803", "Lopez", "Ana", 37, "2236886742", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178804", "Gomez", "Luis", 28, "2236886743", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178805", "Sanchez", "Maria", 32, "2236886744", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178806", "Ramirez", "Carlos", 42, "2236886745", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178807", "Torres", "Laura", 34, "2236886746", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178808", "Diaz", "Jorge", 50, "2236886747", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178810", "Mendoza", "Clara", 27, "2236886748", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178811", "Vega", "Roberto", 38, "2236886749", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178812", "Alvarez", "Paula", 26, "2236886750", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178813", "Gutierrez", "Ramon", 46, "2236886751", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178814", "Morales", "Mariana", 54, "2236886752", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178815", "Ortiz", "Fernando", 39, "2236886753", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178816", "Rojas", "Sandra", 35, "2236886754", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178817", "Herrera", "Diego", 48, "2236886755", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178818", "Marquez", "Lucia", 29, "2236886756", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178819", "Iglesias", "Martin", 31, "2236886757", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178820", "Sosa", "Natalia", 52, "2236886758", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178821", "Ramos", "Antonio", 40, "2236886759", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178822", "Cruz", "Patricia", 33, "2236886760", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178823", "Vasquez", "Pedro", 47, "2236886761", GeneroPersona.MASCULINO));
        clientes.add(new Cliente("17178824", "Silva", "Elena", 41, "2236886762", GeneroPersona.FEMENINO));
        clientes.add(new Cliente("17178825", "Castro", "Julian", 36, "2236886763", GeneroPersona.MASCULINO));

        // Imprimir los datos de los clientes para verificar
        for (Cliente cliente : clientes) {
            b.addMiembros(cliente.getIdDni(),cliente);
        }
    }

}