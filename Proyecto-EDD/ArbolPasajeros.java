import java.io.*;
import java.util.*;

public class ArbolPasajeros {
    private NodoArbol raiz;

    public ArbolPasajeros() {
        this.raiz = null;
    }

    public void insertar(Pasajero pasajero) {
        this.raiz = insertarRecursivo(this.raiz, pasajero);
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, Pasajero pasajero) {
        if (nodo == null) {
            return new NodoArbol(pasajero);
        }
        // Inserción según el orden alfabético del nombre
        if (pasajero.getNombre().compareTo(nodo.getPasajero().getNombre()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), pasajero));
        } else if (pasajero.getNombre().compareTo(nodo.getPasajero().getNombre()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), pasajero));
        } else {
            // Si el pasajero ya existe, no se inserta duplicado
            return nodo;
        }
        // Actualizar altura del nodo
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el árbol AVL
        int factorBalance = balance(nodo);

        // Caso Izquierda-Izquierda
        if (factorBalance > 1 && pasajero.getNombre().compareTo(nodo.getIzquierdo().getPasajero().getNombre()) < 0) {
            return rotarDerecha(nodo);
        }
        // Caso Derecha-Derecha
        if (factorBalance < -1 && pasajero.getNombre().compareTo(nodo.getDerecho().getPasajero().getNombre()) > 0) {
            return rotarIzquierda(nodo);
        }
        // Caso Izquierda-Derecha
        if (factorBalance > 1 && pasajero.getNombre().compareTo(nodo.getIzquierdo().getPasajero().getNombre()) > 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }
        // Caso Derecha-Izquierda
        if (factorBalance < -1 && pasajero.getNombre().compareTo(nodo.getDerecho().getPasajero().getNombre()) < 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private int altura(NodoArbol nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int balance(NodoArbol nodo) {
        return (nodo == null) ? 0 : altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    private NodoArbol rotarDerecha(NodoArbol y) {
        NodoArbol x = y.getIzquierdo();
        NodoArbol T2 = x.getDerecho();

        // Rotación
        x.setDerecho(y);
        y.setIzquierdo(T2);

        // Actualizar alturas
        y.setAltura(1 + Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())));
        x.setAltura(1 + Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())));

        return x;
    }

    private NodoArbol rotarIzquierda(NodoArbol x) {
        NodoArbol y = x.getDerecho();
        NodoArbol T2 = y.getIzquierdo();

        // Rotación
        y.setIzquierdo(x);
        x.setDerecho(T2);

        // Actualizar alturas
        x.setAltura(1 + Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())));
        y.setAltura(1 + Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())));

        return y;
    }

    @Override
    public String toString() {
        return recorridoEnOrden(this.raiz);
    }

    private String recorridoEnOrden(NodoArbol nodo) {
        if (nodo == null) {
            return "";
        }
        return recorridoEnOrden(nodo.getIzquierdo()) +
               nodo.getPasajero() + "\n" +
               recorridoEnOrden(nodo.getDerecho());
    }

    public void cargarPasajerosDesdeCSV(String rutaArchivo) {
        List<String[]> datos = CSVReader.leerCSV(rutaArchivo);
        for (String[] fila : datos) {
            if (fila.length >= 3) { // Asegurarnos de que haya al menos nombre, teléfono y dirección
                Pasajero pasajero = new Pasajero(fila[0], fila[1], fila[2]);
                insertar(pasajero); // Cambiar insertarPasajero por insertar
            }
        }
    }
    
    public Pasajero getPasajero() throws Exception {
        if (raiz == null) {
            throw new Exception("No hay pasajeros en el sistema.");
        }
        Pasajero pasajero = raiz.getPasajero();
        this.raiz = eliminarNodo(this.raiz, pasajero.getNombre());
        return pasajero;
    }

    private NodoArbol eliminarNodo(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getPasajero().getNombre()) < 0) {
            nodo.setIzquierdo(eliminarNodo(nodo.getIzquierdo(), nombre));
        } else if (nombre.compareTo(nodo.getPasajero().getNombre()) > 0) {
            nodo.setDerecho(eliminarNodo(nodo.getDerecho(), nombre));
        } else {
            // Nodo encontrado
            if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                // Caso 1: El nodo tiene 0 o 1 hijo
                NodoArbol temp = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
                return temp; // Si no tiene hijos, se devuelve el hijo o null
            } else {
                // Caso 2: El nodo tiene 2 hijos
                NodoArbol temp = obtenerMinimo(nodo.getDerecho());
                if (temp != null) {
                    nodo.setPasajero(temp.getPasajero()); // Reemplazamos el valor del nodo por el mínimo
                    nodo.setDerecho(eliminarNodo(nodo.getDerecho(), temp.getPasajero().getNombre())); // Eliminamos el nodo mínimo
                }
            }
        }
        return nodo;
    }

    private NodoArbol obtenerMinimo(NodoArbol nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public void imprimirPasajeros() {
        System.out.println(toString());
    }
}
