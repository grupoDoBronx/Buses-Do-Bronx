package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {

    private LocalDate fecha;
    private LocalTime hora;
    private Bus bus;
    private int precio;
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
        int filas = (int)Math.ceil(totalAsientos / 4.0);
        String[][] asientos = new String[totalAsientos][4];
        int num = 1;
        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < 4; j++) {

                if (num <= totalAsientos) {
                    asientos[i][j] = String.valueOf(num);
                    num++;
                } else {
                    asientos[i][j] = " ";
                }
            }
        }

        for (Pasaje p : pasajes) {

            int asiento = p.getAsiento();

            int fila = (asiento - 1) / 4;
            int col  = (asiento - 1) % 4;

            asientos[fila][col] = "*";
        }
        return asientos;
    }
    public void addPasaje(Pasaje pasaje){
        if (existeDisponibilidad()) {
            pasajes.add(pasaje);
        }
    }

    public String[][] getListaPasajeros(){
        String[][] listaPasajeros = new String[pasajes.size()][5];

        for (int i = 0; i < pasajes.size(); i++) {

            Pasaje p = pasajes.get(i);
            listaPasajeros[i][0] = String.valueOf(p.getAsiento());
            listaPasajeros[i][1] = p.getPasajero().getIdPersona().toString();
            listaPasajeros[i][2] = p.getPasajero().getNombreCompleto().toString();
            listaPasajeros[i][3] = p.getPasajero().getNomContacto().toString();
            listaPasajeros[i][4] = p.getPasajero().getFonoContacto();
        }

        return listaPasajeros;
    }
    public int getNroAsientosDisponibles(){
        int nroAsientosDisponibles;
        nroAsientosDisponibles = bus.getNroAsientos()-pasajes.size();
        return nroAsientosDisponibles;
    }
    public boolean existeDisponibilidad(){
        if (getNroAsientosDisponibles()>0){
            return true;
        }
        return false;
    }
}
