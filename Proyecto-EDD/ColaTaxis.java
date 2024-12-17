import java.util.*;
public class ColaTaxis {
    private Taxi[] taxis;
    private int primero;
    private int ultimo;
    private int tamaño;
    public ColaTaxis(int capacidad) {
        this.taxis = new Taxi[capacidad];
        this.primero = -1;
        this.ultimo = -1;
        this.tamaño = 0;
    }
    public boolean estaLlena() {
        return tamaño == taxis.length;
    }
    public boolean estaVacia() {
        return tamaño == 0;
    }
    public boolean agregar(Taxi taxi) throws Exception {
        if (estaLlena()) {
            throw new Exception("La cola de taxis está llena.");
        }
        if (estaVacia()) {
            primero = 0;
        }
        ultimo = (ultimo + 1) % taxis.length;
        taxis[ultimo] = taxi;
        tamaño++;
        return true;
    }
    public void agregarTaxi(Taxi taxi) throws Exception {
        agregar(taxi); // Usa el método de la cola existente para agregar el objeto Taxi
    }
    public Taxi asignar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola de taxis está vacía.");
        }
        Taxi taxi = taxis[primero];
        primero = (primero + 1) % taxis.length;
        tamaño--;
        return taxi;
    }
    public void imprimirTaxis() {
        if (estaVacia()) {
            System.out.println("No hay taxis en la cola.");
            return;
        }
        System.out.println("Taxis en la cola:");
        int indice = primero;
        for (int i = 0; i < tamaño; i++) {
            System.out.println(taxis[indice]); 
            indice = (indice + 1) % taxis.length;
        }
    }
    public Taxi getTaxi() throws Exception {
        if (estaVacia()) {
            throw new Exception("No hay taxis disponibles.");
        }
        return quitar();
    }
    public Taxi quitar() throws Exception {
        if (estaVacia()) {
            throw new Exception("No hay taxis en la cola.");
        }
        Taxi taxiRemovido = taxis[primero];
        primero = (primero + 1) % taxis.length; 
        tamaño--; 
        return taxiRemovido;
    }
    public void cargarTaxisDesdeCSV(String rutaArchivo) {
        List<String[]> datos = CSVReader.leerCSV(rutaArchivo);
        for (String[] fila : datos) {
            if (fila.length >= 3) { // Asegurarnos de que haya al menos placa, modelo y conductor
                Taxi taxi = new Taxi(fila[0], fila[1], fila[2]);
                try {
                agregarTaxi(taxi);
                } catch (Exception e) {
                    System.out.println("Error al agregar taxi: " + e.getMessage());
                }
            }
        }
    }
    @Override
    public String toString() {
        if (estaVacia()) {
            return "Cola vacía";
        }
        StringBuilder sb = new StringBuilder();
        int indice = primero;
        for (int i = 0; i < tamaño; i++) {
            sb.append(taxis[indice]).append("\n");
            indice = (indice + 1) % taxis.length;
        }
        return sb.toString();
    }
}

