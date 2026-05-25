package modelo;

import java.util.ArrayList;

public class Bus {
    private String patente;
    private String marca;
    private String modelo;
    private Empresa empresa;
    private int nroAsientos;
    ArrayList<Viaje> viajes = new ArrayList<>();

    public Bus(int nroAsientos, String patente,Empresa empresa) {
        this.nroAsientos = nroAsientos;
        this.patente = patente;
        this.viajes = new ArrayList<>();
        this.empresa = empresa;
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
    public Empresa getEmpresa(){
        return empresa;
    }
    public void addViaje(Viaje viaje){
        for (Viaje via : viajes){
            if (via.equals(viaje)){
                return;
            }
        }
        viajes.add(viaje);
    }
    public Viaje[] getViajes(){
        return viajes.toArray(new Viaje[0]);
    }
}
