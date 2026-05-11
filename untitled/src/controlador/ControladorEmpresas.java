package controlador;

import excepciones.SistemaVentaPasajesException;
import modelo.*;
import utilidades.Direccion;
import utilidades.IdPersona;
import utilidades.Nombre;
import utilidades.Rut;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class ControladorEmpresas {

    ArrayList<Bus> buses = new ArrayList<>();
    public void createEmpresa(Rut rut, String nombre,String url){

    }
    public void createBus (String patente, String marca, String modelo, int numeroDeAsientos){
        try {
            if(findBus(patente).isPresent()){

            }
        }catch (SistemaVentaPasajesException e){

        }

        Bus nuevoBus = new Bus(numeroDeAsientos, patente);
        buses.add(nuevoBus);
    }
    public void createTerminal(String nombre, Direccion direccion){

    }
    public void hireConstructorForEmpresa (Rut rutEmp, IdPersona id, Nombre nom, Direccion dir){

    }
    public void hireAuxiliarForEmpresa (Rut rutEmp,IdPersona id, Nombre nom, Direccion dir) {

    }
    public String[][] listEmpresas(){
        return null;
    }
    public String[][] listLLegadasSalidasTerminal(String nombre, Date fecha){
        return null;
    }
    public String[][] listVentasEmpresa(Rut rut){
        return null;
    }
    protected Optional <Empresa> findEmpresa(Rut rut){

        return null;
    }
    protected Optional<Terminal> findTerminal(String nombre){

    }
    protected Optional<Terminal> findTerminalPorComuna(String comuna){

    }
    protected Optional <Bus> findBus(String patente) {
        for (Bus b : buses) {
            if (b.getPatente().equals(patente)) return Optional.of(b);
        }
        return null;
    }
    protected Optional<Conductor> findConductor(IdPersona idPersona, Rut rutEmpresa){

    }
    protected Optional<Auxiliar> findAuxiliar(IdPersona idPersona, Rut rutEmpresa){

    }


}
