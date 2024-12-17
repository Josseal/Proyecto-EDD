import java.util.List;
public class GrafoDestinos {
    private Grafo grafo;
    public GrafoDestinos() {
        grafo = new Grafo();
    }
    public void agregarDestino(String origen, String destino) {
        grafo.agregarNodo(origen);
        grafo.agregarNodo(destino);
        grafo.agregarArista(origen, destino);
    }
    public void imprimirDestinos() {
        List<Object> nodos = grafo.obtenerNodos(); // Suponiendo que grafo.obtenerNodos() devuelve una lista de nodos
        for (Object nodo : nodos) {
            System.out.println("Destino: " + nodo);
        }
    }
    public String getDestino() throws Exception {
        List<Object> nodos = grafo.obtenerNodos(); // Obtener los nodos del grafo
        if (nodos.isEmpty()) {
            throw new Exception("No hay destinos disponibles.");
        }
        return nodos.get(0).toString(); 
    }
    public void cargarDestinosDesdeCSV(String rutaArchivo) {
        List<String[]> datos = CSVReader.leerCSV(rutaArchivo);
        for (String[] fila : datos) {
            if (fila.length >= 2) { // Asegurarnos de que haya al menos origen y destino
                agregarDestino(fila[0], fila[1]);
            }
        }
    }

}

