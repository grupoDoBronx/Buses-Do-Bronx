package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import Figura1.Persona;

import java.util.ArrayList;

public class Cliente extends Persona {
    private String email;
    private ArrayList<Venta> ventas = new ArrayList<>();

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
        ventas.add(venta);
    }
    public Venta [] getVentas(){
        Venta [] regresarVentas = new Venta[ventas.size()];
        return ventas.toArray(regresarVentas);
    }

}
