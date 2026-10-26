
package Vista;

import Controlador.ControladorGeneral;
import java.util.Scanner;

public class VistaGeneral {

    private final Scanner teclado;
    private final ControladorGeneral controlador;

    public VistaGeneral(ControladorGeneral controlador) {
        this.controlador = controlador;
        this.teclado = new Scanner(System.in);
    }

    // LEER DATOS DEL USUARIO
    private String pedir(String mensaje) {
        System.out.print(mensaje + ": ");
        return teclado.nextLine().trim();
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(pedir("Seleccione una opcion"));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // MENU PRINCIPAL
    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n================================");
            System.out.println("    SISTEMA DE TRANSPORTE");
            System.out.println("================================");
            System.out.println("1. Gestion de carros");
            System.out.println("2. Gestion de choferes");
            System.out.println("3. Gestion de motores");
            System.out.println("4. Gestion de pasajeros");
            System.out.println("5. Registro de viajes");
            System.out.println("0. Salir");

            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> menuGestion("CARROS");
                case 2 -> menuGestion("CHOFERES");
                case 3 -> menuGestion("MOTORES");
                case 4 -> menuGestion("PASAJEROS");
                case 5 -> menuViajes();
                case 0 -> System.out.println(
                        "Gracias por utilizar el sistema.");
                default -> System.out.println(
                        "Opcion no valida.");
            }

        } while (opcion != 0);
    }

    // MENU COMPARTIDO PARA LOS CUATRO MODULOS
    private void menuGestion(String modulo) {
        int opcion;

        do {
            System.out.println("\n===== " + modulo + " =====");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");

            opcion = leerOpcion();

            if (opcion >= 1 && opcion <= 5) {
                ejecutarOperacion(modulo, opcion);
            } else if (opcion != 0) {
                System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    // DISTRIBUIR LAS OPERACIONES AL CONTROLADOR
    private void ejecutarOperacion(String modulo, int opcion) {

        String resultado = "";

        switch (modulo) {

            case "CARROS" -> resultado = operarCarros(opcion);

            case "CHOFERES" -> resultado = operarChoferes(opcion);

            case "MOTORES" -> resultado = operarMotores(opcion);

            case "PASAJEROS" -> resultado = operarPasajeros(opcion);

            default -> resultado = "Modulo desconocido.";
        }

        System.out.println("\n" + resultado);
    }

    // ====================================
    //            CARROS
    // ====================================
    private String operarCarros(int opcion) {

        switch (opcion) {
            case 1 -> {
                String marca = pedir("Marca");
                String placa = pedir("Placa");
                String modelo = pedir("Modelo");

                return controlador.registrarCarro(
                        marca, placa, modelo);
            }

            case 2 -> {
                return controlador.listarCarros();
            }

            case 3 -> {
                String placa = pedir("Placa a buscar");
                return controlador.buscarCarro(placa);
            }

            case 4 -> {
                String placa = pedir("Placa del carro");
                String marca = pedir("Nueva marca");
                String modelo = pedir("Nuevo modelo");

                return controlador.actualizarCarro(
                        placa, marca, modelo);
            }

            case 5 -> {
                String placa = pedir("Placa a eliminar");
                return controlador.eliminarCarro(placa);
            }

            default -> {
                return "Operacion no valida.";
            }
        }
    }

    // ====================================
    //            CHOFERES
    // ====================================
    private String operarChoferes(int opcion) {

        switch (opcion) {
            case 1 -> {
                String nombre = pedir("Nombre");
                String cedula = pedir("Cedula");
                String licencia = pedir("Licencia");

                return controlador.registrarChofer(
                        nombre, cedula, licencia);
            }

            case 2 -> {
                return controlador.listarChoferes();
            }

            case 3 -> {
                String cedula = pedir("Cedula a buscar");
                return controlador.buscarChofer(cedula);
            }

            case 4 -> {
                String cedula = pedir("Cedula del chofer");
                String nombre = pedir("Nuevo nombre");
                String licencia = pedir("Nueva licencia");

                return controlador.actualizarChofer(
                        cedula, nombre, licencia);
            }

            case 5 -> {
                String cedula = pedir("Cedula a eliminar");
                return controlador.eliminarChofer(cedula);
            }

            default -> {
                return "Operacion no valida.";
            }
        }
    }

    // ====================================
    //            MOTORES
    // ====================================
    private String operarMotores(int opcion) {

        switch (opcion) {
            case 1 -> {
                String tipo = pedir("Tipo de motor");
                String serie = pedir("Numero de serie");
                String cilindraje = pedir("Cilindraje");

                return controlador.registrarMotor(
                        tipo, serie, cilindraje);
            }

            case 2 -> {
                return controlador.listarMotores();
            }

            case 3 -> {
                String serie = pedir("Serie a buscar");
                return controlador.buscarMotor(serie);
            }

            case 4 -> {
                String serie = pedir("Serie del motor");
                String tipo = pedir("Nuevo tipo");
                String cilindraje = pedir("Nuevo cilindraje");

                return controlador.actualizarMotor(
                        serie, tipo, cilindraje);
            }

            case 5 -> {
                String serie = pedir("Serie a eliminar");
                return controlador.eliminarMotor(serie);
            }

            default -> {
                return "Operacion no valida.";
            }
        }
    }

    // ====================================
    //            PASAJEROS
    // ====================================
    private String operarPasajeros(int opcion) {

        switch (opcion) {
            case 1 -> {
                String nombre = pedir("Nombre");
                String apellido = pedir("Apellido");
                String cedula = pedir("Cedula");
                String telefono = pedir("Telefono");

                return controlador.registrarPasajero(
                        nombre, apellido, cedula, telefono);
            }

            case 2 -> {
                return controlador.listarPasajeros();
            }

            case 3 -> {
                String cedula = pedir("Cedula a buscar");
                return controlador.buscarPasajero(cedula);
            }

            case 4 -> {
                String cedula = pedir("Cedula del pasajero");
                String nombre = pedir("Nuevo nombre");
                String apellido = pedir("Nuevo apellido");
                String telefono = pedir("Nuevo telefono");

                return controlador.actualizarPasajero(
                        cedula, nombre, apellido, telefono);
            }

            case 5 -> {
                String cedula = pedir("Cedula a eliminar");
                return controlador.eliminarPasajero(cedula);
            }

            default -> {
                return "Operacion no valida.";
            }
        }
    }

    // ====================================
    //            VIAJES
    // ====================================
    private void menuViajes() {
        int opcion;

        do {
            System.out.println("\n===== GESTION DE VIAJES =====");
            System.out.println("1. Registrar viaje");
            System.out.println("2. Consultar viajes");
            System.out.println("0. Volver");

            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> registrarViaje();
                case 2 -> System.out.println(
                        controlador.listarViajes());
                case 0 -> System.out.println(
                        "Regresando al menu principal.");
                default -> System.out.println(
                        "Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    private void registrarViaje() {

        System.out.println("\n--- NUEVO VIAJE ---");

        String placa = pedir("Placa del carro");
        String serie = pedir("Serie del motor");
        String chofer = pedir("Cedula del chofer");
        String pasajero = pedir("Cedula del pasajero");

        String resultado = controlador.registrarViaje(
                placa, serie, chofer, pasajero);

        System.out.println(resultado);
    }
}
