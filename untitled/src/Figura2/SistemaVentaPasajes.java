package Figura2;

import enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;

public class SistemaVentaPasajes {
    public boolean createCliente (IdPersona id, Nombre nom, String fono, String email){
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




}