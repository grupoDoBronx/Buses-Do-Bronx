package modelo;

import utilidades.IdPersona;
import utilidades.Nombre;

import java.util.ArrayList;

public class Cliente extends Persona {
    private String email;
    private ArrayList<Venta> ventas = new ArrayList<>();

    public Cliente(IdPersona id, Nombre nom,String email,String telefono) {
        super(id,nom,telefono);
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
