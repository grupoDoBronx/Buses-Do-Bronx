package modelo;

import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;

public class Tripulante extends Persona{
    private Direccion direccion;
    public Tripulante(IdPersona id, Nombre nombre, Direccion direccion, String telefono) {
        super(id, nombre, telefono);
        this.direccion = direccion;
    }

}
