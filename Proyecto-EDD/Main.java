import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        SistemaTaxis sistema = new SistemaTaxis(4000); 
        Scanner scanner = new Scanner(System.in);

        // Rutas de los archivos CSV
        String rutaPasajeros = "pasajeros.csv";
        String rutaTaxis = "taxis.csv";
        String rutaDestinos = "destinos.csv";

        // Cargar datos desde los CSV
        System.out.println("Cargando datos desde los archivos CSV");
        sistema.cargarPasajerosDesdeCSV(rutaPasajeros);
        sistema.cargarTaxisDesdeCSV(rutaTaxis);
        sistema.cargarDestinosDesdeCSV(rutaDestinos);
        System.out.println("Datos cargados exitosamente.");

        // Menú principal
        int opcion;
        do {
            System.out.println("\n--- Menú del Sistema de Taxis ---");
            System.out.println("1. Agregar pasajero manualmente");
            System.out.println("2. Agregar destino manualmente");
            System.out.println("3. Agregar taxi manualmente");
            System.out.println("4. Asignar pasajero a un taxi con destino");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Agregar Pasajero ---");
                    System.out.print("Nombre: ");
                    String nombrePasajero = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefonoPasajero = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccionPasajero = scanner.nextLine();
                    Pasajero pasajero = new Pasajero(nombrePasajero, telefonoPasajero, direccionPasajero);
                    sistema.insertarPasajero(pasajero);
                    System.out.println("Pasajero agregado al sistema.");
                    break;

                case 2:
                    System.out.println("\n--- Agregar Destino ---");
                    System.out.print("Nombre del destino: ");
                    String nombreDestino = scanner.nextLine();
                    System.out.print("Dirección del destino: ");
                    String direccionDestino = scanner.nextLine();
                    sistema.agregarDestino(nombreDestino, direccionDestino);
                    System.out.println("Destino agregado al sistema.");
                    break;

                case 3:
                    System.out.println("\n--- Agregar Taxi ---");
                    System.out.print("Número de placa: ");
                    String numeroPlaca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modeloTaxi = scanner.nextLine();
                    System.out.print("Nombre del conductor: ");
                    String nombreConductor = scanner.nextLine();
                    Taxi taxi = new Taxi(numeroPlaca, modeloTaxi, nombreConductor);
                    try {
                        sistema.agregarTaxi(taxi);
                    } catch (Exception e) {
                        System.out.println("Error al agregar taxi: " + e.getMessage());
                    }
                    System.out.println("Taxi agregado al sistema.");
                    break;

                case 4:
                    System.out.println("\n--- Asignar Pasajero a un Taxi con Destino ---");
                    try {
                        Pasajero pasajeroAsignado = sistema.getPasajero();
                        Taxi taxiAsignado = sistema.getTaxi();
                        String destinoAsignado = sistema.getDestino();

                        System.out.println("Asignación completada:");
                        System.out.println("Pasajero: " + pasajeroAsignado);
                        System.out.println("Taxi: " + taxiAsignado);
                        System.out.println("Destino: " + destinoAsignado);
                    } catch (Exception e) {
                        System.out.println("Error en la asignación: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Salindo");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
    
}
