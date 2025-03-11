package Utilidades;

//import java.util.regex.Pattern;
//import java.util.regex.Matcher;

import BibliotecaAdmin.Excepciones.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.format.ResolverStyle.STRICT;

public class Validator {

    //private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    //private static final String ADDRESS_REGEX = "^[\\d\\w\\s,-\\.]+$";

    // Valida si una cadena contiene solo letras
    public static boolean isAlphabetic(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }

    // Valida si una cadena contiene solo números
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+");
    }

    // Valida si una cadena no está vacía ni es nula
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Valida si un número está dentro de un rango específico
    public static <T extends Number> boolean isValidNumber(T number, T min, T max) {
        if (number == null || min == null || max == null) {
            return false;
        }
        return number.doubleValue() >= min.doubleValue() && number.doubleValue() <= max.doubleValue();
    }

    // Valida si una cadena representa un número entero
    public static boolean isInteger(int str) {
        if (String.valueOf(str) == null) {
            return false;
        }
        try {
            Integer.parseInt(String.valueOf(str));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Valida si una cadena representa un número decimal
    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }*/

    /*public static boolean isValidAddress(String address) {
        if (address == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(ADDRESS_REGEX);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }*/

    public static boolean isValidAddress(String address) {

        if (address == null || address.isEmpty()) {
            return false;
        }

        if (address.length() < 5) {
             return false;
        }

        return true;
    }

    public static int validarInt(Scanner sc)
    {
        int num = 0;
        boolean flag = false;
        while(flag==false)
        {
        try
        {
            num = sc.nextInt();
            flag=true;
        }
        catch (InputMismatchException ime)
        {
        System.out.println("Por favor asegurese de ingresar un numero entero");
        }
        finally
        {
            sc.nextLine();
        }
        }
        return num;
    }

    //VALIDA SI LA CANTIDAD DE NUMERO DE LA TARJETA ES CORRECTA
    public static boolean cantDigValido(String numTarjeta){
        return numTarjeta.length() >= 13 && numTarjeta.length() <= 19;
    }

    //VALIDA LOS PRIMEROS NUMEROS DEPENDIENDO LA EMPRESA
    // VISA-4 MASTER-5 AMERICAN-37
    public static boolean prefValido(String numTarjeta){
        String [] prefijoValido = {"4","5","37"};
        for(String prefijo: prefijoValido) {
            if (numTarjeta.startsWith(prefijo)) {
                return true;
            }
        }
        return false;
    }

    //ASI SE VALIDAN LAS TARJETAS DE CREDITO/DEBITO
    public static boolean validacionLuhn (String numTarjeta){
        int sum = 0;
        boolean alternate = false;
        for (int i = numTarjeta.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numTarjeta.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    //VALIDAR TODAS LAS ANTERIORES
    public static boolean validarTodo (String numTarjeta){
        return cantDigValido(numTarjeta) && cantDigValido(numTarjeta) && validacionLuhn(numTarjeta);
    }

    //VALIDAR CODIGO DE SEGURIDAD
    public static boolean codSegValido (String codSeguridad){
        return codSeguridad.length()==3 && codSeguridad.matches("\\d{3}");
    }

    public static void codSegValidoE (int longitud) throws CantDigitosIncorrectoException {
        if (longitud!=3)
        {
            throw new CantDigitosIncorrectoException();
        }
    }

    public static void nroTarjetaValidoE (int longitud) throws CantDigitosIncorrectoException {
        if (longitud!=16)
        {
            throw new CantDigitosIncorrectoException();
        }
    }

    public static void validarEdad(int edad) throws EdadNoDentrodeRangoPermitidoException {
        if (edad<13 || edad>100)
        {
            throw new EdadNoDentrodeRangoPermitidoException();
        }
    }

    public static boolean validarEdadBool(int edad){
        if (edad<13 || edad>100)
        {
            return false;
        }
        return true;
    }

    public static void isNumericE (String str) throws StringNoNumericoException
    {
        if (str == null || !str.matches("\\d+"))
        {
           throw new StringNoNumericoException();
        }
    }

    public static void existe (String str) throws DatoIncompletoException
    {
        if (str.isEmpty())
        {
            throw new DatoIncompletoException();
        }
    }

    public static boolean isAlfabetico(String str) ///Valida que solo contenga letras
    {
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Iterar a través de cada carácter y verificar si es una letra
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void isAlfabeticoE(String str) throws StringNoAlfabeticoException ///Valida que solo contenga letras con excepcion
    {
        if (str == null || str.isEmpty()) {
            throw new StringNoAlfabeticoException();
        }

        // Iterar a través de cada carácter y verificar si es una letra
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                throw new StringNoAlfabeticoException();
            }
        }
    }

    public static void validarFecha(String fechaStr) throws TarjetaVencidaException, DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        YearMonth yearMonth = YearMonth.parse(fechaStr, formatter);

        // Obtener la fecha actual
        YearMonth ahora = YearMonth.now();

        if (yearMonth.isBefore(ahora))
        {
            throw new TarjetaVencidaException();
        }
    }
}

