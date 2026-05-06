package utilidades;

import java.util.Objects;

public class Direccion {
    private String calle;
    private int numero;
    private String comuna;

    public Direccion(String calle, String comuna, int numero) {
        this.calle = calle;
        this.comuna = comuna;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getComuna() {
        return comuna;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", comuna='" + comuna + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return numero == direccion.numero && Objects.equals(calle, direccion.calle) && Objects.equals(comuna, direccion.comuna);
    }

}
