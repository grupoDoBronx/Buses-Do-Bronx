package modelo;

import utilidades.Direccion;

public class Terminal {
    private String nombre;
    private Direccion direccion;

    public Terminal(Direccion direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
