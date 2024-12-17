public class Destino {
    private String nombre;
    private String direccion;
    public Destino(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    @Override
    public String toString() {
        return "Destino: " + nombre + ", Direccion: " + direccion;
    }
}
