package persistencia;

import modelo.*;
import utilidades.IdPersona;
import utilidades.Rut;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OISVP {
    private static OISVP instance;
    public static OISVP getInstance(){
        if (instance == null){
            return instance = new OISVP();
        }
        return instance;
    }
    public Object[] readDatosIniciales(){
        ArrayList<Object> datosIniciales = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("SVPDatosIniciales.txt"))){
            String linea;
            while ((linea = br.readLine()) != null){
                datosIniciales.add(linea);
            }
        }catch (IOException e){
            System.out.println("ERROR : No existe o no se puede abrir el archivo SVPDatosIniciales.txt");
        }
        return datosIniciales.toArray();
    }
    public void saveControladores(Object[] controladores){

    }
    public Object[] readControladores(){

    }
    public void savePasajesDeVenta(Pasaje[] pasajes, String nombreArchivo){

    }
    private Optional<Empresa> findEmpresa(List<Empresa> empresas, Rut rut){

    }
    private Optional<Tripulante> findTripulante(Empresa empresa, IdPersona id){

    }
    private Optional<Bus> findBus(List<Bus> buses, String patente){

    }
    private Optional<Terminal> findTerminal(List<Terminal> terminales, String nombres){

    }
}
