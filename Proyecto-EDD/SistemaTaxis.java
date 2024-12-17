import java.util.*;
public class SistemaTaxis {
    private ColaTaxis colaTaxis;
    private ArbolPasajeros arbolPasajeros;
    private GrafoDestinos grafoDestinos;
    public SistemaTaxis(int capacidad) {
        colaTaxis = new ColaTaxis(capacidad);
        arbolPasajeros = new ArbolPasajeros();
        grafoDestinos = new GrafoDestinos();
    }
    public void agregarTaxi(Taxi taxi) throws Exception {
        colaTaxis.agregar(taxi);
    }
    public void asignarTaxi() throws Exception {
        Taxi taxi = colaTaxis.asignar(); // Cambia a devolver el objeto Taxi
        System.out.println("Taxi asignado: " + taxi);
    }
    public void insertarPasajero(Pasajero pasajero) {
        arbolPasajeros.insertar(pasajero);
    }
    public void agregarDestino(String origen, String destino) {
        grafoDestinos.agregarDestino(origen, destino);
    }
    public void cargarTaxisDesdeCSV(String rutaArchivo) {
        List<String[]> datos = CSVReader.leerCSV(rutaArchivo);
        for (String[] fila : datos) {
            if (fila.length >= 3) { // Asegúrate de que haya al menos placa, modelo y conductor
                Taxi taxi = new Taxi(fila[0], fila[1], fila[2]);
                try {
                    agregarTaxi(taxi);
                } catch (Exception e) {
                    System.out.println("Error al agregar taxi: " + e.getMessage());
                }
            }
        }
    }
    public void cargarPasajerosDesdeCSV(String rutaArchivo) {
        arbolPasajeros.cargarPasajerosDesdeCSV(rutaArchivo);
    }
    public void cargarDestinosDesdeCSV(String rutaArchivo) {
        grafoDestinos.cargarDestinosDesdeCSV(rutaArchivo);
    }
    public void imprimirSistema() {
        System.out.println("Pasajeros:");
        arbolPasajeros.imprimirPasajeros();

        System.out.println("Taxis:");
        colaTaxis.imprimirTaxis(); // Implementar un método para mostrar la cola

        System.out.println("Destinos:");
        grafoDestinos.imprimirDestinos();
    }
    public Pasajero getPasajero() throws Exception {
        return arbolPasajeros.getPasajero(); // Obtiene un pasajero del árbol
    }
    public Taxi getTaxi() throws Exception {
        return colaTaxis.asignar(); // Obtiene un taxi disponible de la cola
    }
    public String getDestino() throws Exception {
        return grafoDestinos.getDestino(); // Asumiendo que tienes un método para obtener un destino
    }
}
