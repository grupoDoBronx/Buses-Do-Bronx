package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;

import java.util.ArrayList;

public class Tripulante extends Persona{
    private Direccion direccion;

    ArrayList<Viaje> viajes = new ArrayList<>();
    public Tripulante(IdPersona id, Nombre nombre, Direccion direccion) {
        super(id, nombre);
        this.direccion = direccion;
        ArrayList<Viaje> viajes;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void addViaje(Viaje viaje){
        for (Viaje via : viajes){
            if (via.equals(viaje)){
                return;
            }
        }
        viajes.add(viaje);
    }
    public int getNroViajes(){
        return viajes.size();
    }
}
