public class Pasajero extends Persona{
    private Nombre nomContacto;
    private String fonoContacto;

    public Pasajero(idPersona idPersona, Nombre nombre) {
        super(idPersona, nombre);
    }

    public Nombre nomContacto() {
        return nomContacto;
    }

    public void setNomContacto(Nombre nomContacto) {
        this.nomContacto = nomContacto;
    }

    public String getFonoContacto() {
        return fonoContacto;
    }

    public void setFonoContacto(String fonoContacto) {
        this.fonoContacto = fonoContacto;
    }

}
