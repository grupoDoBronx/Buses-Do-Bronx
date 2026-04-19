import enums.Tratamiento;

public class Nombre {
    private Tratamiento tratamiento;
    private String nombre;
    private String apellido_parterno;
    private String apellido_materno;

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_parterno() {
        return apellido_parterno;
    }

    public void setApellido_parterno(String apellido_parterno) {
        this.apellido_parterno = apellido_parterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }


    @Override
    public String toString() {
        return tratamiento + " " + nombre + " " + apellido_parterno + " " + apellido_materno  + ' ';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
