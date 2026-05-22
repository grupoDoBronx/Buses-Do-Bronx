package controlador;


import excepciones.SistemaVentaPasajesException;
import utilidades.IdPersona;
import utilidades.Nombre;
import modelo.TipoDocumento;
import modelo.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Time;

import java.util.ArrayList;
import java.util.Optional;

public class SistemaVentaPasajes implements ViajesPorFecha {

    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Pasajero> pasajeros = new ArrayList<>();
    ArrayList<Viaje> viajes = new ArrayList<>();
    ArrayList<Venta> venta = new ArrayList<>();
    // establece el tipo formato de las fechas
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    public void createCliente (IdPersona id, Nombre nombre,String fono, String email){
        try {
            if(findCliente(id).isPresent()){
                throw new RuntimeException("Ya existe cliente con el id indicado");
            }else {
                Cliente nuevoCliente = new Cliente(id, nombre, email);
                clientes.add(nuevoCliente);
                System.out.println("\n...:::: Cliente guardado exitosamente ::::....");
            }
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void createPasajero (IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){
        try {
            if(findPasajero(id) != null){
                throw new SistemaVentaPasajesException("Ya existe pasajero con el id indicado");
            }
            Pasajero nuevoPasajero = new Pasajero(id, nom, fono, nomContacto);
            pasajeros.add(nuevoPasajero);
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }



    public void createViaje (Date fecha, Time hora, int precio, int duracion,String patenteBus, IdPersona[] idTripulantes, String[] nomComunas){
        ControladorEmpresas controlEmpre = new ControladorEmpresas();
        Optional<Bus> bus = controlEmpre.findBus(patenteBus);
        try {
            if (bus.isEmpty()){

            }

            for (Viaje v:  viajes){
                if(v.getFecha().equals(fecha) && v.getHora().equals(hora)){

                }
            }
            Viaje nuevoViaje = new Viaje(fecha, hora, precio, bus.orElse(null));
            viajes.add(nuevoViaje);
            System.out.println("\n...:::: Viaje guardado exitosamente ::::....");
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR : " +e.getMessage());
            return;
        }

    }
    

    public void iniciaVenta(String idDoc, TipoDocumento tipo, Date fechaViaje, String comSalida, String comLlegada ,IdPersona idCliente, int nroPasajes) {

        try {
            if (findVenta(idDoc, tipo) != null|| findCliente(idCliente) == null) {

            }
            Optional<Cliente> cliente = findCliente(idCliente);

            Venta nuevaVenta = new Venta(idDoc, tipo, fechaViaje, cliente.orElse(null));

            venta.add(nuevaVenta);
            System.out.println(":::: Listado de horarios disponibles");
            System.out.println("     *----------*----------*----------*----------*");
            System.out.println("     | BUS      |   SALIDA |    VALOR | ASIENTOS |");
            for () {

            }
            System.out.println("     *----------*----------*----------*----------*\n\n");

        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR : " + e.getMessage());
        }
    }

    public String[][] getHorariosDisponibles(Date fechaViaje){

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

    public String[] listAsientosDeViaje(Date fecha, Time hora, String patenteBus) {
        String fechaString = fechaFormato.format(fecha);
        String horaSting = hora.toString();
        Viaje viaje = findViaje(fechaString, horaSting, patenteBus);

        if (viaje == null) {
            return new String[0];
        }

        String[] asientos = viaje.getAsientos();

        String[] estadosAsientos = new String[asientos.length];

        for (int i = 0; i < asientos.length; i++) {
            estadosAsientos[i] = asientos[i];
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

    public void vendePasaje(String idDocumento, String fecha, String hora, String patente,int asiento, IdPersona idPasajero, TipoDocumento tipoDocumento) {
        Venta venta = findVenta(idDocumento, tipoDocumento);
        Viaje viaje = findViaje(fecha, hora,patente);

        Pasajero pasajero = findPasajero(idPasajero);

        if (venta == null || viaje == null || pasajero == null) {

        }
        venta.createPasaje(asiento,viaje,pasajero,venta);

    }
    public void pagaVenta (String idDocumento, TipoDocumento tipo){

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
    public String [][] listPasajeros(Date fecha,Time hora, String patenteBus){
        String fechaSt = fechaFormato.format(fecha);
        String horaSt = hora.toString();
        Viaje encontrarViaje = findViaje(fechaSt, horaSt, patenteBus);
        if(encontrarViaje == null){
            return new String[0][0];
        }
        return encontrarViaje.getListaPasajeros();
    }

    private Optional <Cliente> findCliente(IdPersona id) {
        for (Cliente c : clientes) {
            if (c.getIdPersona().equals(id)) return Optional.of(c);
        }
        return null;
    }



    private Venta findVenta(String idDocumento, TipoDocumento tipoDoc) {
        for (Venta v : venta) {
            if (v.getIdDocumento().equals(idDocumento) && v.getTipo().equals(tipoDoc)) return v;
        }
        return null;
    }

    private Pasajero findPasajero(IdPersona idPersona) {
        for (Pasajero p : pasajeros) {
            if (p.getIdPersona().equals(idPersona)) return p;
        }
        return null;
    }

    private Viaje findViaje(String  fecha, String  hora, String patente) {

        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }

}
