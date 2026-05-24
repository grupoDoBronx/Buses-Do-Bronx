
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        if(findCliente(id) != null){
            throw new SistemaVentaPasajesException("Ya existe cliente con el id indicado");
        }
        Cliente nuevoCliente = new Cliente(id, nombre,fono,email);
        clientes.add(nuevoCliente);
    }
}