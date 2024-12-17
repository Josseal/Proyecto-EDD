public class Pasajero {
    private String nombre;
    private String telefono;
    private String direccion;
    public Pasajero(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    @Override
    public String toString() {
        return "Pasajero: " + nombre + ", Teléfono: " + telefono + ", Dirección: " + direccion;
    }
}
