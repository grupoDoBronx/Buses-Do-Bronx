package controlador;


import excepciones.SVPException;
import persistencia.IOISVP;
import utilidades.*;
import modelo.TipoDocumento;
import modelo.*;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Time;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class SistemaVentaPasajes implements Serializable {


    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Pasajero> pasajeros = new ArrayList<>();
    ArrayList<Viaje> viajes = new ArrayList<>();
    ArrayList<Venta> venta = new ArrayList<>();
    // establece el tipo formato de las fechas
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");

    private ControladorEmpresas controlEmpre;
    private IOISVP IOISVP;
    public static SistemaVentaPasajes instance;
    private SistemaVentaPasajes(){
        controlEmpre = ControladorEmpresas.getInstance();
        IOISVP = IOISVP.getInstance();
    }
    public static SistemaVentaPasajes getInstance(){
        if (instance == null){
            return instance = new SistemaVentaPasajes();
        }
        return instance;
    }
    public void createCliente (IdPersona id, Nombre nombre,String fono, String email){
        try {
            if(findCliente(id).isPresent()){
                throw new RuntimeException("Ya existe cliente con el id indicado");
            }else {
                Cliente nuevoCliente = new Cliente(id, nombre, email);
                clientes.add(nuevoCliente);
                System.out.println("\n...:::: Cliente guardado exitosamente ::::....");
            }
        }catch (SVPException e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void createPasajero (IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){
        try {
            if(findPasajero(id) != null){
                throw new SVPException("Ya existe pasajero con el id indicado");
            }
            Pasajero nuevoPasajero = new Pasajero(id, nom, fono, nomContacto);
            pasajeros.add(nuevoPasajero);
        }catch (SVPException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }



    public void createViaje (Date fecha, Time hora, int precio, int duracion,String patenteBus, IdPersona[] idTripulantes, String[] nomComunas){

        Optional<Bus> bus = controlEmpre.findBus(patenteBus);
        Optional<Terminal> terminalSalida = controlEmpre.findTerminalPorComuna(nomComunas[0]);
        Optional<Terminal> terminalLlegada = controlEmpre.findTerminalPorComuna(nomComunas[1]);
        try {
            if (bus.isEmpty()){
                throw new SVPException("No existe bus con la patente indicada");
            }

            for (Viaje v:  viajes){
                if(v.getFecha().equals(fecha) && v.getHora().equals(hora)){
                    throw new SVPException("Ya existe viaje con fecha, hora y patente de bus indicados");
                }
            }
            if (controlEmpre.findTerminal(nomComunas[0]).isEmpty()){
                throw new SVPException("No existe terminal de salida en la comuna indicada");
            }
            if (controlEmpre.findTerminal(nomComunas[1]).isEmpty()){
                throw new SVPException("No existe terminal de llegada en la comuna indicada");
            }
            Terminal terminal1 = terminalSalida.get();
            Terminal terminal2 = terminalLlegada.get();
            Bus busviaje = bus.get();
            Auxiliar auxiliar = (Auxiliar) idTripulantes[0];
            Conductor conductor1 = null;
            Conductor conductor2 = null;


            if (idTripulantes.length==1){
                conductor1 = (Conductor) idTripulantes[1];
                conductor2 = (Conductor) idTripulantes[2];
            }else {
                conductor1 = (Conductor) idTripulantes[1];
            }

            Viaje viaje = new Viaje(fecha,hora, precio,busviaje,duracion,auxiliar,conductor1,terminal1,terminal2);
            bus.get().addViaje(viaje);
            viajes.add(viaje);
            System.out.println("\n...:::: Viaje guardado exitosamente ::::....");
        }catch (SVPException e){
            System.out.println("ERROR : " +e.getMessage());
            return;
        }

    }
    

    public void iniciaVenta(String idDoc, TipoDocumento tipo, Date fechaViaje, String comSalida, String comLlegada ,IdPersona idCliente, int nroPasajes) {
        // ya no alcanzo a terminar todas las cosas y dudo que en lo que tengo de tiempo avance con algo funcional, prefiero que quede asi a que no compile despues y me quede sin tiempo
        // al menos se nota la logica que planeaba hacer pero aunque estuve toda la semana casi sin dormir no lo logre
        try {
            if (findVenta(idDoc, tipo).isPresent()) {
                throw new SVPException("throw new SistemaVentaPasajesException(\"\");");
            }
            if (findCliente(idCliente).isEmpty()){
                throw new SVPException("");
            }
            Optional<Cliente> cliente = findCliente(idCliente);

            Venta nuevaVenta = new Venta(idDoc, tipo, fechaViaje, cliente.orElse(null));

            venta.add(nuevaVenta);
            System.out.println(":::: Listado de horarios disponibles");
            System.out.println("     *----------*----------*----------*----------*");
            System.out.println("     | BUS      |   SALIDA |    VALOR | ASIENTOS |");
            System.out.println("     *----------*----------*----------*----------*\n\n");
            getHorariosDisponibles(fechaViaje);
            String horaSt = getHorariosDisponibles(fechaViaje)[1][1];
            String fechaSt = fechaFormato.format(fechaViaje);
            String patenteSt = getHorariosDisponibles(fechaViaje)[1][0];

            vendePasaje(idDoc,fechaSt,horaSt,patenteSt,1,null,null);
        }catch (SVPException e){
            System.out.println("ERROR : " + e.getMessage());
        }
    }

    public String[][] getHorariosDisponibles(Date fechaViaje){

        ArrayList<Viaje> vs = new ArrayList<>();
        for (Viaje v : viajes) {
            if (v.getFecha().equals(fechaViaje)) {
                vs.add(v);
            }
        }

        String[][] resultado = new String[vs.size()][4];
        for (int i = 0; i < vs.size(); i++) {
            Viaje v = vs.get(i);
            resultado[i][0] = v.getBus().getPatente();
            resultado[i][1] = String.valueOf(v.getHora());
            resultado[i][2] = String.valueOf(v.getPrecio());
            resultado[i][3] = String.valueOf(v.getNroAsientosDisponibles()); //
        }
        return resultado;
    }

    public String[] listAsientosDeViaje(Date fecha, Time hora, String patenteBus) {
        String fechaString = fechaFormato.format(fecha);
        String horaSting = hora.toString();
        Optional <Viaje> viaje = findViaje(fechaString, horaSting, patenteBus);

        if (viaje == null) {
            return new String[0];
        }

        String[] asientos = viaje.get().getAsientos();

        String[] estadosAsientos = new String[asientos.length];

        for (int i = 0; i < asientos.length; i++) {
            estadosAsientos[i] = asientos[i];
        }

        return estadosAsientos;
    }

    public int getMontoVenta(String idDocumento, TipoDocumento tipo){
       Optional <Venta> v = findVenta(idDocumento, tipo);
       if(v.isEmpty()){
           return 0;
       }
       return v.get().getMonto();
    }

    public String getNombrePasajero(IdPersona idPasajero){
        Optional <Pasajero> p = findPasajero(idPasajero);
        if(p == null){
            return null;
        }

        return p.getClass().toString();
    }

    public void vendePasaje(String idDocumento, String fecha, String hora, String patente,int asiento, IdPersona idPasajero, TipoDocumento tipoDocumento) {

        for (Venta v: venta) {
            if (findVenta(idDocumento, tipoDocumento).isEmpty()) {
                throw new SVPException("No existe venta con el id y tipo de documento indicados\n");
            }
        }
        for (Cliente c: clientes){
            if (findCliente(idPasajero).isEmpty()){
                throw new SVPException("No existe pasajero con el id indicado");
            }
        }
        for (Viaje v: viajes){
            if (findViaje(fecha,hora,patente).isEmpty()){
                throw new SVPException("No existe viaje con la fecha, hora y patente de bus indicados");
            }
        }


    }
    public void pagaVenta (String idDocumento, TipoDocumento tipo){
        try {
            if (findVenta(idDocumento, tipo).isEmpty()){
                throw new SVPException("No existe venta con el id y tipo de documento indicados");
            }
            System.out.println("  ...:::: Venta realizada exitosamente ::::....");
        }catch (SVPException e){
            System.out.println("ERROR : ");
            return;
        }
    }
    public void pagaVenta (String idDocumento, TipoDocumento tipo,long nroTarjeta){
        try {
            if (findVenta(idDocumento, tipo).isEmpty()){
                throw new SVPException("No existe venta con el id y tipo de documento indicados");
            }
            System.out.println("  ...:::: Venta realizada exitosamente ::::....");
        }catch (SVPException e){
            System.out.println("ERROR : ");
            return;
        }
    }

    public String[][] listVentas() {
        if (venta == null || venta.isEmpty()) {
            return new String[0][0];
        }

        String[][] matrizVentas = new String[venta.size()][7];

        for (int i = 0; i < venta.size(); i++) {
            Venta v = venta.get(i);
            Cliente c = v.getCliente();

            matrizVentas[i][0] = v.getIdDocumento();
            matrizVentas[i][1] = v.getTipo().toString();
            matrizVentas[i][2] = v.getFecha().toString();
            matrizVentas[i][3] = c.getIdPersona().toString();
            matrizVentas[i][4] = c.getNombreCompleto().toString();
            matrizVentas[i][5] = String.valueOf(v.getPasajes().length);
            matrizVentas[i][6] = String.valueOf(this.getMontoVenta(v.getIdDocumento(), v.getTipo()));
        }

        return matrizVentas;
    }

    public String[][] listViajes() {
        String[][] datos = new String[viajes.size()][4];
            for (int i = 0; i < viajes.size(); i++) {
                Viaje v = viajes.get(i);
                datos[i][0] = String.valueOf(v.getFecha());
                datos[i][1] = String.valueOf(v.getHora());
                datos[i][2] = String.valueOf(v.getPrecio());
                datos[i][3] = String.valueOf(v.getNroAsientosDisponibles());
                datos[i][4] = v.getBus().getPatente();
            }
            return datos;
    }
    public String [][] listPasajeros(Date fecha,Time hora, String patenteBus){
        String fechaSt = fechaFormato.format(fecha);
        String horaSt = hora.toString();
        Optional <Viaje> encontrarViaje = findViaje(fechaSt, horaSt, patenteBus);
        if(encontrarViaje == null){
            return new String[0][0];
        }
        return encontrarViaje.get().getListaPasajeros();
    }
    public void readDatosIniciales(){
        try {
            Tratamiento tratamientoCli= null;
            Tratamiento tratamientoPas= null;
            Nombre nom1 = new Nombre();
            Nombre nom2 = new Nombre();
            Rut rutEmpresa = null;
            Rut rutPersona = null;
            ArrayList<String> linea = new ArrayList<>();
            for (Object obj: IOISVP.readDatosIniciales()){
                linea.add(Arrays.toString(IOISVP.readDatosIniciales()));
            }
            int tipoALeer = 0;
            for (String obj: linea){
                String[] datos = obj.split(";");
                if (obj.startsWith("+")){
                    tipoALeer++;
                    continue;
                }
                switch (tipoALeer){
                    case 0:
                        if (datos[2].equals("SR")){
                            tratamientoCli = Tratamiento.SR;
                        } else if (datos[2].equals("SRA")) {
                            tratamientoCli = Tratamiento.SRA;
                        }
                        if (linea.get(0).equals("C") && linea.get(1).equals("P")){
                            rutPersona = Rut.of(datos[1]);
                            //Datos del cliente
                            nom1.setTratamiento(tratamientoCli);
                            nom1.setNombre(datos[3]);
                            nom1.setApellido_parterno(datos[4]);
                            nom1.setApellido_materno(datos[5]);
                            createCliente(rutPersona,nom1,datos[7], datos[8]);
                            //datos del pasajero
                            if (datos[9].equals("SR")){
                                tratamientoPas = Tratamiento.SR;
                            } else if (datos[9].equals("SRA")) {
                                tratamientoPas = Tratamiento.SRA;
                            }
                            nom2.setTratamiento(tratamientoPas);
                            nom2.setNombre(datos[10]);
                            nom2.setApellido_parterno(datos[11]);
                            nom2.setApellido_materno(datos[12]);
                            createPasajero(rutPersona,nom1,datos[7],nom2,datos[13]);
                        }else if (linea.get(0).equals("C")){
                            if (datos[2].equals("SR")){
                                tratamientoCli = Tratamiento.SR;
                            } else if (datos[2].equals("SRA")) {
                                tratamientoCli = Tratamiento.SRA;
                            }
                            if (linea.get(0).equals("C") && linea.get(1).equals("P")) {
                                rutPersona = Rut.of(datos[1]);
                                //Datos del cliente
                                nom1.setTratamiento(tratamientoCli);
                                nom1.setNombre(datos[3]);
                                nom1.setApellido_parterno(datos[4]);
                                nom1.setApellido_materno(datos[5]);
                                createCliente(rutPersona, nom1, datos[7], datos[8]);
                            }
                        }else if (linea.get(0).equals("P")){
                                rutPersona = Rut.of(datos[1]);
                                //Datos del cliente
                                nom1.setTratamiento(tratamientoCli);
                                nom1.setNombre(datos[3]);
                                nom1.setApellido_parterno(datos[4]);
                                nom1.setApellido_materno(datos[5]);

                                //datos del pasajero
                                if (datos[8].equals("SR")){
                                    tratamientoPas = Tratamiento.SR;
                                } else if (datos[8].equals("SRA")) {
                                    tratamientoPas = Tratamiento.SRA;
                                }
                                nom2.setTratamiento(tratamientoPas);
                                nom2.setNombre(datos[10]);
                                nom2.setApellido_parterno(datos[10]);
                                nom2.setApellido_materno(datos[11]);
                                createPasajero(rutPersona,nom1,datos[7],nom2,datos[12]);
                        }
                        break;
                    case 1:
                        rutEmpresa = Rut.of(datos[0]);
                        controlEmpre.createEmpresa(rutEmpresa,datos[1],datos[2]);
                        break;
                    case 2:
                        rutEmpresa = Rut.of(datos[1]);

                        rutPersona = Rut.of(datos[9]);
                        if (datos[2].equals("SR")){
                            tratamientoPas = Tratamiento.SR;
                        } else if (datos[2].equals("SRA")) {
                            tratamientoPas = Tratamiento.SRA;
                        }
                        nom1.setTratamiento(tratamientoCli);
                        nom1.setNombre(datos[3]);
                        nom1.setApellido_parterno(datos[4]);
                        nom1.setApellido_materno(datos[5]);
                        Direccion direccion = new Direccion(datos[6],datos[7],Integer.parseInt(datos[8]));
                        if (linea.get(0).equals("C")){
                            controlEmpre.hireConductorForEmpresa(rutEmpresa,rutPersona,nom1,direccion);
                        } else if (linea.get(0).equals("A")) {
                            controlEmpre.hireAuxiliarForEmpresa(rutEmpresa,rutPersona,nom1,direccion);
                        }
                        break;
                    case 3:
                        Direccion direccionTerminal = new Direccion(datos[1],datos[2],Integer.parseInt(datos[3]));
                        controlEmpre.createTerminal(datos[0], direccionTerminal);
                        break;
                    case 4:
                        rutEmpresa = Rut.of(datos[4]);
                        controlEmpre.createBus(datos[0],datos[1],datos[2],Integer.parseInt(datos[3]),rutEmpresa );
                        break;
                    case 5:
                        Date fechaviaje = (Date) fechaFormato.parse(datos[0]);
                        LocalTime hora = LocalTime.parse(datos[1]);
                        Time horaViaje = Time.valueOf(hora);
                        IdPersona[] tripulantes = new IdPersona[0];
                        tripulantes[0] = Rut.of(datos[5]);
                        tripulantes[1] = Rut.of(datos[6]);
                        String[] nombreComunas = new String[0];
                        nombreComunas[0] = datos[7];
                        nombreComunas[1] = datos[8];
                        createViaje(fechaviaje,horaViaje,Integer.parseInt(datos[2]),Integer.parseInt(datos[3]),datos[4],tripulantes,nombreComunas);
                        break;
                    default:
                }


            }
        }catch (SVPException e){
            
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveDatosSistema(){


    }
    public void readDatosSistema(){

    }



    private Optional <Cliente> findCliente(IdPersona id) {
        return clientes.stream().filter(c -> c.getIdPersona().equals(id)).findFirst();
    }

    private Optional <Venta> findVenta(String idDocumento, TipoDocumento tipoDoc) {
        return venta.stream().filter(v -> v.getIdDocumento().equals(idDocumento) && v.getTipo().equals(tipoDoc)).findFirst();
    }

    private Optional <Pasajero> findPasajero(IdPersona idPersona) {
        return pasajeros.stream().filter(p -> p.getIdPersona().equals(idPersona)).findFirst();
    }

    private Optional <Viaje> findViaje(String  fecha, String  hora, String patente) {
        return viajes.stream().filter(v -> v.getFecha().equals(fecha) && v.getHora().equals(hora) && v.getBus().getPatente().equals(patente)).findFirst();
    }

}
