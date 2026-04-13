package Figura2;
import  java.util.Scanner;
public class Main {
    Scanner sc = new Scanner(System.in);
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
        String rutcliente;
        String pasaportecliente;
        boolean existecliente = false;
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. Rut\n2. Pasaporte");
        int identificador = sc.nextInt();
        if (identificador == 1){
            System.out.println("Ingrese el rut del cliente: ");
            rutcliente = sc.nextLine();
        } else if (identificador == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            pasaportecliente = sc.nextLine();
        }else{
            return;
        }
        for (int i = 0; i < ; i++) {
            if(String )
        }
        if (existecliente = false){
            System.out.println("Ingrese si es Sr.(1) o Sra.(2):");
            int srsra = sc.nextInt();
            System.out.println("Ingrese los nombres del  cliente:");
            String nombres = sc.nextLine();
            System.out.println("Ingrese el Apellido Paterno del cliente:");
            String apepaterno = sc.next();
            System.out.println("Ingrese el Apellido Materno del cliente:");
            String apematerno = sc.next();
            System.out.println("Ingrese el numero telefonico del cliente:");
            String telefonoCliente = sc.nextLine();
            System.out.println("Ingrese el email del cliente:");
            String emailCliente = sc.nextLine();


            System.out.println("   ...:::: Crear un nuevo Cliente ::::....");
            System.out.println("Rut[1] o Pasaporte[2] : " + identificador);
            if (identificador == 1){
                System.out.println("R.U.T : " + rutcliente);
            } else if (identificador == 2) {
                System.out.println("Pasaporte Cliente : " + pasaportecliente);
            }
            System.out.println("Sr.[1] o Sra. [2] : " + srsra);
            System.out.println("Nombres : " + nombres);
            System.out.println("Apellido Paterno : " + apepaterno);
            System.out.println("Apellido Materno : " + apematerno);
            System.out.println("Telefono movil : " + telefonoCliente);
            System.out.println("Email : " + emailCliente +"\n");
            System.out.println("...:::: Cliente guardado exitosamente ::::....");
        }else{
            System.out.println("Ya existe un cliente con esta identificacion");
            return;
        }

    }
    private void createBus(){
        System.out.println("Ingrese la patente del bus: ");

    }
    private void createViaje(){

    }
    private void vendePasajes(){

    }
    private void listPasajerosViaje(){
        for (int i = 0; i < ; i++) {
            
        }
    }
    private void listVentas(){
        for (int i = 0; i < ; i++) {


        }
    }
    private void listViajes(){
        for (int i = 0; i < ; i++) {
            
        }
    }
}