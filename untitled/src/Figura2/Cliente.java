package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;

public class Cliente {
    private String email;
    private Nombre nom;
    private IdPersona id;

    public Cliente(IdPersona id, Nombre nom,String email) {
        this.id = id;
        this.email = email;
        this.nom = nom;
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
