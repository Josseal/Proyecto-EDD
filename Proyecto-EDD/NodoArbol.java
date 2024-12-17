public class NodoArbol {
    private Pasajero pasajero;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    private int altura;
    public NodoArbol(Pasajero pasajero) {
        this.pasajero = pasajero;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; // Altura inicial del nodo al insertarlo
    }
    public Pasajero getPasajero() {
        return pasajero;
    }
    public void setPasajero(Pasajero pasajero){
        this.pasajero = pasajero;
    }
    public NodoArbol getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }
    public NodoArbol getDerecho() {
        return derecho;
    }
    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
}
