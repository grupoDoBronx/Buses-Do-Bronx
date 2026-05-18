package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;

import java.util.ArrayList;

public class Conductor extends Tripulante{


    public Conductor(IdPersona id, Nombre nombre, Direccion direccion) {
        super(id, nombre, direccion);
    }
    @Override
    public void addViaje(Viaje viaje){
        for (Viaje via : viajes){
            if (via.equals(viaje)){
                return;
            }
        }
        super.viajes.add(viaje);
    }
    @Override
    public int getNroViajes(){
        return super.viajes.size();
    }
}
