package controlador;

import excepciones.SistemaVentaPasajesException;
import modelo.*;
import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;
import utilidades.Rut;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class ControladorEmpresas {
    ArrayList<Rut> ruts = new ArrayList<>();
    ArrayList<Terminal> terminales = new ArrayList<>();
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Empresa> empresas = new ArrayList<>();
    public void createEmpresa(Rut rut, String nombre,String url){
        try {
            throw new SistemaVentaPasajesException("Ya existe empresa con el rut indicado");
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    public void createBus (String patente, String marca, String modelo, int numeroDeAsientos, Rut rutEmp){
        try {
            if (findEmpresa(rutEmp).isPresent()){
                throw new SistemaVentaPasajesException("No existe empresa con el rut indicado");
            }
            if(findBus(patente).isPresent()){
                throw new SistemaVentaPasajesException("Ya existe bus con la patente indicada");
            }
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        Bus nuevoBus = new Bus(numeroDeAsientos, patente);
        buses.add(nuevoBus);
    }
    public void createTerminal(String nombre, Direccion direccion){
        try {
            if (){
                throw new SistemaVentaPasajesException("Ya existe terminal en la comuna indicada");
            }


        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    public void hireConstructorForEmpresa (Rut rutEmp, IdPersona id, Nombre nom, Direccion dir){
        try {
            throw new SistemaVentaPasajesException("");
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    public void hireAuxiliarForEmpresa (Rut rutEmp,IdPersona id, Nombre nom, Direccion dir) {
        try {

            if (){
                throw new SistemaVentaPasajesException("No existe empresa con el rut indicado");
            }
            throw new SistemaVentaPasajesException("Ya está contratado auxiliar/conductor con el id dado en la empresa señalada");
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
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
    public String[][] listLLegadasSalidasTerminal(String nombre, Date fecha){
        try {
            throw new SistemaVentaPasajesException("No existe terminal con el nombre indicado");
        }catch (SistemaVentaPasajesException e){
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    public String[][] listVentasEmpresa(Rut rut){

        return null;
    }
    protected Optional <Empresa> findEmpresa(Rut rut){
        for (Rut r : ruts){
            if (Objects.equals(r.getDv(), rut)) return Optional.of(r) ;
        }
        return null;
    }
    protected Optional<Terminal> findTerminal(String nombre){
        return null;
    }
    protected Optional<Terminal> findTerminalPorComuna(String comuna){
        return null;
    }
    protected Optional <Bus> findBus(String patente) {
        for (Bus b : buses) {
            if (b.getPatente().equals(patente)) return Optional.of(b);
        }
        return null;
    }
    protected Optional<Conductor> findConductor(IdPersona idPersona, Rut rutEmpresa){
        return null;
    }
    protected Optional<Auxiliar> findAuxiliar(IdPersona idPersona, Rut rutEmpresa){
        return null;
    }


}
