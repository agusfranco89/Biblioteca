package BibliotecaAdmin;

import BibliotecaAdmin.Cobranzas.MetodosPago;
import BibliotecaAdmin.Ediciones.*;
import BibliotecaAdmin.Excepciones.BusquedaSinResultadosException;
import BibliotecaAdmin.Excepciones.StockNoDisponibleException;
import Utilidades.Utilidades;
import Utilidades.Validator;

import java.io.File;
import java.util.*;

public class BibliotecaAdmin {
    private LinkedHashSet<Edicion> ediciones;
    private TreeMap<String, Persona> miembros;
    private LinkedList<Venta> ventas;
    private LinkedList<Prestamo> prestamos;
    private String usuario;
    private String contrasenia;
    private Buscador buscador;

    public BibliotecaAdmin() {

        this.ediciones = new LinkedHashSet<>();
        this.miembros = new TreeMap<>();
        this.ventas = new LinkedList<>();
        this.prestamos = new LinkedList<>();
        this.usuario = "admin";
        this.contrasenia = "password";
        this.buscador = new Buscador();
    }


    public Boolean validaContrasenia(String contraseniaIngresada) {
        return (this.contrasenia.equals(contraseniaIngresada));

    }

    public LinkedHashSet<Edicion> getEdiciones() {
        return ediciones;
    }

    public void setEdiciones(LinkedHashSet<Edicion> ediciones) {
        this.ediciones = ediciones;
    }

    public TreeMap<String, Persona> getMiembros() {
        return miembros;
    }

    public void setMiembros(TreeMap<String, Persona> miembros) {
        this.miembros = miembros;
    }

    public LinkedList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(LinkedList<Venta> ventas) {
        this.ventas = ventas;
    }

    public LinkedList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(LinkedList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void addEdicion(Edicion edicionNueva) {
        ediciones.add(edicionNueva);
    }

    public void addMiembros(String key, Persona value){miembros.put(key,value);}

    public void addVentas(Venta ventaNueva) {
        ventas.add(ventaNueva);
    }

    public void addPrestamo(Prestamo alquilerNuevo) {
        prestamos.add(alquilerNuevo);
    }

    //-----------------------------------------------------------------

    public Persona generaSocio(Scanner sc) {
        String idDni, apellido, nombre, tel, direccion = null;
        int option, edad = 0;

        System.out.println("Ingrese del nuevo miembro: ");
        System.out.println("\tDatos socio: ");

        System.out.println("Dni: ");
        idDni = sc.nextLine();
        while (!(Validator.isNumeric(idDni))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Dni: ");
            idDni = sc.nextLine();
        }
        System.out.println(idDni);

        System.out.println("Apellido: ");
        apellido = sc.nextLine();
        while (!(Validator.isAlphabetic(apellido)) || !(Validator.isValidString(apellido))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Apellido: ");
            apellido = sc.nextLine();
        }

        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        while (!(Validator.isAlphabetic(nombre)) || !(Validator.isValidString(nombre))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Nombre: ");
            nombre = sc.nextLine();
        }

        System.out.println("Edad: ");
        while (!sc.hasNextInt()) {
            System.out.println("Dato erroneo. Ingrese nuevamente la Edad: ");
            sc.next();
        }
        edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Tel: ");
        tel = sc.nextLine();
        while (!(Validator.isNumeric(tel))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Tel: ");
            tel = sc.nextLine();
        }

        GeneroPersona genero = null;
        System.out.println("Genero: ");
        System.out.println("Opciones para seleccionar: ");
        System.out.println("1) Masculino");
        System.out.println("2) Femenino");
        System.out.println("3) Otro");

        System.out.println("Seleccione la opcion: ");
        while (!sc.hasNextInt()) {
            System.out.println("Opcion incorrecta. Seleccione nuevamente la opcion: ");
            sc.next(); // Descartar entrada incorrecta
        }

        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                genero = GeneroPersona.MASCULINO;
                break;
            case 2:
                genero = GeneroPersona.FEMENINO;
                break;
            case 3:
                genero = GeneroPersona.OTROS;
                break;
            default:
                System.out.println("Opcion incorrecta.");
                break;
        }

        System.out.println("Direccion: ");
        direccion = sc.nextLine();
        while (!(Validator.isValidAddress(direccion))) {
            System.out.println("Dato erroneo. Ingrese nuevamente la Direccion: ");
            direccion = sc.nextLine();
        }

        return new Socio(idDni, apellido, nombre, edad, tel, genero, direccion);
    }

    public void generaListSocio(Scanner sc) {
        char ctrol = 's';

        do {
            Persona socioNuevo = generaSocio(sc);
            System.out.println("**Mostrando el socio nuevo ingresado: ");
            System.out.println(socioNuevo);

            miembros.put(socioNuevo.getIdDni(), socioNuevo);

            System.out.println("Continua ingresando datos? (s/n)");
            ctrol = sc.next().charAt(0);
            sc.nextLine(); // Consumir el salto de línea
        } while (ctrol == 's' || ctrol == 'S');

    }

