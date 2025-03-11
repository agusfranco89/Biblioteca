package BibliotecaAdmin.Ediciones;

import BibliotecaAdmin.Excepciones.BusquedaSinResultadosException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Buscador implements Serializable {
    private final String API_KEY = "AIzaSyDWf60nN73bbRstCU1M4lK4cry9oDxEYR0";
    private ArrayList<Edicion> resultados;

    public Buscador() {
        resultados = new ArrayList<>();
    }

    private String request(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Error - codigo de respuesta: " + responseCode);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
                scanner.close();
                return stringBuilder.toString();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    public String buscarLibro(String titulo) throws BusquedaSinResultadosException{
        StringBuilder stringBuilder = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String tituloURL = stringURL(titulo);
        stringBuilder.append(tituloURL).append("&printType=books&key=").append(API_KEY);
        String link = stringBuilder.toString();
        String jsonString = request(link);
        return listarBusquedaLibros(jsonString);
    }

    public String buscarLibro(String titulo, String autor) throws BusquedaSinResultadosException{
        StringBuilder stringBuilder = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String tituloURL = stringURL(titulo);
        stringBuilder.append(tituloURL).append("+inauthor:");
        String autorURL = stringURL(autor);
        stringBuilder.append(autorURL).append("&printType=books&key=").append(API_KEY);
        String link = stringBuilder.toString();
        String jsonString = request(link);
        return listarBusquedaLibros(jsonString);
    }

    public String buscarRevista(String titulo) throws BusquedaSinResultadosException{
        StringBuilder stringBuilder = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String tituloURL = stringURL(titulo);
        stringBuilder.append(tituloURL).append("&printType=magazines&key=").append(API_KEY);
        String link = stringBuilder.toString();
        String jsonString = request(link);
        return listarBusquedaRevistas(jsonString);
    }

    public Edicion buscarPorIndiceResultados(int indice) {
        indice--;
        return resultados.get(indice);
    }

    private String stringURL(String string) {
        String[] stringArray = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            stringBuilder.append(stringArray[i]);
            if (i < stringArray.length - 1) {
                stringBuilder.append('+');
            }
        }
        return stringBuilder.toString();
    }

    private JSONArray items(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONArray("items");
    }

    private String listarBusquedaLibros(String jsonString) throws JSONException, BusquedaSinResultadosException{
        if (!sinResultados()) resultados.clear();
        try {
            JSONArray items = items(jsonString);
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    Libro libro = generarLibro(item);
                    resultados.add(libro);
                } catch (JSONException jsonException) {

                }
            }
        } catch (JSONException jsonException) {

        }
        return listarUltimaBusqueda();
    }

    private String listarBusquedaRevistas(String jsonString) throws JSONException, BusquedaSinResultadosException {
        if (!sinResultados()) resultados.clear();
        try {
            JSONArray items = items(jsonString);
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    Revista revista = generarRevista(item);
                    resultados.add(revista);
                } catch (JSONException jsonException) {
                    System.out.println(jsonException.getMessage());
                }
            }
        } catch (JSONException jsonException) {
            System.out.println(jsonException.getMessage());
        }
        return listarUltimaBusqueda();
    }

    private String listarUltimaBusqueda() throws BusquedaSinResultadosException{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSu busqueda obtuvo ").append(resultados.size()).append(" resultado(s):\n");
        if (sinResultados()) {
            throw new BusquedaSinResultadosException("No hay ediciones disponibles para elegir.");
        }
        for (Edicion l : resultados) {
            stringBuilder.append('\n').append(resultados.indexOf(l) + 1).append(" -").append(l);
        }
        return stringBuilder.toString();
    }

    private Libro generarLibro(JSONObject item) throws JSONException {
        String id = item.getString("id");
        JSONObject volumeInfo = item.getJSONObject("volumeInfo");
        String titulo = volumeInfo.getString("title");
        JSONArray authors = volumeInfo.getJSONArray("authors");
        StringBuilder autores = new StringBuilder();
        for (int j = 0; j < authors.length(); j++) {
            if (j > 0) {
                autores.append(", ");
            }
            autores.append(authors.getString(j));
        }
        String editorial = volumeInfo.getString("publisher");
        String fechaPublicacion = volumeInfo.getString("publishedDate");
        String descripcion = volumeInfo.getString("description");
        JSONArray industryIdentifiersArray = volumeInfo.getJSONArray("industryIdentifiers");
        JSONObject industryIdentifiersObject = industryIdentifiersArray.getJSONObject(0);
        String isbn = industryIdentifiersObject.getString("identifier");
        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
        String imagen = imageLinks.getString("thumbnail");
        return new Libro(id, titulo, editorial, fechaPublicacion, autores.toString(), descripcion, isbn, imagen);
    }

    private Revista generarRevista(JSONObject item) throws JSONException {
        String id = item.getString("id");
        JSONObject volumeInfo = item.getJSONObject("volumeInfo");
        String titulo = volumeInfo.getString("title");
        String fechaPublicacion = volumeInfo.getString("publishedDate");
        JSONObject accessInfo = item.getJSONObject("accessInfo");
        JSONObject epub = accessInfo.getJSONObject("epub");
        boolean edicionDigital = epub.getBoolean("isAvailable");
        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
        String imagen = imageLinks.getString("thumbnail");
        return new Revista(id, titulo, fechaPublicacion, edicionDigital, imagen);
    }

    private boolean sinResultados() {
        return resultados.isEmpty();
    }

    public int cantidad() {
        return resultados.size();
    }

    public ArrayList<Edicion> getResultados() {
        return resultados;
    }
}
