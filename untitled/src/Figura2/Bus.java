package Figura2;

public class Bus {
    private String patente;
    private String marca;
    private String modelo;
    private int nroAsientos;
    private Viaje[] viajes;
    public Bus(int nroAsientos, String patente) {
        this.nroAsientos = nroAsientos;
        this.patente = patente;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNroAsientos() {
        return nroAsientos;
    }
    public void addViaje(Viaje viaje){

    }
}
