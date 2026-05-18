package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;
import utilidades.Rut;

import java.util.ArrayList;

public class Empresa {
    private Rut rut;
    private String nombre;
    private String url;
    ArrayList<Bus> buses = new ArrayList<>();
    public Empresa(String nombre, Rut rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.buses = new ArrayList<>();
    }

    public Rut getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void addBus(Bus bus){
        for (Bus b : buses){
            if (b.equals(bus)){
                return;
            }
            buses.add(bus);
        }
    }
    public Bus[] getBuses(){

    }
    public boolean addConductor(IdPersona id, Nombre nom, Direccion dir){
        return false;
    }
    public boolean addAuxiliar(IdPersona id, Nombre nom, Direccion dir) {
        return false;
    }
    public Tripulante[] getTripulante(){

    }
    public Venta[] getVentas(){

    }
}
