package Figura2;
public class Bus {
    private String patente;
    private String marca;
    private String modelo;
    private int nroAsientos;

    public Bus(String patente, int nroAsientos) {
        this.patente= patente;
        this.nroAsientos= nroAsientos;
    }

    public String getPatente () {
        return patente;
    }
    public String getMarca(){
        return marca;
    }
    public void setMarca (String marca){
        this.marca=marca;
    }
    public String getModelo(){
        return modelo;
    }
    public int getNroAsientos () {
        return nroAsientos;
    }

}