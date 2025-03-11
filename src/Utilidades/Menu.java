package Utilidades;

import BibliotecaAdmin.BibliotecaAdmin;
import BibliotecaAdmin.Excepciones.BusquedaSinResultadosException;
import BibliotecaAdmin.Socio;
import BibliotecaAdmin.Cliente;
import BibliotecaAdmin.Persona;
import BibliotecaAdmin.Ediciones.*;

import java.util.Scanner;

public class Menu {
    private BibliotecaAdmin bibliotecaAdmin;

    public Menu() {
        bibliotecaAdmin = Utilidades.leerBiblioteca();
    }

    //-----------------------------------------------------------------

    public int opcionesMenuLibro(Scanner sc){
        int option;

        System.out.println("\tIngreso al menu de la biblioteca: ");
        System.out.println("**Menu Libros: ");
        System.out.println("\tSeleccione la operacion a realizar: ");
        System.out.println("1: Alta libros");
        System.out.println("2: Modificando libros");
        System.out.println("3: Historial libros");
        System.out.println("4: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 4");
        option = sc.nextInt();
        sc.nextLine();

        while (option < 1 || option > 4) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            option = sc.nextInt();
            sc.nextLine();
        }
        return option;
    }



    public void menuSwitchLibro(Scanner sc){
        boolean exit = false;

        while (!exit) {
            int option = opcionesMenuLibro(sc);
            switch (option) {
                case 1:
                    altaLibro(sc);
                    break;
                case 2:
                    modificarLibro(sc);
                    break;
                case 3:
                    System.out.println(bibliotecaAdmin.listarLibros());
                    break;
                case 4:
                    exit = true;
                    System.out.println("Saliendo del programa...");
                    break;
            }
        }
    }

    public void altaLibro(Scanner sc) {
        System.out.println("Desea buscar por titulo, o titulo y autor?\n1: Titulo\n2: Titulo y autor");
        int opcion = sc.nextInt();
        sc.nextLine();
        while (opcion != 1 && opcion != 2) {
            System.out.println("La opcion no es valida, intente nuevamente:");
            opcion = sc.nextInt();
            sc.nextLine();
        }
        String titulo;
        String si_no;
        System.out.println("Ingrese el titulo del libro que desea buscar:");
        titulo = sc.nextLine();
        try {
            if (opcion == 1) {
                System.out.println(bibliotecaAdmin.buscarLibro(titulo));
            } else {
                System.out.println("Ingrese el autor del libro que desea buscar:");
                String autor = sc.nextLine();
                System.out.println(bibliotecaAdmin.buscarLibro(titulo, autor));
            }
            System.out.println("Desea agregar uno de los libros encontrados? s/n:");
            si_no = sc.nextLine();
            if (si_no.equalsIgnoreCase("s")) {
                System.out.println("Seleccione el indice del libro que desea agregar:");
                int indiceLibro = sc.nextInt();
                sc.nextLine();
                while (indiceLibro < 1 || indiceLibro > bibliotecaAdmin.cantidadResultados()) {
                    System.out.println("El indice ingresado es incorrecto, intente de nuevo:");
                    indiceLibro = sc.nextInt();
                    sc.nextLine();
                }
                System.out.println("Seleccione el genero:");
                for (GeneroEdicion generoEdicion : GeneroEdicion.values()) {
                    System.out.println((generoEdicion.ordinal() + 1) + ": " + generoEdicion.getGenero());
                }
                int valorGenero = sc.nextInt() - 1;
                sc.nextLine();
                while (valorGenero < 0 || valorGenero > GeneroEdicion.values().length - 1) {
                    System.out.println("El valor ingresado es incorrecto, intente de nuevo:");
                    valorGenero = sc.nextInt() - 1;
                    sc.nextLine();
                }
                GeneroEdicion generoEdicion = GeneroEdicion.values()[valorGenero];
                System.out.println("Ingrese el precio:");
                float precio = sc.nextFloat();
                sc.nextLine();
                while (precio < 0) {
                    System.out.println("El precio no puede ser negativo, intente de nuevo:");
                    precio = sc.nextFloat();
                    sc.nextLine();
                }
                System.out.println("Ingrese el stock:");
                int stock = sc.nextInt();
                sc.nextLine();
                while (stock < 0) {
                    System.out.println("El stock no puede ser negativo, intente de nuevo:");
                    stock = sc.nextInt();
                    sc.nextLine();
                }
                bibliotecaAdmin.agregarLibro(indiceLibro, generoEdicion, stock, precio);
            }
        }
        catch (BusquedaSinResultadosException busquedaSinResultadosException) {
            System.out.println(busquedaSinResultadosException.getMessage());
        }
    }

    public void modificarLibro(Scanner sc) {
        System.out.println(bibliotecaAdmin.listarLibros());
        System.out.println("Ingrese el ID del libro que quiere modificar:");
        String id = sc.nextLine().trim();
        try {
            Libro libro = (Libro)bibliotecaAdmin.buscarPorID(id);
            boolean exit = false;
            while (!exit) {
                System.out.println("\tModificar libro: ");
                System.out.println("1: Genero");
                System.out.println("2: Precio");
                System.out.println("3: Ingresar stock");
                System.out.println("4: Exit");
                int opcion = sc.nextInt();
                sc.nextLine();
                while (opcion < 1 || opcion > 4) {
                    System.out.println("El valor ingresado es incorrecto, intente de nuevo:");
                    opcion = sc.nextInt();
                    sc.nextLine();
                }
                switch (opcion) {
                    case 1:
                        System.out.println("Seleccione el genero:");
                        for (GeneroEdicion generoEdicion : GeneroEdicion.values()) {
                            System.out.println((generoEdicion.ordinal() + 1) + ": " + generoEdicion.getGenero());
                        }
                        int valorGenero = sc.nextInt() - 1;
                        sc.nextLine();
                        while (valorGenero < 0 || valorGenero > GeneroEdicion.values().length - 1) {
                            System.out.println("El valor ingresado es incorrecto, intente de nuevo:");
                            valorGenero = sc.nextInt() - 1;
                            sc.nextLine();
                        }
                        GeneroEdicion generoEdicion = GeneroEdicion.values()[valorGenero];
                        libro.setGenero(generoEdicion);
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo precio:");
                        float precio = sc.nextFloat();
                        sc.nextLine();
                        while (precio < 0) {
                            System.out.println("El precio no puede ser negativo, intente de nuevo:");
                            precio = sc.nextFloat();
                            sc.nextLine();
                        }
                        libro.setPrecio(precio);
                        break;
                    case 3:
                        System.out.println("Ingrese el stock que desea ingresar:");
                        int stock = sc.nextInt();
                        sc.nextLine();
                        while (stock < 0) {
                            System.out.println("El stock no puede ser negativo, intente de nuevo:");
                            stock = sc.nextInt();
                            sc.nextLine();
                        }
                        stock += libro.getStock();
                        libro.setStock(stock);
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Saliendo del programa...");
                        break;
                }
            }

        }
        catch (BusquedaSinResultadosException busquedaSinResultadosException) {
            System.out.println(busquedaSinResultadosException.getMessage());
        }
        catch (ClassCastException classCastException) {
            System.out.println("La edicion encontrada no es un libro");
        }
    }

    //-----------------------------------------------------------------

    public int opcionesMenuRevista(Scanner sc){
        int option;

        System.out.println("\tIngreso al menu de la biblioteca: ");
        System.out.println("**Menu Revistas: ");
        System.out.println("\tSeleccione la operacion a realizar: ");
        System.out.println("1: Alta revistas");
        System.out.println("2: Modificando revistas");
        System.out.println("3: Historial revistas");
        System.out.println("4: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 4");
        option = sc.nextInt();
        sc.nextLine();

        while (option < 1 || option > 4) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            option = sc.nextInt();
            sc.nextLine();
        }
        return option;
    }

    public void menuSwitchRevista(Scanner sc){
        boolean exit = false;

        while (!exit) {
            int option = opcionesMenuRevista(sc);
            switch (option) {
                case 1:
                    altaRevista(sc);
                    break;
                case 2:
                    modificarRevista(sc);
                    break;
                case 3:
                    System.out.println(bibliotecaAdmin.listarRevistas());
                    break;
                case 4:
                    exit = true;
                    System.out.println("Saliendo del programa...");
                    break;
            }
        }
    }

    public void altaRevista(Scanner sc) {
        String titulo;
        String si_no;
        System.out.println("Ingrese el titulo de la revista que desea buscar:");
        titulo = sc.nextLine();
        try {
            System.out.println(bibliotecaAdmin.buscarRevista(titulo));
            System.out.println("Desea agregar una de las revistas encontradas? s/n:");
            si_no = sc.nextLine();
            if (si_no.equalsIgnoreCase("s")) {
                System.out.println("Seleccione el indice de la revista que desea agregar:");
                int indiceRevista = sc.nextInt();
                sc.nextLine();
                while (indiceRevista < 1 || indiceRevista > bibliotecaAdmin.cantidadResultados()) {
                    System.out.println("El indice ingresado es incorrecto, intente de nuevo:");
                    indiceRevista = sc.nextInt();
                    sc.nextLine();
                }
                System.out.println("Ingrese el precio:");
                float precio = sc.nextFloat();
                sc.nextLine();
                while (precio < 0) {
                    System.out.println("El precio no puede ser negativo, intente de nuevo:");
                    precio = sc.nextFloat();
                    sc.nextLine();
                }
                System.out.println("Ingrese el stock:");
                int stock = sc.nextInt();
                sc.nextLine();
                while (stock < 0) {
                    System.out.println("El stock no puede ser negativo, intente de nuevo:");
                    stock = sc.nextInt();
                    sc.nextLine();
                }
                bibliotecaAdmin.agregarRevista(indiceRevista, stock, precio);
            }
        }
        catch (BusquedaSinResultadosException busquedaSinResultadosException) {
            System.out.println(busquedaSinResultadosException.getMessage());
        }
    }

    public void modificarRevista(Scanner sc) {
        System.out.println(bibliotecaAdmin.listarRevistas());
        System.out.println("Ingrese el ID la revista que quiere modificar:");
        String id = sc.nextLine().trim();
        try {
            Revista revista = (Revista) bibliotecaAdmin.buscarPorID(id);
            boolean exit = false;
            while (!exit) {
                System.out.println("\tModificar revista: ");
                System.out.println("1: Precio");
                System.out.println("2: Ingresar stock");
                System.out.println("3: Exit");
                int opcion = sc.nextInt();
                sc.nextLine();
                while (opcion < 1 || opcion > 3) {
                    System.out.println("El valor ingresado es incorrecto, intente de nuevo:");
                    opcion = sc.nextInt();
                    sc.nextLine();
                }
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo precio:");
                        float precio = sc.nextFloat();
                        sc.nextLine();
                        while (precio < 0) {
                            System.out.println("El precio no puede ser negativo, intente de nuevo:");
                            precio = sc.nextFloat();
                            sc.nextLine();
                        }
                        revista.setPrecio(precio);
                        break;
                    case 2:
                        System.out.println("Ingrese el stock que desea ingresar:");
                        int stock = sc.nextInt();
                        sc.nextLine();
                        while (stock < 0) {
                            System.out.println("El stock no puede ser negativo, intente de nuevo:");
                            stock = sc.nextInt();
                            sc.nextLine();
                        }
                        stock += revista.getStock();
                        revista.setStock(stock);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Saliendo del programa...");
                        break;
                }
            }

        }
        catch (BusquedaSinResultadosException busquedaSinResultadosException) {
            System.out.println(busquedaSinResultadosException.getMessage());
        }
        catch (ClassCastException classCastException) {
            System.out.println("La edicion encontrada no es una revista");
        }
    }

    //----------------------------------------------------------------

    public int opcionesMenuModificacionSocio(Scanner sc){
        int option = 0;

            System.out.println("\tIngreso al menu de la biblioteca: ");
            System.out.println("**Menu Socio a modificar: ");
            System.out.println("\tSeleccione la operacion a realizar: ");
            System.out.println("1: Dni");
            System.out.println("2: Apellido");
            System.out.println("3: Nombre");
            System.out.println("4: Edad");
            System.out.println("5: Tel");
            System.out.println("6: Direccion");
            System.out.println("7: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 7");
        option = sc.nextInt();

        while (option < 1 || option > 7) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            }
        }

        return option;
    }

    public void menuSwitchModificacionSocio(Scanner sc){
        int option = opcionesMenuModificacionSocio(sc);
        boolean exit = false;
        BibliotecaAdmin admin = new BibliotecaAdmin();
        String dni, apellido, nombre, tel, direccion = null;
        int edad = 0;

        System.out.println("Ingrese el dni de la persona a eliminar: ");
        dni = sc.nextLine();

        while(!(Validator.isNumeric(dni))){
            System.out.println("Dato erroneo.");
        }

        if(!admin.ifMiembroExist(dni)){
            System.out.println("Persona inexistente");
        }else{
            Persona personaToChange = admin.getMiembros().get(dni);
            if(personaToChange instanceof Socio){
                Socio socioToChange = (Socio) personaToChange;

            switch(option){
                case 1:
                    System.out.println("Ingrese dni a modificar: ");
                    dni = sc.nextLine();

                    socioToChange.setIdDni(dni);
                    break;
                case 2:
                    System.out.println("Ingrese apellido a modificar: ");
                    apellido = sc.nextLine();

                    socioToChange.setApellido(apellido);
                    break;
                case 3:
                    System.out.println("Ingrese nombre a modificar: ");
                    nombre = sc.nextLine();

                    socioToChange.setNombre(nombre);
                    break;
                case 4:
                    System.out.println("Ingrese edad a modificar: ");
                    edad = sc.nextInt();

                    socioToChange.setEdad(edad);
                    break;
                case 5:
                    System.out.println("Ingrese tel a modificar: ");
                    tel = sc.nextLine();

                    socioToChange.setTel(tel);
                    break;
                case 6:
                    System.out.println("Ingrese direccion a modificar: ");
                    direccion = sc.nextLine();

                    socioToChange.setDireccion(direccion);
                    break;
                case 7:
                    exit = true;
                    System.out.println("Saliendo del programa...");
                    break;
            }
        }
        }
    }

    //----------------------------------------------------------------

    public int opcionesMenuModificacionCliente(Scanner sc){
        int option = 0;

        System.out.println("\tIngreso al menu de la biblioteca: ");
        System.out.println("**Menu Cliente a modificar: ");
        System.out.println("\tSeleccione la operacion a realizar: ");
        System.out.println("1: Dni");
        System.out.println("2: Apellido");
        System.out.println("3: Nombre");
        System.out.println("4: Edad");
        System.out.println("5: Tel");
        System.out.println("6: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 6");
        option = sc.nextInt();

        while (option < 1 || option > 6) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            }
        }

        return option;
    }

    public void menuSwitchModificacionCliente(Scanner sc){
        int option = opcionesMenuModificacionCliente(sc);
        boolean exit = false;
        BibliotecaAdmin admin = new BibliotecaAdmin();
        String dni, apellido, nombre, tel, direccion, email = null;
        int edad = 0;

        System.out.println("\tIngrese el dni de la persona a eliminar: ");
        dni = sc.nextLine();

        while(!(Validator.isNumeric(dni))){
            System.out.println("Dato erroneo.");
        }

        if(!admin.ifMiembroExist(dni)){
            System.out.println("Persona inexistente");
        }else{
            Persona personaToChange = admin.getMiembros().get(dni);
            if(personaToChange instanceof Cliente){
                Cliente clienteToChange = (Cliente) personaToChange;

                switch(option){
                    case 1:
                        System.out.println("Ingrese dni a modificar: ");
                        dni = sc.nextLine();

                        clienteToChange.setIdDni(dni);
                        break;
                    case 2:
                        System.out.println("Ingrese apellido a modificar: ");
                        apellido = sc.nextLine();

                        clienteToChange.setApellido(apellido);
                        break;
                    case 3:
                        System.out.println("Ingrese nombre a modificar: ");
                        nombre = sc.nextLine();

                        clienteToChange.setNombre(nombre);
                        break;
                    case 4:
                        System.out.println("Ingrese edad a modificar: ");
                        edad = sc.nextInt();

                        clienteToChange.setEdad(edad);
                        break;
                    case 5:
                        System.out.println("Ingrese tel a modificar: ");
                        tel = sc.nextLine();

                        clienteToChange.setTel(tel);
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Saliendo del programa...");
                        break;
                }
            }
        }
    }

    //-----------------------------------------------------------------

    public int opcionesMenuCliente(Scanner sc){
        int option = 0;
        BibliotecaAdmin admin = new BibliotecaAdmin();

        System.out.println("\tIngreso al menu de la biblioteca: ");
        System.out.println("**Menu Clientes: ");
        System.out.println("\tSeleccione la operacion a realizar: ");
        System.out.println("1: Alta clientes");
        System.out.println("2: Modificando clientes");
        System.out.println("3: Baja clientes");
        System.out.println("4: Historial clientes");
        System.out.println("5: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 5");
        option = sc.nextInt();

        while (option < 1 || option > 5) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            }
        }

        return option;
    }

    public void menuSwitchCliente(Scanner sc){
        int option = opcionesMenuCliente(sc);
        boolean exit = false;
        String dni = null;

        sc.nextLine();
        switch(option){
            case 1:
                bibliotecaAdmin.generaListCliente(sc);
                bibliotecaAdmin.recorreListClientes();
                returnMenuCliente(sc);
                break;
            case 2:
                menuSwitchModificacionCliente(sc);
                break;
            case 3:
                System.out.println("Ingrese el dni de la persona a eliminar: ");
                dni = sc.nextLine();
                while(!(Validator.isNumeric(dni))){
                    System.out.println("Dato erroneo.");
                }
                bibliotecaAdmin.eliminaPersona(sc, dni);
                break;
            case 4:
                bibliotecaAdmin.recorreListClientes();
                break;
            case 5:
                exit = true;
                System.out.println("Saliendo del programa...");
                returnMenuPrincipal(sc);
                break;
        }
    }

    //-----------------------------------------------------------------

    public int opcionesMenuSocio(Scanner sc){
        int option = 0;

            System.out.println("\tIngreso al menu de la biblioteca: ");
            System.out.println("**Menu Socios: ");
            System.out.println("\tSeleccione la operacion a realizar: ");
            System.out.println("1: Alta socios");
            System.out.println("2: Modificando socios");
            System.out.println("3: Baja socios");
            System.out.println("4: Historial socios");
            System.out.println("5: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 5");
        option = sc.nextInt();
        sc.nextLine();

        while (option < 1 || option > 5) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            }
        }


        return option;
    }

    public void menuSwitchSocio(Scanner sc){
        int option = opcionesMenuSocio(sc);
        boolean exit = false;
        String dni = null;

        switch(option){
            case 1:
                bibliotecaAdmin.generaListSocio(sc);
                bibliotecaAdmin.recorreListSocios();
                returnMenuSocio(sc);
                break;
            case 2:
                menuSwitchModificacionSocio(sc);
                break;
            case 3:
                System.out.println("Ingrese el dni de la persona a eliminar: ");
                dni = sc.nextLine();
                while(!(Validator.isNumeric(dni))){
                    System.out.println("Dato erroneo.");
                }
                bibliotecaAdmin.eliminaPersona(sc, dni);
                break;
            case 4:
                bibliotecaAdmin.recorreListSocios();
                break;
            case 5:
                exit = true;
                System.out.println("Saliendo del programa...");
                returnMenuPrincipal(sc);
                break;
        }
    }

    //----------------------------------------------------------------

    public int opcionesMenuPrincipal(Scanner sc) {
            int option = 0;

            System.out.println("\tIngreso al menu de la biblioteca: ");
            System.out.println("**Menu principal: ");
            System.out.println("\tSeleccione la operacion a realizar: ");
            System.out.println("1: Menu socios");
            System.out.println("2: Menu clientes");
            System.out.println("3: Menu libros");
            System.out.println("4: Menu revistas");
            System.out.println("5: Menu ventas");
            System.out.println("6: Menu prestamos");
            System.out.println("7: Menu cobranza");
            System.out.println("8: Exit");

        System.out.println("\tIngrese una opcion del menu del 1 al 8");
        option = sc.nextInt();

        while (option < 1 || option > 8) {
            System.out.println("Opción inválida. Por favor, intente nuevamente.");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            }
        }

        return option;
    }

    public void menuSwitchPrincipal(Scanner sc){
        int option = opcionesMenuPrincipal(sc);
        boolean exit = false;

        switch(option){
            case 1:
                menuSwitchSocio(sc);
                returnMenuPrincipal(sc);
                break;
            case 2:
                menuSwitchCliente(sc);
                returnMenuPrincipal(sc);
                break;
            case 3:
                menuSwitchLibro(sc);
                returnMenuPrincipal(sc);
                break;
            case 4:
                menuSwitchRevista(sc);
                returnMenuPrincipal(sc);
                break;
            case 5:
                //menuSwitchVenta(sc);
                returnMenuPrincipal(sc);
                break;
            case 6:
                //menuSwitchAlquiler(sc);
                returnMenuPrincipal(sc);
                break;
            case 7:
                ///menuSwitchCobranza(sc); VER IMPLEMENTACION SI SE USA
                returnMenuPrincipal(sc);
                break;
            case 8:
                exit = true;
                System.out.println("Saliendo del programa...");
                break;
        }
    }

    //----------------------------------------------------------------

    public void returnMenuPrincipal(Scanner sc){
            clearConsole();
            menuSwitchPrincipal(sc);
    }

    public void clearConsole() {
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
    }

    public void returnMenuSocio(Scanner sc){
        clearConsole();
        menuSwitchSocio(sc);
    }

    public void returnMenuCliente(Scanner sc){
        clearConsole();
        menuSwitchCliente(sc);
    }

    public void escribirBiblioteca() {
        Utilidades.escribir(this.bibliotecaAdmin);
    }


    //----------------------------------------------------------------
}