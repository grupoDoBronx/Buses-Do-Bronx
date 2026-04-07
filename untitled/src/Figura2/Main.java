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
        System.out.println();
    }
    private void createBus(){

    }
    private void createViaje(){

    }
    private void vendePasajes(){

    }
    private void listPasajerosViaje(){

    }
    private void listVentas(){

    }
    private void listViajes(){

    }
}