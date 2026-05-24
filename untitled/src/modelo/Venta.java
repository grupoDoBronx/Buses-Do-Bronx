package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import controlador.*;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private Cliente cliente;
    private Pago pago;
    private ArrayList<Pasaje> pasajes; //Javier: Ahora se almacenan los pasajes de la venta.

    public Venta(String idDocumento, TipoDocumento tipo, LocalDate fecha, Cliente cli) {
        this.idDocumento = idDocumento;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cliente = cli;
        this.pasajes= new ArrayList<>();//Se inicializa pasajes para poder añadir,
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

    public void createPasaje(int asiento, Viaje viaje, Pasajero pasajero,Venta venta){
        Pasaje pasaje = new Pasaje (asiento,pasajero, viaje,venta);
        pasajes.add(pasaje);
        viaje.addPasaje(pasaje);
    }
    public Pasaje[] getPasajes(){
        return pasajes.toArray(new Pasaje[0]);
    }
    public int getMonto(){
        int monto= 0;

        for (Pasaje p: pasajes){
            monto += p.getViaje().getPrecio();
        }
        return monto;
    }
    public int getMontoPagado(){
        if (pago == null) {
            return 0;
        }

        return pago.getMonto();
    }
    public boolean pagaMonto(){
        if (pago == null) {
            return false;
        }

        return pago.getMonto()==getMonto();
    }
    public boolean pagaMonto(long nroTarjeta){
        if (pago == null) {
            return false;
        }

        return pago.getMonto() == getMonto();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Venta)) {
            return false;
        }



        Venta otro = (Venta) obj;
        return idDocumento.equals(otro.idDocumento);
    }
    public String getTipoPago(){
        if (pago == null) {
            return null;
        }

        return pago.getClass().getSimpleName();
    }
}


