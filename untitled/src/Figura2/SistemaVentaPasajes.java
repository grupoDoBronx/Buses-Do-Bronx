package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;

public class SistemaVentaPasajes {
    Main main = new Main();

    public boolean createCliente (IdPersona id, Nombre nombre, String fono, String email){
        findCliente()
        return true;
    }


    public boolean createPasajero (IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){

        return true;
    }


    public boolean createBus (String patente, String marca, String modelo, int nroAsientos){
        return true;
    }


    public boolean createViaje (LocalDate fecha, LocalTime hora, int precio, String patBus){
        return true;
    }


    public boolean iniciaVenta (String idDoc, TipoDocumento tipo, LocalDate fechaVenta, IdPersona idCliente){
        return true;
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
    public boolean vendePasaje(String idDoc,LocalDate fecha,LocalTime hora,String patBus,int asiento){
        findVenta();
        findCliente();
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
    private Cliente findCliente(IdPersona id){
        return findCliente(id);
    }
    private Venta findVenta(String idDocumento,TipoDocumento tipoDocumento){

    }
    private Bus findBus(String patente){

    }
    private Viaje findViaje(String fecha, String hora, String patenteBus){

    }
    private Pasajero findPasajero(IdPersona idPersona){

    }









}