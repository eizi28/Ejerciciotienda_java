
package Controlador;

import Modelo.Api;

public class ControladorGeneral {

    private final Api datos;

    public ControladorGeneral(Api datos) {
        if (datos == null) {
            throw new IllegalArgumentException(
                "El sistema de datos es obligatorio."
            );
        }
        this.datos = datos;
    }

    // =====================================
    //             CARROS
    // =====================================

    public String registrarCarro(String marca,
                                String placa,
                                String modelo) {
        return datos.guardarCarro(marca, placa, modelo);
    }

    public String listarCarros() {
        return datos.consultarCarros();
    }

    public String buscarCarro(String placa) {
        return datos.consultarCarro(placa);
    }

    public String actualizarCarro(String placa,
                                 String marca,
                                 String modelo) {
        return datos.modificarCarro(placa, marca, modelo);
    }

    public String eliminarCarro(String placa) {
        return datos.borrarCarro(placa);
    }

    // =====================================
    //             MOTORES
    // =====================================

    public String registrarMotor(String tipo,
                                String serie,
                                String cilindraje) {
        try {
            int valor = Integer.parseInt(cilindraje.trim());
            return datos.guardarMotor(valor, tipo, serie);
        } catch (NumberFormatException | NullPointerException e) {
            return "Error: El cilindraje debe ser un numero entero.";
        }
    }

    public String listarMotores() {
        return datos.consultarMotores();
    }

    public String buscarMotor(String serie) {
        return datos.consultarMotor(serie);
    }

    public String actualizarMotor(String serie,
                                 String tipo,
                                 String cilindraje) {
        try {
            int valor = Integer.parseInt(cilindraje.trim());
            return datos.modificarMotor(serie, valor, tipo);
        } catch (NumberFormatException | NullPointerException e) {
            return "Error: El cilindraje debe ser un numero entero.";
        }
    }

    public String eliminarMotor(String serie) {
        return datos.borrarMotor(serie);
    }

    // =====================================
    //             CHOFERES
    // =====================================

    public String registrarChofer(String nombre,
                                 String cedula,
                                 String licencia) {
        return datos.guardarChofer(nombre, cedula, licencia);
    }

    public String listarChoferes() {
        return datos.consultarChoferes();
    }

    public String buscarChofer(String cedula) {
        return datos.consultarChofer(cedula);
    }

    public String actualizarChofer(String cedula,
                                  String nombre,
                                  String licencia) {
        return datos.modificarChofer(cedula, nombre, licencia);
    }

    public String eliminarChofer(String cedula) {
        return datos.borrarChofer(cedula);
    }

    // =====================================
    //             PASAJEROS
    // =====================================

    public String registrarPasajero(String nombre,
                                   String apellido,
                                   String cedula,
                                   String telefono) {
        return datos.guardarPasajero(
            nombre, apellido, cedula, telefono
        );
    }

    public String listarPasajeros() {
        return datos.consultarPasajeros();
    }

    public String buscarPasajero(String cedula) {
        return datos.consultarPasajero(cedula);
    }

    public String actualizarPasajero(String cedula,
                                    String nombre,
                                    String apellido,
                                    String telefono) {
        return datos.modificarPasajero(
            cedula, nombre, apellido, telefono
        );
    }

    public String eliminarPasajero(String cedula) {
        return datos.borrarPasajero(cedula);
    }

    // =====================================
    //             VIAJES
    // =====================================

    public String registrarViaje(String placa,
                                String serie,
                                String cedulaChofer,
                                String cedulaPasajero) {
        return datos.guardarViaje(
            placa, serie, cedulaChofer, cedulaPasajero
        );
    }

    public String listarViajes() {
        return datos.consultarViajes();
    }
}
