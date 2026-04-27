package Figura2;

public class Pasaje {
    private long numero;
    private int asiento;
    private Viaje viaje;
    private Pasajero pasajero;
    private Venta venta;

    public Pasaje(long numero, int asiento, Pasajero pasajero, Venta venta, Viaje viaje) {
        this.numero=numero;
        this.asiento = asiento;
        this.pasajero = pasajero;
        this.venta = venta;
        this.viaje = viaje;
    }

    public long getNumero() {
        return numero;
    }

    public int getAsiento() {
        return asiento;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Venta getVenta() {
        return venta;
    }
}
