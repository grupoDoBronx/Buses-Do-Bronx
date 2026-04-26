package Figura2;

import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;

    public Venta(String idDocumento, TipoDocumento tipo, LocalDate fecha, Cliente cli) {
        this.idDocumento = idDocumento;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cliente = cli;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public Cliente getCliente(){

    }

    public void createPasaje(int asiento, Viaje viaje, Pasajero pasajero){

    }
    public Pasaje[] getPasaje(){

    }
    public int getMonto(){
        return monto;
    }
}
