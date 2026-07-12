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
    private ArrayList<Bus> buses;
    private ArrayList<Tripulante> tripulantes;

    public Empresa(String nombre, Rut rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.buses = new ArrayList<>();
        this.tripulantes = new ArrayList<>();
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
            if (b == bus){
                System.out.println("Este bus ya ha sido registrado");
            }
            buses.add(bus);
        }
    }
    public Bus[] getBuses(){
        return buses.toArray(new Bus[0]);
    }
    public boolean addConductor(IdPersona id, Nombre nom, Direccion dir){
        for (Tripulante t : tripulantes){
            IdPersona id2 = t.getIdPersona();
            if (id2 == id){
                return false;
            }
        }
        Tripulante tripulante = new Tripulante(id, nom, dir);
        tripulantes.add(tripulante);
        return true;
    }
    public boolean addAuxiliar(IdPersona id, Nombre nom, Direccion dir) {
        for (Tripulante t : tripulantes){
            IdPersona id2 = t.getIdPersona();
            if (id2 == id){
                return false;
            }
        }
        Tripulante tripulante = new Tripulante(id, nom, dir);
        tripulantes.add(tripulante);
        return true;
    }
    public Tripulante[] getTripulante(){
        return tripulantes.toArray(new Tripulante[0]);
    }
    public Venta[] getVentas(){
        ArrayList<Venta> listaVentas = new ArrayList<>();
        for(Bus bus : buses){
            for(Viaje viaje : bus.getViajes()){
                for(Venta venta : viaje.getVentas()){
                    listaVentas.add(venta);

                }
            }
        }

        return listaVentas.toArray(new Venta[0]);
    }
}
