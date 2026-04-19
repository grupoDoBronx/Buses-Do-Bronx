public class Persona {
    private idPersona idPersona;
    private Nombre nombreCompleto;
    private String telefono;

    public Persona(idPersona idPersona, Nombre nombre) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombre;
    }

    public idPersona getIdPersona() {
        return idPersona;
    }

    public Nombre getNombreCompleto() {

        return nombreCompleto;
    }

    public void setNombreCompleto(Nombre nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "|\t" + idPersona + " | " + nombreCompleto + " | " + telefono +"  |";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
