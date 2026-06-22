package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;

public class Auxiliar extends Tripulante{

    public Auxiliar(IdPersona id, Nombre nombre, Direccion direccion) {
        super(id, nombre, direccion );
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
