package Utilidades;

import BibliotecaAdmin.Excepciones.EdadNoDentrodeRangoPermitidoException;
import BibliotecaAdmin.BibliotecaAdmin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Utilidades {
    public static void printList(List<?> list){
        for(Object x: list){
            System.out.println(x);
        }
    }

    public static void printSet(Set<?> setList){
        for(Object x: setList){
            System.out.println(x);
        }
    }
    public static <T> void escribirGson (String nombreJson, T t) ///Escribir generico en un Json
    {
        File file = new File(nombreJson);
        BufferedWriter buffWriter = null;

        try {
            buffWriter = new BufferedWriter(new FileWriter(file));
            Gson gson = new Gson();
            gson.toJson(t, buffWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (buffWriter!=null)
            {
                try {
                    buffWriter.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> void escribirGson (String nombreJson, Collection<T> t) ///Escribir generico en un Json
    {
        File file = new File(nombreJson);
        BufferedWriter buffWriter = null;

        try {
            buffWriter = new BufferedWriter(new FileWriter(file));
            Gson gson = new Gson();
            gson.toJson(t, buffWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (buffWriter!=null)
            {
                try {
                    buffWriter.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public static <T> Collection<T> leerGson (String nombreJson, Class<T> clase) /// El archivo tiene que ser escrito como un arraylist
    {
        File file = new File(nombreJson);
        BufferedReader buffReader = null;
        if (file.exists())
        {
            try{
                buffReader = new BufferedReader(new FileReader(file));
                Gson gson = new Gson();
                Type listType = TypeToken.getParameterized(ArrayList.class, clase).getType();
                return gson.fromJson(buffReader, listType);
            } catch (IOException e){
                throw new RuntimeException(e);
            }finally {
                if (buffReader!=null)
                {
                    try {
                        buffReader.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            System.out.println("El .json solicitado no existe");
            return null;
        }
    }

    public static BibliotecaAdmin leerBiblioteca()
    {
        File file = new File("Biblioteca.json");
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(file, BibliotecaAdmin.class);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return new BibliotecaAdmin();
            }
        }
        else {
            return new BibliotecaAdmin();
        }
    }

    public static void escribir(BibliotecaAdmin bibliotecaAdmin) {
        File file = new File("Biblioteca.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(file, bibliotecaAdmin);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
