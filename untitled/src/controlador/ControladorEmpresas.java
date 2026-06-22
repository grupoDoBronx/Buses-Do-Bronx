package controlador;

import excepciones.SVPException;
import modelo.*;
import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;
import utilidades.Rut;


import java.util.ArrayList;
import java.util.Date;

import java.util.Optional;

public class ControladorEmpresas {
    ArrayList<Rut> ruts = new ArrayList<>();
    ArrayList<Terminal> terminales = new ArrayList<>();
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Empresa> empresas = new ArrayList<>();
    ArrayList<Tripulante> tripulantes = new ArrayList<>();

    private static ControladorEmpresas instance;
    private ControladorEmpresas(){

    }
    public static ControladorEmpresas getInstance(){
        if (instance == null){
            return instance = new ControladorEmpresas();
        }
        return instance;
    }

    public void createEmpresa(Rut rut, String nombre,String url){

        if (findEmpresa(rut).isPresent()){
            throw new SVPException("Ya existe empresa con el rut indicado");
        }
        Empresa empresa = new Empresa(nombre,rut);
        empresas.add(empresa);
        System.out.println("\n...:::: Empresa guardada exitosamente ::::....");

    }
    public void createBus (String patente, String marca, String modelo, int numeroDeAsientos, Rut rutEmp){

        if (findEmpresa(rutEmp).isEmpty()){
            throw new SVPException("No existe empresa con el rut indicado");
        }
        if(findBus(patente).isPresent()){
            throw new SVPException("Ya existe bus con la patente indicada");
        }


        Empresa empresacontrata = findEmpresa(rutEmp).get();
        Bus nuevoBus = new Bus(numeroDeAsientos, patente, empresacontrata);
        buses.add(nuevoBus);
        System.out.print("\n...:::: Bus guardado exitosamente ::::....");
    }
    public void createTerminal(String nombre, Direccion direccion){

        if (findTerminal(nombre).isPresent()){
            throw new SVPException("Ya existe terminal en la comuna indicada");
        }
        Terminal terminal = new Terminal(nombre, direccion);
        terminales.add(terminal);
        System.out.println("\n...:::: Terminal guardado exitosamente ::::....");


    }
    public void hireConductorForEmpresa (Rut rutEmp, IdPersona id, Nombre nom, Direccion dir){

        if (findEmpresa(rutEmp).isEmpty()){
            throw new SVPException("No existe empresa con el rut indicado");
        }
        if (findConductor(id,rutEmp).isPresent()) {
            throw new SVPException("Ya está contratado auxiliar/conductor con el id dado en la empresa señalada");
        }
        Conductor conductor = new Conductor(id,nom,dir);
        tripulantes.add(conductor);
        System.out.println("...:::: Auxiliar contratado exitosamente ::::....");


    }
    public void hireAuxiliarForEmpresa (Rut rutEmp,IdPersona id, Nombre nom, Direccion dir) {


        if (findEmpresa(rutEmp).isEmpty()){
            throw new SVPException("No existe empresa con el rut indicado");
        }
        if (findAuxiliar(id,rutEmp).isPresent()) {
            throw new SVPException("Ya está contratado auxiliar/conductor con el id dado en la empresa señalada");
        }
        Auxiliar auxiliar = new Auxiliar(id,nom,dir);
        tripulantes.add(auxiliar);
        System.out.println("...:::: Auxiliar contratado exitosamente ::::....");
    }
    public String[][] listEmpresas(){
        ArrayList<String []> lista = new ArrayList<>();
        for (Empresa e: empresas){
            String[] listado= {e.getRut().toString(),e.getNombre(),e.getUrl(),String.valueOf(e.getTripulante().length),
                    String.valueOf(e.getBuses().length),String.valueOf(e.getVentas().length)};
            lista.add(listado);
        }
        return lista.toArray(new String[0][0]);
    }
    public String[][] listLlegadasSalidasTerminal(String nombre, Date fecha){
        try {
            ArrayList<String[]> listaLlegadasSalidasTerminal = new ArrayList<>();
            if (findTerminal(nombre).isEmpty()) {
                throw new SVPException("No existe un terminal con el nombre dado ***");
            }

            for (Viaje v : findTerminal(nombre).get().getSalidas()){
                String[] listado = {v.getHora().toString(),v.getBus().getPatente(),};
                listaLlegadasSalidasTerminal.add(listado);
            }
            return listaLlegadasSalidasTerminal.toArray(new String[0][0]);
        }catch (SVPException e){
            System.out.println("*** ERROR: " + e.getMessage());
            return null;
        }
    }
    public String[][] listVentasEmpresa(Rut rut){
        try {
            ArrayList<String[]> lisVentasEmp = new ArrayList<>();
            if (findEmpresa(rut).isEmpty()){
                throw new SVPException("No existe empresa con el rut indicado");
            }

            for (Venta v : findEmpresa(rut).get().getVentas()){
                String[] fila ={v.getFecha().toString(), v.getTipo().toString(), String.valueOf(v.getMontoPagado()), v.getTipoPago()};
                lisVentasEmp.add(fila);
            }
            return lisVentasEmp.toArray(new String[0][0]);

        }catch (SVPException e){
            System.out.println("*** ERROR : " + e.getMessage());
            return null;
        }

    }
    protected Optional <Empresa> findEmpresa(Rut rut){
        for (Empresa e : empresas){
            if (e.getRut().equals(rut)) return Optional.of(e) ;
        }
        return Optional.empty();
    }
    protected Optional<Terminal> findTerminal(String nombre){
        for (Terminal t : terminales){
            if (t.getNombre().equals(nombre)) return Optional.of(t);
        }
        return Optional.empty();
    }
    protected Optional<Terminal> findTerminalPorComuna(String comuna){
        for (Terminal t : terminales){
            if (t.getNombre().equals(comuna)) return Optional.of(t);
        }
        return Optional.empty();
    }
    protected Optional <Bus> findBus(String patente) {
        for (Bus b : buses) {
            if (b.getPatente().equals(patente)) return Optional.of(b);
        }
        return Optional.empty();
    }
    protected Optional<Conductor> findConductor(IdPersona idPersona, Rut rutEmpresa){
        for (Empresa e : empresas){
            if (e.getRut().equals(rutEmpresa)){
                for (Tripulante t : tripulantes){
                    if ((t instanceof Conductor) && t.getIdPersona().equals(idPersona)) {
                        return Optional.of((Conductor)t);}
                }
            }
        }
        return Optional.empty();
    }
    protected Optional<Auxiliar> findAuxiliar(IdPersona idPersona, Rut rutEmpresa){
        for (Empresa e : empresas){
            if (e.getRut().equals(rutEmpresa)){
                for (Tripulante t : tripulantes){
                    if ((t instanceof Auxiliar) && t.getIdPersona().equals(idPersona)) {
                        return Optional.of((Auxiliar)t);}
                }
            }
        }
        return Optional.empty();
    }


}
