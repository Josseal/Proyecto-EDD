public class Taxi {
    private String numeroPlaca;
    private String modelo;
    private String conductor;
    public Taxi(String numeroPlaca, String modelo, String conductor) {
        this.numeroPlaca = numeroPlaca;
        this.modelo = modelo;
        this.conductor = conductor;
    }
    public String getNumeroPlaca() {
        return numeroPlaca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getConductor() {
        return conductor;
    }
    @Override
    public String toString() {
        return "Numero de placa: " + numeroPlaca + ", modelo: " + modelo + ", Conductor: " + conductor;
    }
}
