import java.io.*;
import java.util.*;
public class CSVReader {
    public static List<String[]> leerCSV(String rutaArchivo) {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(","); // Suponiendo que los datos están separados por comas
                datos.add(valores);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return datos;
    }
}