    public Persona generaCliente(Scanner sc) {
        String idDni, apellido, nombre, tel = null;
        int option, edad = 0;

        System.out.println("Ingrese del nuevo miembro: ");
        System.out.println("\tDatos cliente: ");

        System.out.println("Dni: ");
        idDni = sc.nextLine();
        while (!(Validator.isNumeric(idDni))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Dni: ");
            idDni = sc.nextLine();
        }

        System.out.println("Apellido: ");
        apellido = sc.nextLine();
        while (!(Validator.isAlphabetic(apellido)) || !(Validator.isValidString(apellido))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Apellido: ");
            apellido = sc.nextLine();
        }

        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        while (!(Validator.isAlphabetic(nombre)) || !(Validator.isValidString(nombre))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Nombre: ");
            nombre = sc.nextLine();
        }

        System.out.println("Edad: ");
        while (!sc.hasNextInt()) {
            System.out.println("Dato erroneo. Ingrese nuevamente la Edad: ");
            sc.next();
        }
        edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Tel: ");
        tel = sc.nextLine();
        while (!(Validator.isNumeric(tel))) {
            System.out.println("Dato erroneo. Ingrese nuevamente el Tel: ");
            tel = sc.nextLine();
        }

        GeneroPersona genero = null;
        System.out.println("Genero: ");
        System.out.println("Opciones para seleccionar: ");
        System.out.println("1) Masculino");
        System.out.println("2) Femenino");
        System.out.println("3) Otro");

        System.out.println("Seleccione la opcion: ");
        while (!sc.hasNextInt()) {
            System.out.println("Opcion incorrecta. Seleccione nuevamente la opcion: ");
            sc.next(); // Descartar entrada incorrecta
        }

        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                genero = GeneroPersona.MASCULINO;
                break;
            case 2:
                genero = GeneroPersona.FEMENINO;
                break;
            case 3:
                genero = GeneroPersona.OTROS;
                break;
            default:
                System.out.println("Opcion incorrecta.");
                break;
        }

