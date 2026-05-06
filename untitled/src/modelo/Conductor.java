package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;

public class Conductor extends Tripulante{
    public Conductor(IdPersona id, Nombre nombre, Direccion direccion, String telefono) {
        super(id, nombre, direccion, telefono);
    }
    public void addViaje(Viaje viaje){

    }
    public int getNroViajes(){
        int nroViajes= 0;
        return nroViajes;
    }
}
