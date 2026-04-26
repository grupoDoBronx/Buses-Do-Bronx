package Figura2;

public class Pasajero extends Persona {
    private Nombre nomContacto;
    private String fonoContacto;

    public Pasajero(IdPersona id, Nombre nombreCompleto, String telefono, Nombre nomContacto, String fonoContacto){
        super (id, nombreCompleto);
        setTelefono(telefono);
        this.nomContacto= nomContacto;
        this.fonoContacto = fonoContacto;
    }

    public Nombre getNomContacto() {
        return nomContacto;
    }

    public void setNomContacto(Nombre nom){
        this.nomContacto = nom;
    }


}