        return new Cliente(idDni, apellido, nombre, edad, tel, genero);
    }

    public void generaListCliente(Scanner sc) {
        char ctrol = 's';

        do {
            Persona clienteNuevo = generaCliente(sc);
            System.out.println("**Mostrando el cliente nuevo ingresado: ");
            System.out.println(clienteNuevo);

            miembros.put(clienteNuevo.getIdDni(), clienteNuevo);

            System.out.println("Continua ingresando datos? (s/n)");
            ctrol = sc.next().charAt(0);
            sc.nextLine(); // Consumir el salto de línea
        } while (ctrol == 's' || ctrol == 'S');
    }

    public boolean ifMiembroExist(String dni) {
        boolean rta = false;

        if (miembros.containsKey(dni)) {
            System.out.println("La persona es: " + miembros.getClass());
            rta = true;
        } else {
            System.out.println("Persona inexistente.");
        }

        return rta;
    }

    public void recorrelistMiembros() {
        for (Persona prs : miembros.values()) {
            System.out.println("\tId Persona: " + prs.getIdDni() + "\nDetalle completo: " + prs.toString());
        }
    }

    public void eliminaPersona(Scanner sc, String dni) {
        System.out.println("Ingrese el dni de la persona a eliminar: ");
        dni = sc.nextLine();
        while (!(Validator.isNumeric(dni))) {
            System.out.println("Dato erroneo.");
        }

        if (ifMiembroExist(dni)) {
            Persona remove = miembros.remove(dni);
        } else {
            System.out.println("Persona inexistente");
        }
    }

    public void recorreListSocios() {
        TreeMap<String, Persona> miembros = getMiembros();

        System.out.println("**Mostrando listado de Socios: ");
        for (Map.Entry<String, Persona> entry : miembros.entrySet()) {
            Persona miembro = entry.getValue();
            if (miembro.esSocio()) {
                System.out.println("Socio DNI: " + miembro.getIdDni() + ", " + "Info Socio: " + miembro);
            }
        }
    }

    public void recorreListClientes() {
        TreeMap<String, Persona> miembros = getMiembros();

        System.out.println("**Mostrando listado de Clientes: ");
        for (Map.Entry<String, Persona> entry : miembros.entrySet()) {
            Persona miembro = entry.getValue();
            if (!miembro.esSocio()) {
                System.out.println("Cliente DNI: " + miembro.getIdDni() + ", " + "Info Cliente: " + miembro);
            }
        }
    }

    public Boolean generaVenta(ArrayList<Edicion> aVender, Persona persona, MetodosPago mpago) {
        try
        {
            validarStock(aVender);

            Venta v = new Venta(persona.getIdDni(), aVender, mpago);

            for (Edicion e : aVender) {
                e.vender();
            }

            this.ventas.add(v);

            if (persona.esSocio()) {
                ((Socio) persona).addHistorialCompras(v);
            }

            return true;
        } catch (StockNoDisponibleException snde)
        {
            System.out.println("La venta no se ha generado pues uno o mas de los libros no poseen stock");
        }



        return false;
    }

    public Boolean generaPrestamo(Libro aPrestar, Socio s) {
        if (aPrestar.getStock() > 0) {
            if (s.getAlquilados() < s.getLIMITE_ALQUILER()) {
                Prestamo p = new Prestamo(s.getIdDni(), aPrestar);
                s.addHistorialPrestamos(p);
                aPrestar.prestar();
                this.prestamos.add(p);
            } else {
                System.out.println("El socio ha alcanzado el limite de prestamos");
                return false;
            }

        } else {
            System.out.println("No se puede prestar el libro pues no se dispone de stock");
            return false;
        }

        return true;
    }

    public boolean generaDevolucion(Prestamo p)
    {
        p.setFechaDevolucion(new Date());
        Libro l = p.getLibro();
        boolean isDevuelto = l.devolver();

        return isDevuelto;
    }



    /// Recibe un arraylist de ediciones y verifica que TODAS tengan stock. Si es cierto, devuelve true
    public void validarStock (ArrayList<Edicion> aVender) throws StockNoDisponibleException
    {

        for (Edicion e: aVender)
        {
            if (e.getStock()==0)
            {
                throw new StockNoDisponibleException("Stock no disponible");
            }
        }
    }

    public Edicion buscarEdicion (String titulo) ///Busca una edicion entre las cargadas
    {
        for (Edicion e: ediciones)
        {
            if (e.getTitulo().equals(titulo))
            {
                return e;
            }
        }
        return null;
    }

    public Persona buscarMiembro(String dni)
    {
        return miembros.get(dni);
    }

    public ArrayList<Edicion> buscarAutor (String autor)
    {
        ArrayList<Edicion> resultados = new ArrayList<>();

        for(Edicion dato: ediciones)
        {
            if (dato instanceof Libro)
            {
                if (((Libro) dato).getAutor().equals(autor))
                {
                    resultados.add(dato);
                }
            }
        }
        return resultados;
    }

    public ArrayList<Venta> buscarVentasPorDNI (String dni) ///Busca todas las ventas de determinado cliente/socio
    {
        ArrayList<Venta> resultados = new ArrayList<>();

        for(Venta v: ventas)
        {
            if (v.getDniPersona().equals(dni))
            {
                resultados.add(v);
            }
        }
        return resultados;
    }

    public ArrayList<Prestamo> buscarPrestamosPorDNI (String dni) ///Busca todas las ventas de determinado cliente/socio
    {
        ArrayList<Prestamo> resultados = new ArrayList<>();

        for(Prestamo p: prestamos)
        {
            if (p.getDniSocio().equals(dni))
            {
                resultados.add(p);
            }
        }
        return resultados;
    }

    //-----------------------------------------------------------------

    public void agregarLibro(int indice, GeneroEdicion genero, int stock, float precio) {
        try {
            Libro libro = (Libro)buscador.buscarPorIndiceResultados(indice);
            if (libro != null) {
                libro.setGenero(genero);
                libro.setStock(stock);
                libro.setPrecio(precio);
                ediciones.add(libro);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println(indexOutOfBoundsException.getMessage());
        }
        catch (ClassCastException classCastException) {
            System.out.println("La edicion elegida no es un libro");
        }
    }

    public void agregarRevista(int indice, int stock, float precio) {
        try {
            Revista revista = (Revista) buscador.buscarPorIndiceResultados(indice);
            if (revista != null) {
                revista.setStock(stock);
                revista.setPrecio(precio);
                ediciones.add(revista);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println(indexOutOfBoundsException.getMessage());
        }
        catch (ClassCastException classCastException) {
            System.out.println("La edicion elegida no es una revista");
        }
    }

    public String buscarLibro(String titulo) throws BusquedaSinResultadosException {
        return buscador.buscarLibro(titulo);
    }

    public String buscarLibro(String titulo, String autor) throws BusquedaSinResultadosException {
        return buscador.buscarLibro(titulo, autor);
    }

    public String buscarRevista(String titulo) throws BusquedaSinResultadosException{
        return buscador.buscarRevista(titulo);
    }

    public String listarEdiciones() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edicion e : ediciones) {
            stringBuilder.append(e);
        }
        return stringBuilder.toString();
    }

    public String listarLibros() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edicion e : ediciones) {
            if (e instanceof Libro) {
                stringBuilder.append(e);
            }
        }
        return stringBuilder.toString();
    }

    public String listarRevistas() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edicion e : ediciones) {
            if (e instanceof Revista) {
                stringBuilder.append(e);
            }
        }
        return stringBuilder.toString();
    }

    public int cantidadResultados() {
        return buscador.cantidad();
    }

    public Edicion buscarPorID(String id) throws BusquedaSinResultadosException{
        boolean encontrado = false;
        Edicion edicion = null;
        Iterator<Edicion> iterator = ediciones.iterator();
        while (!encontrado && iterator.hasNext()) {
            edicion = iterator.next();
            if (id.equals(edicion.getId())) {
                encontrado = true;
            }
        }
        if (encontrado) {
            return edicion;
        }
        else throw new BusquedaSinResultadosException("No se ha encontrado ninguna edicion con la ID ingresada");
    }

    public boolean contieneID(String id)
    {
        boolean contiene = false;
        for (Edicion ed: ediciones)
        {
            if (ed.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }
}