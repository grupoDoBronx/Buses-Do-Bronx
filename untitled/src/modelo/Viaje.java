package modelo;

import java.sql.Time;
import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {

    private Date fecha;
    private Time hora;
    private Bus bus;
    private int precio;
    private int duracion;
    private Auxiliar aux;
    private ArrayList<Conductor> conductores;
    private Terminal sale;
    private Terminal llega;
    private Tripulante[] tripulantes;
    private ArrayList<Pasaje> pasajes = new ArrayList<>();

    public Viaje(Date fecha, Time hora, int precio, Bus bus,int duracion,Auxiliar aux,Conductor cond,Terminal sale,Terminal llega) {
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
        this.duracion=duracion;
        this.aux=aux;
        conductores = new ArrayList<>();
        conductores.add(cond);
        this.sale=sale;
        this.llega=llega;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    // no encontre una forma de hacer el metodo DateTime
    public LocalDateTime getFechaHoraTermino() {
        LocalTime horaL = hora.toLocalTime();
        LocalDate fechaL = fecha.toLocalDate();
        LocalDateTime salida = LocalDateTime.of(fechaL, horaL);

        return salida.plusMinutes(duracion);
    }

    public Venta[] getVentas() {
        ArrayList<Venta> ventas = new ArrayList<>();
        for (Pasaje p : pasajes) {
            if (!ventas.contains(p.getVenta())) {
                ventas.add(p.getVenta());
            }
        }
        return ventas.toArray(new Venta[0]);
    }

    public Bus getBus(){
        return bus;
    }

    public String[] getAsientos() {

        int totalAsientos = bus.getNroAsientos();
        String[] asientos = new String[totalAsientos];

        int num = 1;
        for (int i = 0; i < totalAsientos; i++) {
            asientos[i] = String.valueOf(i+1);

        }
        for (Pasaje p : pasajes) {
            int asiento = p.getAsiento();

            asientos[asiento-1] = "*";
        }
        return asientos;
    }



    public void addPasaje(Pasaje pasaje){
        if (existeDisponibilidad(1)) {
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
        return bus.getNroAsientos() - pasajes.size();
    }
    public boolean existeDisponibilidad(int nroAsientos){
        return getNroAsientosDisponibles() > 0;
    }
    //hecho por el harold
    public void addConductor(Conductor conductor) {
        if (!conductores.contains(conductor)) {
            conductores.add(conductor);
        }
    }
    public Tripulante[] getTripulantes() {
        ArrayList<Tripulante> lista = new ArrayList<>();
        lista.addAll(conductores);
        if (aux != null) {
            lista.add(aux);
        }

        return lista.toArray(new Tripulante[0]);
    }

    public Terminal getTerminalLlegada(){
        return llega;
    }

    public Terminal getTerminalSalida(){
        return sale;
    }
}