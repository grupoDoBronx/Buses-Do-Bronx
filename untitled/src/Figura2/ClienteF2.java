package Figura2;

import java.util.ArrayList;
import java.util.List;

public class ClienteF2 extends Persona {
    private String email;
    private List<Venta> ventas;

    public Cliente(String id, String nom, String email) {
        super(id, nom);
        this.email = email;
        this.ventas = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addVenta(Venta venta) {
        ventas.add(venta);
    }
    public List<Venta> getVentas() {
        return ventas;
    }
}