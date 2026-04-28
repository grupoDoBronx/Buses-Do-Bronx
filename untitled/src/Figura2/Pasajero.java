package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import Figura1.Persona;

public class Pasajero extends Persona {
    private Nombre nomContacto;
    private String fonoContacto;

    public Pasajero(IdPersona id, Nombre nombre, String fono, Nombre nomContacto, String fonoContacto) {
        super(id, nombre, fono);
        this.nomContacto = nomContacto;
        this.fonoContacto = fonoContacto;
    }

    public String getFonoContacto() {
        return fonoContacto;
    }

    public void setFonoContacto(String fonoContacto) {
        this.fonoContacto = fonoContacto;
    }

    public Nombre getNomContacto() {
        return nomContacto;
    }

    public void setNomContacto(Nombre nomContacto) {
        this.nomContacto = nomContacto;
    }
    
}
