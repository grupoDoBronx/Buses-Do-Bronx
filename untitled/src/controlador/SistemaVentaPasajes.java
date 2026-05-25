package controlador;

import excepciones.SistemaVentaPasajesException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import modelo.Auxiliar;
import modelo.Bus;
import modelo.Cliente;
import modelo.Conductor;
import modelo.Pasajero;
import modelo.Terminal;
import modelo.TipoDocumento;
import modelo.Venta;
import modelo.Viaje;
import utilidades.IdPersona;
import utilidades.Nombre;

public class SistemaVentaPasajes {
    ArrayList<Cliente> clientes = new ArrayList();
    ArrayList<Pasajero> pasajeros = new ArrayList();
    ArrayList<Viaje> viajes = new ArrayList();
    ArrayList<Venta> venta = new ArrayList();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    public static SistemaVentaPasajes instance;

    public static SistemaVentaPasajes getInstance() {
        return instance == null ? (instance = new SistemaVentaPasajes()) : instance;
    }

    public void createCliente(IdPersona id, Nombre nombre, String fono, String email) {
        try {
            if (this.findCliente(id).isPresent()) {
                throw new RuntimeException("Ya existe cliente con el id indicado");
            }

            Cliente nuevoCliente = new Cliente(id, nombre, email);
            this.clientes.add(nuevoCliente);
            System.out.println("\n...:::: Cliente guardado exitosamente ::::....");
        } catch (SistemaVentaPasajesException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void createPasajero(IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto) {
        try {
            if (this.findPasajero(id) != null) {
                throw new SistemaVentaPasajesException("Ya existe pasajero con el id indicado");
            }

            Pasajero nuevoPasajero = new Pasajero(id, nom, fono, nomContacto);
            this.pasajeros.add(nuevoPasajero);
        } catch (SistemaVentaPasajesException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void createViaje(Date fecha, Time hora, int precio, int duracion, String patenteBus, IdPersona[] idTripulantes, String[] nomComunas) {
        ControladorEmpresas controlEmpre = new ControladorEmpresas();
        Optional<Bus> bus = controlEmpre.findBus(patenteBus);
        Optional<Terminal> terminalSalida = controlEmpre.findTerminalPorComuna(nomComunas[0]);
        Optional<Terminal> terminalLlegada = controlEmpre.findTerminalPorComuna(nomComunas[1]);

        try {
            if (bus.isEmpty()) {
                throw new SistemaVentaPasajesException("No existe bus con la patente indicada");
            } else {
                for(Viaje v : this.viajes) {
                    if (v.getFecha().equals(fecha) && v.getHora().equals(hora)) {
                        throw new SistemaVentaPasajesException("");
                    }
                }

                Terminal terminal1 = (Terminal)terminalSalida.get();
                Terminal terminal2 = (Terminal)terminalLlegada.get();
                Bus busviaje = (Bus)bus.get();
                Auxiliar auxiliar = (Auxiliar)idTripulantes[0];
                Conductor conductor1 = null;
                Conductor conductor2 = null;
                if (idTripulantes.length == 1) {
                    conductor1 = (Conductor)idTripulantes[1];
                    conductor2 = (Conductor)idTripulantes[2];
                } else {
                    conductor1 = (Conductor)idTripulantes[1];
                }

                Viaje viaje = new Viaje(fecha, hora, precio, busviaje, duracion, auxiliar, conductor1, terminal1, terminal2);
                ((Bus)bus.get()).addViaje(viaje);
                this.viajes.add(viaje);
                System.out.println("\n...:::: Viaje guardado exitosamente ::::....");
            }
        } catch (SistemaVentaPasajesException e) {
            System.out.println("ERROR : " + e.getMessage());
        }
    }

    public void iniciaVenta(String idDoc, TipoDocumento tipo, Date fechaViaje, String comSalida, String comLlegada, IdPersona idCliente, int nroPasajes) {
        try {
            if (this.findVenta(idDoc, tipo) != null || this.findCliente(idCliente) == null) {
                throw new SistemaVentaPasajesException("");
            }

            Optional<Cliente> cliente = this.findCliente(idCliente);
            Venta nuevaVenta = new Venta(idDoc, tipo, fechaViaje, (Cliente)cliente.orElse((Object)null));
            this.venta.add(nuevaVenta);
            System.out.println(":::: Listado de horarios disponibles");
            System.out.println("     *----------*----------*----------*----------*");
            System.out.println("     | BUS      |   SALIDA |    VALOR | ASIENTOS |");

            for(Viaje var11 : this.viajes) {
                ;
            }

            System.out.println("     *----------*----------*----------*----------*\n\n");
        } catch (SistemaVentaPasajesException e) {
            System.out.println("ERROR : " + e.getMessage());
        }

    }

    public String[][] getHorariosDisponibles(Date fechaViaje) {
        ArrayList<Viaje> vs = new ArrayList();

        for(Viaje v : this.viajes) {
            if (v.getFecha().equals(fechaViaje)) {
                vs.add(v);
            }
        }

        String[][] resultado = new String[vs.size()][4];

        for(int i = 0; i < vs.size(); ++i) {
            Viaje v = (Viaje)vs.get(i);
            resultado[i][0] = v.getBus().getPatente();
            resultado[i][1] = String.valueOf(v.getHora());
            resultado[i][2] = String.valueOf(v.getPrecio());
            resultado[i][3] = String.valueOf(v.getNroAsientosDisponibles());
        }

        return resultado;
    }

    public String[] listAsientosDeViaje(Date fecha, Time hora, String patenteBus) {
        String fechaString = this.fechaFormato.format(fecha);
        String horaSting = hora.toString();
        Optional<Viaje> viaje = this.findViaje(fechaString, horaSting, patenteBus);
        if (viaje == null) {
            return new String[0];
        } else {
            String[] asientos = ((Viaje)viaje.get()).getAsientos();
            String[] estadosAsientos = new String[asientos.length];

            for(int i = 0; i < asientos.length; ++i) {
                estadosAsientos[i] = asientos[i];
            }

            return estadosAsientos;
        }
    }

    public int getMontoVenta(String idDocumento, TipoDocumento tipo) {
        Optional<Venta> v = this.findVenta(idDocumento, tipo);
        return v.isEmpty() ? 0 : ((Venta)v.get()).getMonto();
    }

    public String getNombrePasajero(IdPersona idPasajero) {
        Optional<Pasajero> p = this.findPasajero(idPasajero);
        return p == null ? null : p.getClass().toString();
    }

    public void vendePasaje(String idDocumento, String fecha, String hora, String patente, int asiento, IdPersona idPasajero, TipoDocumento tipoDocumento) {
        Optional<Venta> venta = this.findVenta(idDocumento, tipoDocumento);
        Optional<Viaje> viaje = this.findViaje(fecha, hora, patente);
        Optional<Pasajero> pasajero = this.findPasajero(idPasajero);
        if (venta == null || viaje == null || pasajero == null) {
            ;
        }
    }

    public void pagaVenta(String idDocumento, TipoDocumento tipo) {
        try {
            if (this.findVenta(idDocumento, tipo).isEmpty()) {
                throw new SistemaVentaPasajesException("No existe venta con el id y tipo de documento indicados");
            } else {
                System.out.println("  ...:::: Venta realizada exitosamente ::::....");
            }
        } catch (SistemaVentaPasajesException var4) {
            System.out.println("ERROR : ");
        }
    }

    public void pagaVenta(String idDocumento, TipoDocumento tipo, long nroTarjeta) {
        try {
            if (this.findVenta(idDocumento, tipo).isEmpty()) {
                throw new SistemaVentaPasajesException("No existe venta con el id y tipo de documento indicados");
            } else {
                System.out.println("  ...:::: Venta realizada exitosamente ::::....");
            }
        } catch (SistemaVentaPasajesException var6) {
            System.out.println("ERROR : ");
        }
    }

    public String[][] listVentas() {
        if (this.venta != null && !this.venta.isEmpty()) {
            String[][] matrizVentas = new String[this.venta.size()][7];

            for(int i = 0; i < this.venta.size(); ++i) {
                Venta v = (Venta)this.venta.get(i);
                Cliente c = v.getCliente();
                matrizVentas[i][0] = v.getIdDocumento();
                matrizVentas[i][1] = v.getTipo().toString();
                matrizVentas[i][2] = v.getFecha().toString();
                matrizVentas[i][3] = c.getIdPersona().toString();
                matrizVentas[i][4] = c.getNombreCompleto().toString();
                matrizVentas[i][5] = String.valueOf(v.getPasajes().length);
                matrizVentas[i][6] = String.valueOf(this.getMontoVenta(v.getIdDocumento(), v.getTipo()));
            }

            return matrizVentas;
        } else {
            return new String[0][0];
        }
    }

    public String[][] listViajes() {
        String[][] datos = new String[this.viajes.size()][4];

        for(int i = 0; i < this.viajes.size(); ++i) {
            Viaje v = (Viaje)this.viajes.get(i);
            datos[i][0] = String.valueOf(v.getFecha());
            datos[i][1] = String.valueOf(v.getHora());
            datos[i][2] = String.valueOf(v.getPrecio());
            datos[i][3] = String.valueOf(v.getNroAsientosDisponibles());
            datos[i][4] = v.getBus().getPatente();
        }

        return datos;
    }

    public String[][] listPasajeros(Date fecha, Time hora, String patenteBus) {
        String fechaSt = this.fechaFormato.format(fecha);
        String horaSt = hora.toString();
        Optional<Viaje> encontrarViaje = this.findViaje(fechaSt, horaSt, patenteBus);
        return encontrarViaje == null ? new String[0][0] : ((Viaje)encontrarViaje.get()).getListaPasajeros();
    }

    private Optional<Cliente> findCliente(IdPersona id) {
        for(Cliente c : this.clientes) {
            if (c.getIdPersona().equals(id)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    private Optional<Venta> findVenta(String idDocumento, TipoDocumento tipoDoc) {
        for(Venta v : this.venta) {
            if (v.getIdDocumento().equals(idDocumento) && v.getTipo().equals(tipoDoc)) {
                return Optional.of(v);
            }
        }

        return Optional.empty();
    }

    private Optional<Pasajero> findPasajero(IdPersona idPersona) {
        for(Pasajero p : this.pasajeros) {
            if (p.getIdPersona().equals(idPersona)) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    private Optional<Viaje> findViaje(String fecha, String hora, String patente) {
        for(Viaje v : this.viajes) {
            if (v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)) {
                return Optional.of(v);
            }
        }

        return Optional.empty();
    }
}
