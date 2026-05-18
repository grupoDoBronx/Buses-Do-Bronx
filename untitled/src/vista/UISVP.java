package vista;

import controlador.ControladorEmpresas;
import controlador.SistemaVentaPasajes;
import modelo.Empresa;
import utilidades.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UISVP {
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter fechaFormato =DateTimeFormatter.ofPattern("dd/MM/yyyy");
    SistemaVentaPasajes sistem = new SistemaVentaPasajes();
    ControladorEmpresas controlador = new ControladorEmpresas();

    public void menu(){
        int opcion=0;
        while (true){
            sc.nextLine();
            try {
                System.out.println("============================\n\t   ...::: Menú principal :::...");
                System.out.println("\n  1) Crear Empresa\n  2) Contratar tripulante\n  3) Crear Terminal\n  4) Crear  cliente\n  5) Crear bus\n  6) Crear viaje\n  7) Vender pasajes\n  8) Listar ventas" +
                        "\n  9) Listar viajes\n  10) Listar pasajeros de viaje\n  11) Listar empresas\n  12) Listar llegadas/salida de terminal\n  13) Listar ventas de empresa\n  14) Salir");
                System.out.println("\n----------------------------\n..:: Ingrese número de opción:");
                opcion = sc.nextInt();
                if (opcion == 14) {
                    System.out.println("Saliendo del programa");
                    return;
                }
                switch (opcion) {
                    case 1:
                        createEmpresa();
                        break;
                    case 2:
                        contrataTripulante();
                        break;
                    case 3:
                        createTerminal();
                        break;
                    case 4:
                        createCliente();
                        break;
                    case 5:
                        createBus();
                        break;
                    case 6:
                        createViaje();
                        break;
                    case 7:
                        vendePasajes();
                        break;
                    case 8:
                        listVentas();
                        break;
                    case 9:
                        listViajes();
                        break;
                    case 10:
                        listPasajerosViaje();
                        break;
                    case 11:
                        listEmpresas();
                        break;
                    case 12:
                        listLlegadasSalidasTerminal();
                        break;
                    case 13:
                        listVentasEmpresa();
                    default:
                        System.out.println("Opcion numerica ingresada incorrecta");
                }
            }catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar un numero");
                sc.nextLine();
            }
        }

    }
    private void createEmpresa(){
        IdPersona id;
        System.out.println("Ingrese el R.U.T. de la empresa:");
        String rutEmpresa =sc.nextLine();
        id = Rut.of(rutEmpresa);
        System.out.println("Ingrese el nombre de la empresa:");
        String nomEmp = sc.nextLine();
        System.out.println("Ingrese el url de la empresa: ");
        String urlingresada = sc.nextLine();

        //creando nueva empresa
        System.out.println("...:::: Creando una nueva Empresa ::::....\n\n");
        System.out.println("R.U.T : " + id);
        System.out.println("Nombre : " + nomEmp);
        System.out.println("url : " + urlingresada);
        System.out.println("\n...:::: Empresa guardada exitosamente ::::....");
    }
    private void contrataTripulante(){
        IdPersona idEmp;
        IdPersona idTrip;
        Tratamiento tratamiento = null;
        System.out.println("Ingrese el rut de la empresa: ");
        String rutEmpresa =sc.nextLine();
        idEmp = Rut.of(rutEmpresa);
        System.out.println("Ingrese si es un Conductor o un Axiliar:");
        int opAC = sc.nextInt();
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. Rut\n2. Pasaporte");
        int identificadorRP = sc.nextInt();
        if (identificadorRP == 1){
            System.out.println("Ingrese el rut del cliente: ");
            String ingreso = sc.next();
            idTrip = Rut.of(ingreso);
        } else if (identificadorRP == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            String ingreso = sc.next();
            System.out.println("Ingrese la nacionalidad: ");
            String nacionalidad = sc.next();
            idTrip = Pasaporte.of(ingreso,nacionalidad);
        }
        System.out.println("Ingrese si es Sr.(1) o Sra.(2):");
        int srsra = sc.nextInt();
        if (srsra==1){
            tratamiento =Tratamiento.SR;
        }else {
            tratamiento = Tratamiento.SRA;
        }
        System.out.println("Ingrese los nombres del  cliente:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el Apellido Paterno del cliente:");
        String apepaterno = sc.next();
        System.out.println("Ingrese el Apellido Materno del cliente:");
        String apematerno = sc.next();


    }
    private void createTerminal(){

    }
    private void createCliente(){
        IdPersona id = null;
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. Rut\n2. Pasaporte");
        int identificador = sc.nextInt();
        if (identificador == 1){
            System.out.println("Ingrese el rut del cliente: ");
            String ingreso = sc.next();
            id = Rut.of(ingreso);
        } else if (identificador == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            String ingreso = sc.next();
            System.out.println("Ingrese la nacionalidad: ");
            String nacionalidad = sc.next();
            id = Pasaporte.of(ingreso,nacionalidad);
        }
        System.out.println("Ingrese si es Sr.(1) o Sra.(2):");
        int srsra = sc.nextInt();
        System.out.println("Ingrese los nombres del  cliente:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el Apellido Paterno del cliente:");
        String apepaterno = sc.next();
        System.out.println("Ingrese el Apellido Materno del cliente:");
        String apematerno = sc.next();
        System.out.println("Ingrese el numero telefonico del cliente:");
        String fono = sc.nextLine();
        System.out.println("Ingrese el email del cliente:");
        String emailCliente = sc.nextLine();
        Nombre nom = new Nombre();
        Tratamiento tratamiento = null;
        if (srsra==1){
            tratamiento =Tratamiento.SR;
        }else {
            tratamiento = Tratamiento.SRA;
        }
        nom.setTratamiento(tratamiento);
        nom.setNombre(nombre);
        nom.setApellido_parterno(apepaterno);
        nom.setApellido_materno(apematerno);

        sistem.createCliente(id,nom,emailCliente);

        //mostrar el cliente creado
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


        //mostrar el bus creado
        System.out.println("...:::: Creación de un nuevo Bus ::::....");
        System.out.println("\nPatente : " + patente);
        System.out.println("Marca : " + marca);
        System.out.println("Modelo : " + modelo);
        System.out.println("Número de asientos : " + nroasientos);
        System.out.println("\n...:::: Bus guardado exitosamente ::::....");

    }
    private void createViaje(){

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


        System.out.println("...:::: Creación  de un nuevo Viaje ::::....\n");
        System.out.println("Fecha[dd/mm/yyyy : " + fecha);
        System.out.println("Precio : "+ precio);
        System.out.println("Patente Bus : " + patente);
        System.out.println("\n...:::: Viaje guardado exitosamente ::::....");


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
        System.out.println("Ingrese el tipo de identificador del cliente: \n1. utilidades.Rut\n2. utilidades.Pasaporte");
        int tipoIdentificador = sc.nextInt();
        if (tipoIdentificador == 1){
            System.out.println("Ingrese el rut del cliente: ");
            String ingreso = sc.next();
            id = Rut.of(ingreso);
        } else if (tipoIdentificador == 2) {
            System.out.println("Ingrese el pasaporte del cliente: ");
            String ingreso = sc.next();
            System.out.println("Ingrese la nacionalidad: ");
            String nacionalidad = sc.next();
            id = Pasaporte.of(ingreso,nacionalidad);
        }



            System.out.println("        ...:::: Venta de Pasajes ::::....\n\n\n:::: Datos de la Venta ");
            System.out.println("\t\t\tID Documento : " + idDocumento);
            System.out.println("Tipo documento: [1] Boleta [2] Factura : " + tipoDoc);
            System.out.println("Fecha de venta[dd/mm/yyyy] : " + fecha);
            System.out.println(":::: Datos del cliente\n");
            System.out.println("utilidades.Rut[1] o utilidades.Pasaporte[2] : " + tipoIdentificador);
            if (tipoIdentificador == 1){
                System.out.println("R.U.T : " );
            } else if (tipoIdentificador == 2) {
                System.out.println("utilidades.Pasaporte Figura1.Cliente : ");
            }
            System.out.println("utilidades.Nombre Figura1.Cliente : "  );

            System.out.println("Ingrese cuantos pasajes comprara el cliente: ");
            int cantPas = sc.nextInt();
            System.out.println("Ingrese la fecha del viaje");
            String fechaIngresada2 = sc.nextLine();
            LocalDate fecha2 = LocalDate.parse(fechaIngresada2,fechaFormato);

            System.out.println(":::: Pasajes a vender\n      Cantidad de pasajes : " + cantPas);
            System.out.println("Fecha de viaje[dd/mm/yyyy] : " + fecha2 +"\n");
            System.out.println(":::: Listado de horarios disponibles");
            System.out.println("     *----------*----------*----------*----------*");
            System.out.println("     | BUS      |   SALIDA |    VALOR | ASIENTOS |");
            for (int i = 0; i < sistem.getHorariosDisponibles(fecha2).length; i++) {

            }
            System.out.println("     *----------*----------*----------*----------*\n\n");



    }
    private void pagaVentaPasajes() {

    }

    private void listVentas(){
        if (sistem.listVentas() != null) {
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
        String[][] viajes = sistem.listViajes();
        if (viajes.length == 0 ) {
            System.out.println("No existen viajes registrados");
            return;
        }
        System.out.println("\t\t...:::: Listado de viajes ::::....\n");
        System.out.println("*--------------*--------------*--------------*--------------*--------------*");
        System.out.println("|        FECHA |         HORA |       PRECIO |  DISPONIBLES | PATENTE      |");
        for (String[] v : sistem.listViajes()){
            System.out.println("|--------------+--------------+--------------+--------------+--------------|");
            System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s |\n", v[0],v[1],v[2],v[3],v[4]);
        }
        System.out.println("*--------------*--------------*--------------*--------------*--------------*");
    }
    private void listPasajerosViaje(){

    }
    private void listEmpresas(){
        String[][] empresas = controlador.listEmpresas();
        if (empresas.length == 0){
            System.out.println("No existen empresas registradas");
            return;
        }
        System.out.println("       ...:::: Listado de empresas ::::....\n");
        System.out.println("*--------------*--------------------------------*--------------------------------*------------------*------------*-------------*");
        System.out.println("| RUT EMPRESA  | NOMBRE                         | URL                            | NRO. TRIPULANTES | NRO. BUSES | NRO. VENTAS |");
        for (String[] e :empresas) {
            System.out.println("|--------------+--------------------------------+--------------------------------+------------------+------------+-------------|");
            System.out.printf("| %-12s | %-30s | %-30s | %-16s | %-10s | %-11s |\n", e[0], e[1], e[2], e[3], e[4], e[5]);
        }
        System.out.println("*--------------*--------------------------------*--------------------------------*------------------*------------*-------------*");
    }
    private void listLlegadasSalidasTerminal(){

    }
    private void listVentasEmpresa(){

    }
}


















