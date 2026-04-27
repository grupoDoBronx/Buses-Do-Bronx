package Figura2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {

    private LocalDate fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;
    private ArrayList<Pasaje> pasajes = new ArrayList<>();
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
        int totalAsientos = bus.getNroAsientos();

        return asientos;
    }
    public void addPasaje(Pasaje pasaje){

    }

    public String[][] getListaPassajeros(){
        for (int i = 0; i < ; i++) {

        }
        return ;
    }
    public int getNroAsientosDisponibles(){
        int nroAsientosDisponibles;
        nroAsientosDisponibles = bus.getNroAsientos()-pasajes.size();
        return nroAsientosDisponibles;
    }
    public boolean existeDisponibilidad(){
        return getNroAsientosDisponibles()>0;
    }
}
