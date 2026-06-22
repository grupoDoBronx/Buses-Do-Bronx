package modelo;

import utilidades.IdPersona;
import utilidades.Nombre;

public class Pasajero extends Persona {
    private Nombre nomContacto;
    private String fonoContacto;

    public Pasajero(IdPersona id, Nombre nombre,String telefono, Nombre nomContacto) {
        super(id, nombre);
        this.fonoContacto = telefono;
        this.nomContacto = nomContacto;
    }
    public Nombre getNomContacto() {
        return nomContacto;
    }

    public void setNomContacto(Nombre nom) {
        this.nomContacto = nom;
    }
    public String getFonoContacto() {
        return fonoContacto;
    }

    public void setFonoContacto(String fono) {
        this.fonoContacto = fono;
    }



}