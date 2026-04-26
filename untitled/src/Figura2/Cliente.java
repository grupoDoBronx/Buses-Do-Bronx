package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import Figura1.Persona;

import java.util.ArrayList;

public class Cliente extends Persona {
    private String email;
    private ArrayList<Venta> venta = new ArrayList<>();
    Venta [] ventas;
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

}
