package persistencia;

import excepciones.SVPException;
import modelo.*;
import utilidades.IdPersona;
import utilidades.Rut;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOISVP {
    private static IOISVP instance;
    private IOISVP() {}
    public static IOISVP getInstance(){
        if (instance == null){
            return instance = new IOISVP();
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
            br.close();
        }catch (IOException e){
            System.out.println("ERROR : No existe o no se puede abrir el archivo SVPDatosIniciales.txt");
        }
        return datosIniciales.toArray();
    }
    public void saveControladores(Object[] controladores)throws SVPException{
            try (ObjectOutputStream guardado = new ObjectOutputStream(new FileOutputStream("SVPObjetos.obj"))){
                guardado.writeObject(controladores);
                guardado.close();
            } catch (FileNotFoundException e) {
                throw new SVPException("No se puede grabar en el archivo SVPObjetos.obj");
            }catch (IOException e){
                throw new SVPException("No se puede abrir o crear el archivo SVPObjetos.obj");
            }
    }
    public Object[] readControladores()throws SVPException{
        try (ObjectInputStream leido = new ObjectInputStream(new FileInputStream("SVPObjetos.obj"))){
            Object[] datosSistema = (Object[]) leido.readObject();
            leido.close();
            return datosSistema;

        } catch (FileNotFoundException e) {
            throw new SVPException("No existe o no se puede abrir el archivo SVPObjetos.obj");
        }catch (IOException | ClassNotFoundException e){
            throw new SVPException("No se puede leer el archivo SVPObjetos.obj");
        }
    }
    public void savePasajesDeVenta(Pasaje[] pasajes, String nombreArchivo){

    }

    private Optional<Empresa> findEmpresa(List<Empresa> empresas, Rut rut){
        for (Empresa e : empresas){
            if (e.getRut().equals(rut)) return Optional.of(e) ;
        }
        return Optional.empty();
    }

    private Optional<Tripulante> findTripulante(Empresa empresa, IdPersona id){
        for (Tripulante t : empresa.getTripulante()){
            if (t.getIdPersona().equals(id)) return Optional.of(t);
        }
        return Optional.empty();
    }
    private Optional<Bus> findBus(List<Bus> buses, String patente){
        for (Bus b : buses) {
            if (b.getPatente().equals(patente)) return Optional.of(b);
        }
        return Optional.empty();
    }
    private Optional<Terminal> findTerminal(List<Terminal> terminales, String nombres){
        for (Terminal t : terminales){
            if (t.getNombre().equals(nombres)) return Optional.of(t);
        }
        return Optional.empty();
    }
}
