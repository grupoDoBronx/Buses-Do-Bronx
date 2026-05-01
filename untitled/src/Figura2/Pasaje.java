package Figura2;

public class Pasaje {
    private long numero;
    private int asiento;
    private Viaje viaje;
    private Pasajero pasajero;
    private Venta venta;

    public Pasaje( int asiento, Pasajero pasajero, Venta venta, Viaje viaje) {
        this.asiento = asiento;
        this.pasajero = pasajero;
        this.venta = venta;
        this.viaje = viaje;
    }

    public int getNumero() {
        return (int)numero;
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
