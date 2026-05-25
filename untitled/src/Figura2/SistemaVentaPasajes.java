
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class SistemaVentaPasajes {

    private static SistemaVentaPasajes instancia;

    private ArrayList<Cliente> clientes;
    private ArrayList<Pasajero> pasajeros;
    private ArrayList<Viaje> viajes;
    private ArrayList<Venta> venta;

    private SistemaVentaPasajes() {
        clientes = new ArrayList<>();
        pasajeros = new ArrayList<>();
        viajes = new ArrayList<>();
        venta = new ArrayList<>();
    }

    public static SistemaVentaPasajes getInstance() {
        if (instancia == null) {
            instancia = new SistemaVentaPasajes();
        }
        return instancia;
    }
    public void createCliente (IdPersona id, Nombre nombre, String fono, String email){
        if(findCliente(id).isPresent()){
            throw new SistemaVentaPasajesException("Ya existe cliente con el id indicado");
        }
        Cliente nuevoCliente = new Cliente(id, nombre,fono,email);
        clientes.add(nuevoCliente);
    }
    public void createPasajero (IdPersona id, Nombre nombre, String fono, Nombre nombreContacto, String fonoContacto){
        if(findPasajero(id).isPresent()){
            throw new SistemaVentaPasajesException("Ya existe pasajero con el id ingresado");
        }

        Pasajero nuevoPasajero = new Pasajero(id, nombre, fono, nombreContacto, fonoContacto);
        pasajeros.add(nuevoPasajero);
    }
    public void createViaje(LocalDate fecha, LocalTime hora, int precio, int duracion, String patenteBus, IdPersona[] idsTripulantes, String[] comunas) {
        if (findViaje(fecha, hora, patenteBus).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe viaje con fecha, hora y patente de bus indicados");
        }

        Optional<Bus> busOpt = ControladorEmpresas.getInstance().findBus(patenteBus);
        if (!busOpt.isPresent()) {
            throw new SistemaVentaPasajesException("No existe bus con la patente indicada");
        }
    }
    public void iniciaVenta(String idDocumento, TipoDocumento tipo, Date fechaViaje, String comSalida, String comLlegada, IdPersona idCliente, int nroPasajes) {
        if (findVenta(idDocumento, tipo).isPresent()) {
            throw new SistemaVentaPasajesException("Ya existe venta con el id y tipo de documento indicados");
        }
        if(!findCliente(idCliente).isPresent()){
            throw new SistemaVentaPasajesException("No existe cliente con el id indicado");
        }
    }
    public Optional<String> getNombrePasajero(IdPersona idPasajero){
        Optional<Pasajero> pasajeroOpt = findPasajero(idPasajero);
        if(!pasajeroOpt.isPresent()){
            return Optional.empty();
        }
        return pasajeroOpt.getNomContacto.toString();
    }

    public Optional<Integer> getMontoVenta(String idDocumento, TipoDocumento tipo){
        Optional<Venta> ventaOpt = findVenta(idDocumento, tipo);
        if(!ventaOpt.isPresent()){
            return Optional.empty();
        }
        return ventaOpt.getMonto();
    }
}