package modelo;

import utilidades.*;

import java.util.ArrayList;

public class Terminal {
    private String nombre;
    private Direccion direccion;
    //agrego 2 array list para corregir el error
    private ArrayList<Viaje> llegadas;
    private ArrayList<Viaje> salidas;

    public Terminal(String nombre,Direccion direccion){
        this.nombre=nombre;
        this.direccion=direccion;

        llegadas=new ArrayList<>();
        salidas=new ArrayList<>();
    }
    public String getNombre(){
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void addLlegada(Viaje viaje){
        llegadas.add(viaje);
    }
    public void addSalida(Viaje viaje){
        salidas.add(viaje);
    }
    public Viaje[] getLlegadas(){
        return llegadas.toArray(new Viaje[0]);
    }
    public Viaje[] getSalidas(){
        return salidas.toArray(new Viaje[0]);
    }
}
