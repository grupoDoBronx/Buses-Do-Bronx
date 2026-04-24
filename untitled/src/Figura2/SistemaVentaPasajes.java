package Figura2;

import Figura1.IdPersona;
import Figura1.Nombre;
import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;

public class SistemaVentaPasajes {
    Main main = new Main();

    public boolean createCliente (IdPersona id, Nombre nombre, String fono, String email){
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

    public String [][] listVentas(){
        return listViajes();
    }
    public String [][] listViajes(){
        return listViajes();
    }


    public String[][] getHorariosDisponibles(LocalDate fechaViaje){
        return horariosDisponibles();
    }







}