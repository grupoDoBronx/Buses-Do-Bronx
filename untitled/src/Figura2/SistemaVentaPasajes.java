package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SistemaVentaPasajes {
    Main main = new Main();

    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Pasajero> pasajeros = new ArrayList<>();
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Viaje> viajes = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();

    public boolean createCliente (IdPersona id, Nombre nombre, String telefono, String email){
        if(findCliente(id) != null){
            return false;
        }
        Cliente nuevoCliente = new Cliente(id, nombre, telefono, email);
        clientes.add(nuevoCliente);
        return true;
    }

    public boolean createPasajero (IdPersona id, Nombre nombre, String telefono, Nombre nombreContacto, String telefonoContacto){
        if(findPasajero(id) != null){
            return false;
        }

        Pasajero nuevoPasajero = new Pasajero(id, nombre, telefono, nombreContacto, telefonoContacto);
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

        Bus bus = findBus(patente);
        if (bus == null) return false;

        for (Viaje v:  viajes){
            if(v.getFecha.equals(fecha) && v.getHora.equals(hora)){
                return false;
            }
        }
        Viaje nuevoViaje = new Viaje(fecha, hora, precio, patenteBus);
        viajes.add(nuevoViaje);
        return true;
    }

    public boolean iniciaVenta(String idDocumento, String tipoDoc, String fechaVenta, String idCliente) {
        if (findVenta(idDocumento) != null || findCliente(idCliente) == null) {
            return false;
        }
        Cliente cliente = findCliente(idCliente);

        Venta nuevaVenta = new Venta(idDocumento, tipoDoc, fechaVenta,cliente);
        return ventas.add(nuevaVenta);
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje){
        String[] horariosDisponibles;
        return horariosDisponibles;
    }

    public String[][] listAsientosDeViaje(LocalDate fecha,LocalTime hora,String patBus){
        for (int i = 0; i < ; i++) {

        }
    }

    public int getMontoVenta(String idDocumento, TipoDocumento tipo){
        int montoVenta = 0;
        return montoVenta;
    }

    public String getNombrePasajero(IdPersona idPasajero){

        String nombrePasajero =
        return nombrePasajero
    }

    public boolean vendePasaje(String idDocumento, String tipoDocumento, String fecha, String hora, String patente, String idPasajero) {
        Venta venta = findVenta(idDocumento);
        Viaje viaje = findViaje(fecha, hora, patente);
        Pasajero pasajero = findPasajero(idPasajero);

        if (venta == null || viaje == null || pasajero == null) {
            return false;
        }
        venta.createPasaje(viaje, pasajero);
        return true;
    }

    public String [][] listVentas(){
        return listViajes();
    }
    public String [][] listViajes(){
        return listViajes();
    }
    public String [][] listPasajeros(LocalDate fecha,LocalTime hora, String patBus){

    }

    private Cliente findCliente(String id) {
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

    private Venta findVenta(String id) {
        for (Venta v : ventas) {
            if (v.getIdDocumento().equals(id)) return v;
        }
        return null;
    }

    private Pasajero findPasajero(String id) {
        for (Pasajero p : pasajeros) {
            if (p.getIdPersona().equals(id)) return p;
        }
        return null;
    }

    private Viaje findViaje(String fecha, String hora, String patente) {
        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }

}