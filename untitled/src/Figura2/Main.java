package Figura2;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import  java.util.Scanner;
public class Main {
    Scanner sc = new Scanner(System.in);
    SistemaVentaPasajes sistem = new SistemaVentaPasajes;
    public static void main(String[] args) {





    }
    private void menu(){
        int opcion=0;
        while (opcion!=8){
            System.out.println("============================");
            System.out.println("...::: Menú principal :::...");
            System.out.println("\n  1) Crear cliente\n  2) Crear bus\n  3) Crear viaje\n  4) Vender pasajes\n  5) Lista de pasajeros\n  6) Lista de ventas\n  7) Lista de viajes\n  8) Salir");
            System.out.println("----------------------------");
            System.out.println("..:: Ingrese número de opción:");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    createCliente();
                    break;
                case 2:
                    createBus();
                    break;
                case 3:
                    createViaje();
                    break;
                case 4:
                    vendePasajes();
                    break;
                case 5:
                    listPasajerosViaje();
                    break;
                case 6:
                    listVentas();
                    break;
                case 7:
                    listViajes();
                    break;
                case 8:
                    break;
                default:
            }
        }

    }
    private void createCliente(){
        IdPersona id;
        boolean existecliente = false;
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. Rut\n2. Pasaporte");
        int identificador = sc.nextInt();
        if (identificador == 1){
            System.out.println("Ingrese el rut del cliente: ");
            id = sc.next();
        } else if (identificador == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            id = sc.next();
        }
        System.out.println("Ingrese si es Sr.(1) o Sra.(2):");
        int srsra = sc.nextInt();
        System.out.println("Ingrese los nombres del  cliente:");
        String nom = sc.nextLine();
        System.out.println("Ingrese el Apellido Paterno del cliente:");
        String apepaterno = sc.next();
        System.out.println("Ingrese el Apellido Materno del cliente:");
        String apematerno = sc.next();
        System.out.println("Ingrese el numero telefonico del cliente:");
        String fono = sc.nextLine();
        System.out.println("Ingrese el email del cliente:");
        String emailCliente = sc.nextLine();
        if (sistem.createCliente(id, nom,fono,emailCliente) == false){
            System.out.println("   ...:::: Crear un nuevo Cliente ::::....");
            System.out.println("Rut[1] o Pasaporte[2] : " + identificador);
            if (identificador == 1){
                System.out.println("R.U.T : " + id);
            } else if (identificador == 2) {
                System.out.println("Pasaporte Cliente : " + id);
            }
            System.out.println("Sr.[1] o Sra. [2] : " + srsra);
            System.out.println("Nombres : " + nom);
            System.out.println("Apellido Paterno : " + apepaterno);
            System.out.println("Apellido Materno : " + apematerno);
            System.out.println("Telefono movil : " + fono);
            System.out.println("Email : " + emailCliente +"\n");
            System.out.println("...:::: Cliente guardado exitosamente ::::....");
        }else{
            System.out.println("Ya existe un cliente con esta identificacion");
            return;
        }

    }
    private void createBus(){
        System.out.println("Ingrese la patente del bus: ");
        String patente = sc.nextLine();
        System.out.println("Ingrese la Marca del bus: ");
        String marca = sc.nextLine();
        System.out.println("Ingrese el Modelo del bus");
        String modelo = sc.nextLine();
        System.out.println("Ingrese el Número de asientos del bus");
        int nroasientos = sc.nextInt();
        if (sistem.createBus(patente, marca, modelo, nroasientos) == false){
            System.out.println("...:::: Creación de un nuevo Bus ::::....");
            System.out.println("\nPatente : " + patente);
            System.out.println("Marca : " + marca);
            System.out.println("Modelo : " + modelo);
            System.out.println("Número de asientos : " + nroasientos);
            System.out.println("\n...:::: Bus guardado exitosamente ::::....");
        } else {
            System.out.println("Ya existe un bus con esta patente");
            return;
        }
    }
    private void createViaje(){
        DateTimeFormatter fechaFormato =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Ingrese la fecha del viaje dd/mm/aaaa:");
        String fechaIngresada = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fechaIngresada,fechaFormato);
        System.out.println("Ingrese la hora del viaje");
        String horaIngresada = sc.nextLine();
        LocalTime hora = LocalTime.parse(horaIngresada);
        System.out.println("Ingrse el precio del viaje");
        int precio = sc.nextInt();
        System.out.println("Ingrese la patente del bus para el viaje:");
        String patente = sc.nextLine();

        if (sistem.createViaje(fecha,hora,precio,patente)==false){
            System.out.println("...:::: Creación  de un nuevo Viaje ::::....\n");
            System.out.println("Fecha[dd/mm/yyyy : " + fecha);
            System.out.println("Precio : "+ precio);
            System.out.println("Patente Bus : " + patente);
            System.out.println("\n...:::: Viaje guardado exitosamente ::::....");
        }

    }
    private void vendePasajes(){
        IdPersona id;
        DateTimeFormatter fechaFormato =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Ingrese la ID del documento:");
        int idDocumento = sc.nextInt();
        System.out.println("Ingrese el tipo de documento: \n[1]Boleta \n[2]Factura");
        int tipoDoc = sc.nextInt();
        System.out.println("Ingrse la fecha de venta [dd/mm/yyyy]: ");
        String fechaIngresada = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fechaIngresada,fechaFormato);
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. Rut\n2. Pasaporte");
        int tipoIdentificador = sc.nextInt();
        if (tipoIdentificador == 1){
            System.out.println("Ingrese el rut del cliente: ");
            id = sc.next();
        } else if (tipoIdentificador == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            id = sc.next();
        }
        if (sistem.iniciaVenta()==false){
            System.out.println("        ...:::: Venta de Pasajes ::::....\n\n\n:::: Datos de la Venta ");
            System.out.println("\t\t\tID Documento : " + idDocumento);
            System.out.println("Tipo documento: [1] Boleta [2] Factura : " + tipoDoc);
            System.out.println("Fecha de venta[dd/mm/yyyy] : " + fecha);
            System.out.println(":::: Datos del cliente\n");
            System.out.println("Rut[1] o Pasaporte[2] : " + tipoIdentificador);
            if (tipoIdentificador == 1){
                System.out.println("R.U.T : " + id);
            } else if (tipoIdentificador == 2) {
                System.out.println("Pasaporte Cliente : " + id);
            }
            System.out.println("Nombre Cliente : " + );
        }else {
            System.out.println("Los datos ingresados son incorrectos");
        }
    }
    private void listPasajerosViaje(){
        for (int i = 0; i < ; i++) {
            
        }
    }
    private void listVentas(){
        if (sistem.listVentas() == null) {
            System.out.println("\t\t...:::: Listado de ventas ::::....");
            System.out.println("*------------*----------*------------*------------------*--------------------------------*--------------*--------------*");
            System.out.println("| ID DOCUMENT| TIPO DOCU|      FECHA |    RUT/PASAPORTE | CLIENTE                        | CANT BOLETOS |  TOTAL VENTA |");
            System.out.println("+------------+----------+------------+------------------+--------------------------------+--------------+--------------+");
            for (String[] ventas: sistem.listVentas()){
                System.out.println("|            |          |            |                  |                                |              |              |");
            }
            System.out.println("*------------*----------*------------*------------------*--------------------------------*--------------*--------------*");
        }else {
            System.out.println("No existen ventas registradas");
        }

    }
    private void listViajes(){
        if (sistem.listViajes() == null) {
            System.out.println("\t\t...:::: Listado de viajes ::::....\n");
            System.out.println("*--------------*--------------*--------------*--------------*--------------*");
            System.out.println("|        FECHA |         HORA |       PRECIO |  DISPONIBLES | PATENTE      |");
            for (String[] viajes : sistem.listViajes()){
                System.out.println("|--------------+--------------+--------------+--------------+--------------|");
                System.out.println("|              |              |              |              |              |");
            }
            System.out.println("*--------------*--------------*--------------*--------------*--------------*");
        }else {
            System.out.println("No existen viajes registrados");
        }
    }
}