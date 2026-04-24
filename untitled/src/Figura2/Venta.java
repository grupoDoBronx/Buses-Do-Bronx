package Figura2;

import enums.TipoDocumento;

import java.time.LocalDate;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private final Cliente cliente;
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
        return cliente;
    }

    public void createPasaje(int asiento, Viaje viaje, Pasajero pasajero){

    }
    public Pasaje[] getPasaje(){
        Pasaje[] pasaje;
        return pasaje;
    }
    public int getMonto(){
        int monto= 0;
        return monto;
    }
}
