package utilidades;

public class Rut implements IdPersona {
    private int numero;
    private char dv;
    // recive el rut y lo desglosa en dos partes
    //la antes del guion y despues del guion ademas de quitar los espacios o puntos si es que el usuario los escribe al ingresar el rut del cliente
    public Rut(int num, char dv) {
        this.numero = num;
        this.dv = dv;
    }

    public int getNumero() {
        return numero;
    }

    public char getDv() {
        return dv;
    }
    public static Rut of(String rutConDv){
        rutConDv = rutConDv.replace(".","");
        String[] numCom = rutConDv.split("-");
        int numero = Integer.parseInt(numCom[0]);
        char dv = numCom[1].toUpperCase().charAt(0);
        return new Rut(numero,dv);
    }

    @Override
    public String toString() {
        return numero + "-" + dv;
    }

    @Override
    public boolean equals(Object otro) {

        if (!(otro instanceof Rut)) {
            return false;
        }

        Rut r = (Rut) otro;

        return this.numero == r.numero && this.dv == r.dv;
    }
}
