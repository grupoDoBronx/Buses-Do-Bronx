package Figura2;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;

    public Viaje(LocalDate fecha, LocalTime hora, int precio, Bus bus) {
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public Bus getBus(){
        return bus;
    }
    public String[][] getAsientos(){
        return asientos;
    }
    public void addPasaje(Pasaje pasaje){

    }
    public String[][] getListaPassajeros(){
        for (int i = 0; i < ; i++) {
            for (int j = 0; j < ; j++) {
                
            }
        }
        return listaPasajeros;
    }
    public boolean existeDisponibilidad(){
        for (int i = 0; i < ; i++) {
            for (int j = 0; j < ; j++) {
                
            }    
        }
        
        return false;
    }
    public getNroAsientosDisponibles(){
        int nroAsientosDisponibles = 0;
        return nroAsientosDisponibles;
    }
}
