package controlador;

import excepciones.SistemaVentaPasajesException;
import modelo.*;
import utilidades.*;

import java.time.LocalTime;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

public class SistemaVentaPasajes {
    private static SistemaVentaPasajes instancia;

    private ArrayList<Cliente> clientes;
    private ArrayList<Pasajero> pasajeros;
    private ArrayList<Viaje> viajes;
    private ArrayList<Venta> ventas;

    private SistemaVentaPasajes() {
        clientes = new ArrayList<>();
        pasajeros = new ArrayList<>();
        viajes = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public static SistemaVentaPasajes getInstance() {
        if (instancia == null) {
            instancia = new SistemaVentaPasajes();
        }
        return instancia;
    }
    public void createCliente (IdPersona id, Nombre nom, String fono, String email){
        if (findCliente(id).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe cliente con el id indicado.");
        }
        Cliente nuevoCliente = new Cliente(id, nom,email);
        nuevoCliente.setTelefono(fono);
        clientes.add(nuevoCliente);
    }

    public void createPasajero (IdPersona id, Nombre nom, String fono, Nombre nombreContacto, String fonoContacto){
        if (findPasajero(id).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe pasajero con el id indicado.");
        }
        Pasajero nuevoPasajero = new Pasajero(id, nom, fono, nombreContacto, fonoContacto);
        pasajeros.add(nuevoPasajero);
    }

    public void createViaje(LocalDate fecha, LocalTime hora, int precio, int duracion, String patenteBus, IdPersona[] idsTripulantes, String[] nomComunas) {
        if (findViaje(fecha, hora, patenteBus).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe viaje con fecha, hora y patente de bus indicados.");
        }
        ControladorEmpresas ce = ControladorEmpresas.getInstance();

        Optional<Bus> busOpt = ce.findBus(patenteBus);
        if (busOpt.isEmpty()) {
            throw new SistemaVentaPasajesException("No existe bus con la patente indicada.");
        }
        Bus busViaje = busOpt.get();
        String rutEmpresa = busViaje.getEmpresa().getRut();
        Optional<Terminal> termSalidaOpt = ce.findTerminal(nomComunas[0]);
        Optional<Terminal> termLlegadaOpt = ce.findTerminal(nomComunas[1]);

        if (termSalidaOpt.isEmpty() || termLlegadaOpt.isEmpty()) {
            throw new SistemaVentaPasajesException("Uno o ambos terminales de los indicados no existen.");
        }
        Terminal terminalSalida = termSalidaOpt.get();
        Terminal terminalLlegada = termLlegadaOpt.get();

        Optional<Auxiliar> auxOpt = ce.findAuxiliar(idsTripulantes[0], rutEmpresa);
        if (auxOpt.isEmpty()) {
            throw new SistemaVentaPasajesException("El auxiliar indicado no existe o no pertenece a la empresa.");
        }
        Auxiliar auxiliarViaje = auxOpt.get();

        ArrayList<Conductor> conductoresViaje = new ArrayList<>();
        for (int i = 1; i < idsTripulantes.length; i++) {
            Optional<Conductor> condOpt = ce.findConductor(idsTripulantes[i], rutEmpresa);
            if (condOpt.isEmpty()) {
                throw new SistemaVentaPasajesException("Uno de los conductores que se indicaron no existe o no pertenece a la empresa.");
            }
            conductoresViaje.add(condOpt.get());
        }
        Viaje nuevoViaje = new Viaje(fecha, hora, precio, duracion, busViaje, auxiliarViaje, conductoresViaje, terminalSalida, terminalLlegada);
        viajes.add(nuevoViaje);
    }

    public void iniciaVenta(String idDocumento, TipoDocumento tipo, LocalDate fechaViaje, String comSalida, String comLlegada, IdPersona idCliente, int nroPasajes) {
        if (findVenta(idDocumento, tipo).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe ventas con el id y tipo de documento indicados.");
        }

        Optional<Cliente> clienteOpt = findCliente(idCliente);
        if (clienteOpt.isEmpty()) {
            throw new SistemaVentaPasajesException("No existe cliente con el id indicado.");
        }
        Cliente clienteVenta = clienteOpt.get();
        Venta nuevaVenta = new Venta(idDocumento, tipo, fechaViaje, clienteVenta);
        ventas.add(nuevaVenta);
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje, String comSalida, String comLlegada, int nroPasajes) {
        ArrayList<Viaje> viajesValidos = new ArrayList<>();

        for (Viaje viaje : viajes) {
            if (viaje.getFecha().isEqual(fechaViaje) &&
                    viaje.getTerminalSalida().getComuna().getNombre().equalsIgnoreCase(comSalida) &&
                    viaje.getTerminalLlegada().getComuna().getNombre().equalsIgnoreCase(comLlegada) &&
                    viaje.getnroAsientosDisponibles() >= nroPasajes) {

                viajesValidos.add(viaje);
            }
        }
        if (viajesValidos.isEmpty()) {
            return new String[0][4];
        }
        String[][] horarios = new String[viajesValidos.size()][4];

        for (int i = 0; i < viajesValidos.size(); i++) {
            Viaje viaje = viajesValidos.get(i);

            horarios[i][0] = viaje.getBus().getPatente();
            horarios[i][1] = viaje.getHora().toString();
            horarios[i][2] = String.valueOf(viaje.getPrecio());
            horarios[i][3] = String.valueOf(viaje.getnroAsientosDisponibles());
        }
        return horarios;
    }

    public String[][] listVentas() {
        if (ventas.isEmpty()) {
            return new String[0][7];
        }

        String[][] listaVentas = new String[ventas.size()][7];
        for (int i = 0; i < ventas.size(); i++) {
            Venta v = ventas.get(i);

            listaVentas[i][0] = v.getIdDocumento();
            listaVentas[i][1] = v.getTipo().name();
            listaVentas[i][2] = v.getFecha().toString();
            listaVentas[i][3] = v.getCliente().getIdPersona().toString();
            listaVentas[i][4] = v.getCliente().getNombreCompleto();
            listaVentas[i][5] = String.valueOf(v.getPasajes().length);
            listaVentas[i][6] = String.valueOf(v.getMonto());
        }
        return listaVentas;
    }

    public String[][] listViajes() {
        if (viajes.isEmpty()) {
            return new String[0][5];
        }
        String[][] listaViajes = new String[viajes.size()][5];
        for (int i = 0; i < viajes.size(); i++) {
            Viaje v = viajes.get(i);

            listaViajes[i][0] = String.valueOf(v.getFecha());
            listaViajes[i][1] = String.valueOf(v.getHora());
            listaViajes[i][2] = String.valueOf(v.getPrecio());
            listaViajes[i][3] = String.valueOf(v.getnroAsientosDisponibles());
            listaViajes[i][4] = v.getBus().getPatente();
        }
        return listaViajes;
    }

    public String[][] listPasajeros(LocalDate fecha, LocalTime hora, String patenteBus) {
        Optional<Viaje> viajeOpt = findViaje(fecha, hora, patenteBus);
        if (viajeOpt.isEmpty()) {
            return new String[0][4];
        }
        return viajeOpt.get().getListaPasajeros();
    }

    //Faltan los métodos: ListAsientoDeViaje
    //getMontoVenta
    //getNombrePasajero
    //vendePasaje
    //pagaVenta
    //pagaVenta(long)


    private Optional<Cliente> findCliente(IdPersona id) {
        for (Cliente c : clientes) {
            if (c.getIdPersona().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private Optional<Venta> findVenta(String idDocumento, TipoDocumento tipoDocumento) {
        for (Venta v : ventas) {
            if (v.getIdDocumento().equals(idDocumento) && v.getTipo().equals(tipoDocumento)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    private Optional<Viaje> findViaje(LocalDate fecha, LocalTime hora, String patenteBus) {
        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha)
                    && v.getHora().equals(hora)
                    && v.getBus().getPatente().equals(patenteBus)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    private Optional<Pasajero> findPasajero(IdPersona idPersona) {
        for (Pasajero p : pasajeros) {
            if (p.getIdPersona().equals(idPersona)) return Optional.of(p);
        }
        return Optional.empty();
    }
}