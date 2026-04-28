package Figura2;


import Figura1.IdPersona;
import Figura1.Nombre;
import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SistemaVentaPasajes implements ViajesPorFecha{

    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Pasajero> pasajeros = new ArrayList<>();
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Viaje> viajes = new ArrayList<>();
    ArrayList<Venta> venta = new ArrayList<>();

    public boolean createCliente (IdPersona id, Nombre nombre, String fono, String email){
        if(findCliente(id) != null){
            return false;
        }
        Cliente nuevoCliente = new Cliente(id, nombre, fono, email);
        clientes.add(nuevoCliente);
        return true;
    }

    public boolean createPasajero (IdPersona id, Nombre nombre, String fono, Nombre nombreContacto, String fonoContacto){
        if(findPasajero(id) != null){
            return false;
        }

        Pasajero nuevoPasajero = new Pasajero(id, nombre, fono, nombreContacto, fonoContacto);
        pasajeros.add(nuevoPasajero);
        return true;
    }

    public boolean createBus (String patente, String marca, String modelo, int numeroDeAsientos){
        if(findBus(patente) != null){
            return false;
        }

        Bus nuevoBus = new Bus(patente, marca, modelo, numeroDeAsientos);
        buses.add(nuevoBus);
        return true;
    }

    public boolean createViaje (LocalDate fecha, LocalTime hora, int precio, String patenteBus){

        Bus bus = findBus(patenteBus);
        if (bus == null) {return false;}

        for (Viaje v:  viajes){
            if(v.getFecha().equals(fecha) && v.getHora().equals(hora)){
                return false;
            }
        }

        Viaje nuevoViaje = new Viaje(fecha, hora, precio, bus);
        viajes.add(nuevoViaje);
        return true;
    }

    public boolean iniciaVenta(String idDocumento, TipoDocumento tipoDoc, LocalDate fechaVenta, IdPersona idCliente) {
        if (findVenta(idDocumento, tipoDoc) != null|| findCliente(idCliente) == null) {
            return false;
        }
        Cliente cliente = findCliente(idCliente);

        Venta nuevaVenta = new Venta(idDocumento, tipoDoc, fechaVenta,cliente);
        return venta.add(nuevaVenta);
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje){

        ArrayList<Viaje> vs = new ArrayList<>();
        for (Viaje v : viajes) {
            if (v.getFecha().equals(fechaViaje)) {
                vs.add(v);
            }
        }

        String[][] resultado = new String[vs.size()][4];
        for (int i = 0; i < vs.size(); i++) {
            Viaje v = vs.get(i);
            resultado[i][0] = v.getBus().getPatente();
            resultado[i][1] = String.valueOf(v.getHora());
            resultado[i][2] = String.valueOf(v.getPrecio());
            resultado[i][3] = String.valueOf(v.getNroAsientosDisponibles()); //
        }
        return resultado;
    }

    public String[] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patenteBus) {

        Viaje viaje = findViaje(fecha, hora, patenteBus);

        if (viaje == null) {
            return new String[0];
        }

        String[][] asientos = viaje.getAsientos();

        String[] estadosAsientos = new String[asientos.length];

        for (int i = 0; i < asientos.length; i++) {
            estadosAsientos[i] = asientos[i][1];
        }

        return estadosAsientos;
    }

    public int getMontoVenta(String idDocumento, TipoDocumento tipo){
       Venta v = findVenta(idDocumento, tipo);
       if(v == null){
           return 0;
       }
       return v.getMonto();
    }

    public String getNombrePasajero(IdPersona idPasajero){
        Pasajero p = findPasajero(idPasajero);
        if(p == null){
            return null;
        }

        return p.getNombreCompleto().toString();
    }

    public boolean vendePasaje(String idDocumento, String fecha, String hora, String patente,int asiento, String idPasajero) {
        Venta venta = findVentaId(idDocumento);
        Viaje viaje = findViajeString(fecha, hora, patente);
        Pasajero pasajero = findPasajeroString(idPasajero);

        if (venta == null || viaje == null || pasajero == null) {
            return false;
        }
        venta.createPasaje(asiento,viaje, pasajero);
        return true;
    }

    public String[][] listVentas() {
        if (venta == null || venta.isEmpty()) {
            return new String[0][0];
        }

        String[][] matrizVentas = new String[venta.size()][7];

        for (int i = 0; i < venta.size(); i++) {
            Venta v = venta.get(i);
            Cliente c = v.getCliente();

            matrizVentas[i][0] = v.getIdDocumento();
            matrizVentas[i][1] = v.getTipo().toString();
            matrizVentas[i][2] = v.getFecha().toString();
            matrizVentas[i][3] = c.getIdPersona().toString();
            matrizVentas[i][4] = c.getNombreCompleto().toString();
            matrizVentas[i][5] = String.valueOf(v.getPasaje().length);
            matrizVentas[i][6] = String.valueOf(this.getMontoVenta(v.getIdDocumento(), v.getTipo()));
        }

        return matrizVentas;
    }

    public String[][] listViajes() {
        String[][] datos = new String[viajes.size()][4];
            for (int i = 0; i < viajes.size(); i++) {
                Viaje v = viajes.get(i);
                datos[i][0] = String.valueOf(v.getFecha());
                datos[i][1] = String.valueOf(v.getHora());
                datos[i][2] = String.valueOf(v.getPrecio());
                datos[i][3] = String.valueOf(v.getNroAsientosDisponibles());
                datos[i][4] = v.getBus().getPatente();
            }
            return datos;
    }
    public String [][] listPasajeros(LocalDate fecha,LocalTime hora, String patenteBus){
        Viaje encontrarViaje = findViaje(fecha, hora, patenteBus);
        if(encontrarViaje == null){
            return new String[0][0];
        }
        return encontrarViaje.getListaPasajeros();
    }

    private Cliente findCliente(IdPersona id) {
        for (Cliente c : clientes) {
            if (c.getIdPersona().equals(id)) return c;
        }
        return null;
    }

    private Bus findBus(String patente) {
        for (Bus b : buses) {
            if (b.getPatente().equals(patente)) return b;
        }
        return null;
    }
    private Venta findVentaId(String idDocumento){
        for(Venta v : venta){
            if(v.getIdDocumento().equals(idDocumento));
            return v;
        }
        return null;
    }

    private Venta findVenta(String idDocumento, TipoDocumento tipoDoc) {
        for (Venta v : venta) {
            if (v.getIdDocumento().equals(idDocumento) && v.getTipo().equals(tipoDoc)) return v;
        }
        return null;
    }
    private Pasajero findPasajeroString (String idPersona) {
        for (Pasajero p : pasajeros) {
            if (p.getIdPersona().equals(idPersona)) return p;
        }
        return null;
    }

    private Pasajero findPasajero(IdPersona idPersona) {
        for (Pasajero p : pasajeros) {
            if (p.getIdPersona().equals(idPersona)) return p;
        }
        return null;
    }
    private Viaje findViajeString(String fecha, String hora, String patente) {

        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }

    private Viaje findViaje(LocalDate fecha, LocalTime hora, String patente) {

        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String[][] mostrarViajesFecha() {
        String[][] datos = new String[viajes.size()][3];
        for (int i = 0; i < viajes.size(); i++) {
            Viaje v = viajes.get(i);
            datos[i][0] = String.valueOf(v.getHora());
            datos[i][1] = String.valueOf(v.getPrecio());
            datos[i][2] = String.valueOf(v.getNroAsientosDisponibles());
            datos[i][3] = v.getBus().getPatente();
        }
        return datos;
    }
}
