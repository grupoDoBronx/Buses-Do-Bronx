package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import Figura1.Persona;

public class Cliente extends Persona {
    private String email;
    private Venta[] ventas;
    public Cliente(IdPersona id, Nombre nom,String email) {
        super(id,nom);
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void add(Venta venta){

    }
    public Venta [] getVentas(){

        return ventas;
    }
    @Override
    public String toString() {
        return getIdPersona() +" | " + getNombreCompleto();
    }

    @Override
    public boolean equals(Object otro) {

        return false;
    }
}
