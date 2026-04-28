package Figura2;
import enums.TipoDocumento;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private Cliente cliente;
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

    public void createPasaje(int asiento, Viaje viaje, Pasajero pasajero){
        Pasaje pasaje=new Pasaje (asiento, viaje, pasajero);
        pasajes.add(pasaje);
    }
    public Pasaje[] getPasaje(){
        return pasajes.toArray(new Pasaje[0]);
    }
    public int getMonto(){
        int monto= 0;

        for (Pasaje p: pasajes){
            monto += p.getViaje().getPrecio();
        }
        return monto;
    }
}