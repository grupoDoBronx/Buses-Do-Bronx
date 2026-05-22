package vista;

import controlador.ControladorEmpresas;
import controlador.SistemaVentaPasajes;

import modelo.TipoDocumento;
import utilidades.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
// Hecho por Harold Topp (herreronovato en git ya que me equivoque de cuenta)
public class UISVP {
    Scanner sc = new Scanner(System.in);
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
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
        Rut id;
        System.out.println("  ...:::: Creando una nueva Empresa ::::....\n\n");
        System.out.print(  "                  R.U.T : ");
        String rutEmpresa =sc.nextLine();
        id = Rut.of(rutEmpresa);
        System.out.print("                   Nombre : ");
        String nomEmp = sc.nextLine();
        System.out.print("                      url : ");
        String urlingresada = sc.nextLine();
        controlador.createEmpresa(id,nomEmp,urlingresada);
    }
    private void contrataTripulante(){
        try {
            int identificadorRP = 1;
            int opAC =1;
            Rut idEmp;
            IdPersona idTrip = null;
            Tratamiento tratamiento = null;
            System.out.println(" ...:::: Contatando un nuevo Tripulante ::::....\n\n");
            System.out.println(":::: Dato de la Empresa");
            System.out.print(  "                    R.U.T :");
            String rutEmpresa =sc.nextLine();
            idEmp = Rut.of(rutEmpresa);
            System.out.println("\n:::: Datos tripulante");

            boolean opValidoAC = false;
            while (!opValidoAC){
                System.out.print("Auxiliar[1] o Conductor[2] : ");
                opAC = sc.nextInt();
                if (opAC == 1 || opAC ==2){
                    opValidoAC = true;
                }else{
                    System.out.println("Opcion invalida");
                }
            }
            boolean opValidoRP = false;
            while (!opValidoRP){
                System.out.print("    Rut[1] o Pasaporte[2] : ");
                identificadorRP = sc.nextInt();
                if (identificadorRP == 1 || identificadorRP ==2){
                    if (identificadorRP == 1){
                        System.out.print("                R.U.T : ");
                        String ingreso = sc.next();
                        idTrip = Rut.of(ingreso);
                    } else {
                        System.out.print("          Pasaporte : ");
                        String ingreso = sc.next();
                        System.out.print("       Nacionalidad : ");
                        String nacionalidad = sc.next();
                        idTrip = Pasaporte.of(ingreso,nacionalidad);
                    }
                    opValidoRP = true;
                }else{
                    System.out.println("Opcion invalida");
                }
            }
            boolean opValidaSrSra = false;
            while (!opValidaSrSra){
                System.out.print("       Sr.(1) o Sra.(2) : ");
                int srsra = sc.nextInt();
                if (srsra ==1 ||srsra == 2){
                    opValidaSrSra = true;
                    if (srsra==1){
                        tratamiento =Tratamiento.SR;
                    }else {
                        tratamiento = Tratamiento.SRA;
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }

            System.out.print("                Nombres : ");
            String nombre = sc.nextLine();
            System.out.print("       Apellido Paterno : ");
            String apepaterno = sc.next();
            System.out.println("     Apellido Materno : ");
            String apematerno = sc.next();
            System.out.print("                  Calle : ");
            String calle = sc.nextLine();
            System.out.print("                 Numero : ");
            int numero = sc.nextInt();
            System.out.print("                 Comuna : ");
            String comuna = sc.nextLine();

            //guardar nombre
            Nombre nom = new Nombre();
            nom.setTratamiento(tratamiento);
            nom.setNombre(nombre);
            nom.setApellido_parterno(apepaterno);
            nom.setApellido_materno(apematerno);
            //guardar direccion
            Direccion direccion = new Direccion(calle,comuna,numero);
            if (opAC == 1){
                controlador.hireAuxiliarForEmpresa(idEmp,idTrip,nom,direccion);
            } else {
                controlador.hireConductorForEmpresa(idEmp,idTrip,nom,direccion);
            }


        }catch (InputMismatchException e){
            System.out.println("ERROR : " + e.getMessage());
            return;
        }
    }
    private void createTerminal(){
        try {
            System.out.println("   ...:::: Creando un nuevo Terminal ::::....\n");
            System.out.print("                    Nombre : ");
            String nombreTer = sc.nextLine();
            System.out.print("                     Calle : ");
            String nombreCalle = sc.nextLine();
            System.out.print("                    Numero : ");
            int numero = sc.nextInt();
            System.out.print("                    Comuna : ");
            String comuna = sc.nextLine();
            Direccion direccion = new Direccion(nombreCalle,comuna,numero);
            controlador.createTerminal(nombreTer,direccion);
        }catch (InputMismatchException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    private void createCliente(){
        IdPersona id = null;
        System.out.println("   ...:::: Crear un nuevo Cliente ::::....");
        try {
            Nombre nom = new Nombre();
            Tratamiento tratamiento = null;


            boolean opValidaRP = false;
            while (!opValidaRP){
                System.out.print("  Rut[1] o Pasaporte[2] : ");
                int identificador = sc.nextInt();
                if (identificador==1 ||identificador == 2){
                    opValidaRP = true;
                    if (identificador == 1){
                        System.out.print("              R.U.T : ");
                        String ingreso = sc.next();
                        id = Rut.of(ingreso);

                    } else {
                        System.out.print("          Pasaporte : ");
                        String ingreso = sc.next();
                        System.out.print("       Nacionalidad : ");
                        String nacionalidad = sc.next();
                        id = Pasaporte.of(ingreso,nacionalidad);
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }
            boolean opValidaSrSra = false;
            while (!opValidaSrSra){
                System.out.print("       Sr.(1) o Sra.(2) : ");
                int srsra = sc.nextInt();
                if (srsra==1 ||srsra == 2){
                    opValidaSrSra = true;
                    if (srsra==1){
                        tratamiento =Tratamiento.SR;
                    }else {
                        tratamiento = Tratamiento.SRA;
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }
            System.out.print("                Nombres : ");
            String nombre = sc.nextLine();
            System.out.print("       Apellido Paterno : ");
            String apepaterno = sc.next();
            System.out.print("       Apellido Materno : ");
            String apematerno = sc.next();
            System.out.print("         Telefono movil : ");
            String fono = sc.nextLine();
            System.out.println("                Email : ");
            String emailCliente = sc.nextLine();
            //guardadndo nombre
            nom.setTratamiento(tratamiento);
            nom.setNombre(nombre);
            nom.setApellido_parterno(apepaterno);
            nom.setApellido_materno(apematerno);

            sistem.createCliente(id,nom,fono,emailCliente);

        }catch (InputMismatchException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    private void createBus(){
        try {
            Rut rut;
            System.out.println("      ...:::: Creación de un nuevo Bus ::::....\n");
            System.out.print("                        Patente : ");
            String patente = sc.nextLine();
            System.out.print("                          Marca : ");
            String marca = sc.nextLine();
            System.out.print("                         Modelo : ");
            String modelo = sc.nextLine();
            System.out.print("             Número de asientos : ");
            int nroasientos = sc.nextInt();
            System.out.println("\n:::: Dato  de la  Empresa");
            System.out.print("                          R.U.T : ");
            String rutEmpresa =sc.nextLine();
            rut = Rut.of(rutEmpresa);

        }catch (InputMismatchException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }
    private void createViaje(){

        System.out.println("  ...:::: Creación  de un nuevo Viaje ::::....\n");
        try {
            IdPersona[] id = null;
            System.out.print("     Fecha[dd/mm/yyyy : ");
            String fechaIngresada = sc.nextLine();
            Date fecha = fechaFormato.parse(fechaIngresada);
            System.out.print("          Hora[hh:mm] : ");
            String horaIngresada = sc.nextLine();
            LocalTime horaLocal = LocalTime.parse(horaIngresada);
            Time hora = Time.valueOf(horaLocal);
            System.out.print("               Precio : ");
            int precio = sc.nextInt();
            System.out.print("   Duracion (minutos) : ");
            int minutos = sc.nextInt();
            System.out.print("          Patente Bus : ");
            String patente = sc.nextLine();
            boolean opValidaC = false;
            int nroConductores=1;
            while (!opValidaC){
                System.out.print("  Nro. de conductores : ");
                nroConductores = sc.nextInt();
                if (nroConductores==1 || nroConductores ==2){
                    opValidaC = true;
                }else {
                    System.out.println("Opcion ingresada fuera de rango");
                }
            }
            System.out.println("     :: Id Auxiliar ::\n");
            boolean opValidaRPTrip = false;
            while (!opValidaRPTrip){
                System.out.print("   Rut[1] o Pasaporte[2] : ");
                int identificador = sc.nextInt();
                if (identificador==1 ||identificador == 2){
                    opValidaRPTrip = true;
                    if (identificador == 1){
                        System.out.print("              R.U.T : ");
                        String ingreso = sc.next();
                        id[0] = Rut.of(ingreso);

                    } else {
                        System.out.print("          Pasaporte : ");
                        String ingreso = sc.next();
                        System.out.print("       Nacionalidad : ");
                        String nacionalidad = sc.next();
                        id[0] = Pasaporte.of(ingreso,nacionalidad);
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }
            for (int i = 0; i < nroConductores; i++) {
                i++;
                System.out.println("    :: Id Conductor ::");
                boolean opValidaRPCond = false;
                while (!opValidaRPCond){
                    System.out.print("   Rut[1] o Pasaporte[2] : ");
                    int identificador = sc.nextInt();
                    if (identificador==1 ||identificador == 2){
                        opValidaRPCond = true;
                        if (identificador == 1){
                            System.out.print("              R.U.T : ");
                            String ingreso = sc.next();
                            id[i] = Rut.of(ingreso);

                        } else {
                            System.out.print("          Pasaporte : ");
                            String ingreso = sc.next();
                            System.out.print("       Nacionalidad : ");
                            String nacionalidad = sc.next();
                            id[i] = Pasaporte.of(ingreso,nacionalidad);
                        }
                    }else {
                        System.out.println("Opcion invalida");
                    }
                }
            }
            String[] nomcomunas = new String[2];
            System.out.print("  Nombre comuna salida : ");
            nomcomunas[0] = sc.nextLine();
            System.out.print(" Nombre comuna llegada : ");
            nomcomunas[1] = sc.nextLine();
            sistem.createViaje(fecha,hora,precio,minutos,patente,id,nomcomunas);

        }catch (InputMismatchException e){
            System.out.println("ERROR: " + e.getMessage());
            return;
        }catch (ParseException e){
            System.out.println("ERROR: " + e.getMessage());
        }


    }
    private void vendePasajes(){
        IdPersona id = null;
        TipoDocumento tipoDocumento = null;
        try {
            System.out.println("        ...:::: Venta de Pasajes ::::....\n\n:::: Datos de la Venta ");
            System.out.print("             ID Documento : ");
            String  idDocumento = sc.next();
            boolean opVenta = false;
            while (!opVenta){
                System.out.print("Tipo documento: [1] Boleta [2] Factura : ");
                int tipoDoc = sc.nextInt();
                if (tipoDoc == 1 || tipoDoc == 2){
                    opVenta = true;
                    if (tipoDoc == 1){
                        tipoDocumento = TipoDocumento.BOLETA;
                    }else {
                        tipoDocumento = TipoDocumento.FACTURA;
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }
            System.out.print("Fecha de viaje[dd/mm/yyyy] : ");
            String fechaIngresada = sc.nextLine();
            Date fechaviaje = fechaFormato.parse(fechaIngresada);
            System.out.print("          Origen (comuna) : ");
            String comunaOri = sc.nextLine();
            System.out.print("         Destino (comuna) : ");
            String comunaLle = sc.nextLine();
            System.out.println("\n:::: Datos del cliente\n");
            boolean opValidaRPCond = false;
            while (!opValidaRPCond){
                System.out.print("   Rut[1] o Pasaporte[2] : ");
                int identificador = sc.nextInt();
                if (identificador==1 ||identificador == 2){
                    opValidaRPCond = true;
                    if (identificador == 1){
                        System.out.print("              R.U.T : ");
                        String ingreso = sc.next();
                        id = Rut.of(ingreso);

                    } else {
                        System.out.print("          Pasaporte : ");
                        String ingreso = sc.next();
                        System.out.print("       Nacionalidad : ");
                        String nacionalidad = sc.next();
                        id = Pasaporte.of(ingreso,nacionalidad);
                    }
                }else {
                    System.out.println("Opcion invalida");
                }
            }
            System.out.println("\n:::: Pasajes a vender");
            System.out.print("      Cantidad de pasajes : ");
            int cantPasajes = sc.nextInt();

            sistem.iniciaVenta(idDocumento,tipoDocumento,fechaviaje,comunaOri,comunaLle,id,cantPasajes);

        }catch (ParseException | InputMismatchException e){
            System.out.println("ERROR : " + e.getMessage());
        }

    }
    private void pagaVentaPasajes() {
        System.out.println(":::: Monto total de la venta: " + sistem.getMontoVenta());
        System.out.println("\n:::: Pago de la venta ");

    }

    private void listVentas(){
        String[][] ventas = sistem.listVentas();
        if (ventas.length == 0) {
            System.out.println("No existen ventas registradas");
            return;
        }
        System.out.println("\t\t...:::: Listado de ventas ::::....");
        System.out.println("*------------*----------*------------*------------------*--------------------------------*--------------*--------------*");
        System.out.println("| ID DOCUMENT| TIPO DOCU|      FECHA |    RUT/PASAPORTE | CLIENTE                        | CANT BOLETOS |  TOTAL VENTA |");

        for (String[] v: sistem.listVentas()){
            System.out.println("|------------+----------+------------+------------------+--------------------------------+--------------+--------------|");
            System.out.printf("| %-10s | %-8s | %-10s | %-16s | %-30s | %-12s | %-12s |\n", v[0],v[1],v[2],v[3],v[4],v[5],v[6]);
        }
        System.out.println("*------------*----------*------------*------------------*--------------------------------*--------------*--------------*");
    }
    private void listViajes(){
        String[][] viajes = sistem.listViajes();
        if (viajes.length == 0 ) {
            System.out.println("No existen viajes registrados");
            return;
        }
        System.out.println("\t\t...:::: Listado de viajes ::::....\n");
        System.out.println("*--------------*--------------*--------------*--------*----------------*--------------*-----------------*------------------*");
        System.out.println("| FECHA        |    HORA SALE |   HORA LLEGA | PRECIO | ASIENTOS DISP. | PATENTE      | ORIGEN          | DESTINO          |");
        for (String[] v : sistem.listViajes()){
            System.out.println("|--------------+--------------+--------------+--------+----------------+--------------+-----------------+------------------|");
            System.out.printf("| %-12s | %-12s | %-12s | %-6s | %-14s | %-12s | %-15s | %-16s |\n", v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7]);
        }
        System.out.println("*--------------*--------------*--------------*--------*----------------*--------------*-----------------*------------------*");
    }
    private void listPasajerosViaje(){
        try {
            System.out.println("...:::: Listado de pasajeros de un viaje ::::....\n");
            System.out.println("Fecha del viaje [dd/mm/yyyy] : ");
            String fechaIN = sc.nextLine();
            Date fecha = fechaFormato.parse(fechaIN);
            System.out.println("    Hora del viaje[hh:mm] :");
            String horaIN = sc.nextLine();
            Time hora = Time.valueOf(horaIN);
            System.out.println("              Patente bus :");
            String patente = sc.nextLine();
            String[][] pasajeros = sistem.listPasajeros(fecha,hora,patente);
            if (pasajeros.length == 0){
                System.out.println("No existen pasajeros registrados");
                return;
            }
            System.out.println("*---------*-----------------*--------------------------------*--------------------------------*-------------------*");
            System.out.println("| ASIENTO |        RUT/PASS | PASAJERO                       | CONTACTO                       | TELEFONO CONTACTO |");
            for (String[] pV : sistem.listPasajeros(fecha,hora,patente)){
                System.out.println("|---------+-----------------+--------------------------------+--------------------------------+--------------------|");
                System.out.printf ("| %-7s | %-15s | %-30s | %-30s | %-18 |", pV[0], pV[1], pV[2], pV[3], pV[4]);
            }
            System.out.println("*---------*-----------------*--------------------------------*--------------------------------*--------------------*");
        }catch (ParseException e){
            System.out.println("ERROR : " + e.getMessage());
        }

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
        try {
            System.out.println("...:::: Listado de llegadas  y salidas  de un terminal ::::....\n");
            System.out.print("          Nombre terminal : ");
            String nomTerminal = sc.nextLine();
            System.out.print("        Fecha[dd/mm/yyyy] : ");
            String fechaIngresada = sc.nextLine();
            Date fecha = fechaFormato.parse(fechaIngresada);
            controlador.listLLegadasSalidasTerminal(nomTerminal,fecha);
        }catch (ParseException e){
            System.out.println("ERROR : " + e.getMessage());
        }
    }
    private void listVentasEmpresa(){
        Rut rut;
        System.out.println("...:::: Listado de ventas de una empresa ::::....\n");
        String rutEmpresa = sc.nextLine();
        rut = Rut.of(rutEmpresa);
        controlador.listVentasEmpresa(rut);
    }

}


















