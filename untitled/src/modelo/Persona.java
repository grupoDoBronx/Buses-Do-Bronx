package modelo;

import utilidades.IdPersona;
import utilidades.Nombre;

public class Persona {
    private IdPersona id;
    private Nombre nombre;
    private String telefono;

    public Persona(IdPersona id, Nombre nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }



    public IdPersona getIdPersona() {
        return id;
    }

    public Nombre getNombreCompleto() {

        return nombre;
    }

    public void setNombreCompleto(Nombre nombreCompleto) {
        this.nombre = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String toString() {
        return "|\t" + id + " | " + nombre + " | " + telefono +"  |";
    }
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
