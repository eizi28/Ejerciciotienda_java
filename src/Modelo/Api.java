
package Modelo;

import java.util.ArrayList;
import java.util.List;

/** Almacena la informacion del sistema en memoria. */
public class Api {

    private final Carro[][] carros = new Carro[3][3];
    private final Motor[][] motores = new Motor[3][3];
    private final List<Chofer> choferes = new ArrayList<>();
    private final List<Pasajero> pasajeros = new ArrayList<>();
    private final List<RegistroViaje> viajes = new ArrayList<>();

    // =========================================
    //               CARROS
    // =========================================

    private int[] ubicarCarro(String placa) {
        if (placa == null) return null;

        for (int f = 0; f < carros.length; f++) {
            for (int c = 0; c < carros[f].length; c++) {
                if (carros[f][c] != null &&
                    carros[f][c].getPlaca().equalsIgnoreCase(placa.trim())) {
                    return new int[]{f, c};
                }
            }
        }
        return null;
    }

    public String guardarCarro(String marca, String placa, String modelo) {
        if (ubicarCarro(placa) != null) {
            return "Ya existe un carro con esa placa.";
        }

        try {
            Carro nuevo = new Carro(marca, placa, modelo);

            for (int f = 0; f < carros.length; f++) {
                for (int c = 0; c < carros[f].length; c++) {
                    if (carros[f][c] == null) {
                        carros[f][c] = nuevo;
                        return "Carro registrado en [" + f + "][" + c + "].";
                    }
                }
            }

            return "La matriz de carros esta llena (maximo 9).";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String consultarCarros() {
        StringBuilder texto = new StringBuilder();

        for (int f = 0; f < carros.length; f++) {
            for (int c = 0; c < carros[f].length; c++) {
                texto.append("[")
                     .append(f).append("][")
                     .append(c).append("] ")
                     .append(carros[f][c] == null
                         ? "(Vacio)" : carros[f][c])
                     .append('\n');
            }
        }

        return texto.toString();
    }

    public String consultarCarro(String placa) {
        int[] p = ubicarCarro(placa);

        return p == null
            ? "Carro no encontrado."
            : carros[p[0]][p[1]].toString();
    }

    public String modificarCarro(String placa, String marca, String modelo) {
        int[] p = ubicarCarro(placa);

        if (p == null) {
            return "Carro no encontrado.";
        }

        try {
            // Validar ambos campos antes de realizar cambios
            Carro validado = new Carro(
                marca, carros[p[0]][p[1]].getPlaca(), modelo
            );

            carros[p[0]][p[1]].cambiarMarca(validado.getMarca());
            carros[p[0]][p[1]].cambiarModelo(validado.getModelo());

            return "Carro actualizado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String borrarCarro(String placa) {
        int[] p = ubicarCarro(placa);

        if (p == null) {
            return "Carro no encontrado.";
        }

        if (utilizadoEnViajes(carros[p[0]][p[1]])) {
            return "No se puede eliminar: carro asociado a un viaje.";
        }

        carros[p[0]][p[1]] = null;
        return "Carro eliminado correctamente.";
    }

    // =========================================
    //               MOTORES
    // =========================================

    private int[] ubicarMotor(String serie) {
        if (serie == null) return null;

        for (int f = 0; f < motores.length; f++) {
            for (int c = 0; c < motores[f].length; c++) {
                if (motores[f][c] != null &&
                    motores[f][c].getNumeroSerie()
                        .equalsIgnoreCase(serie.trim())) {
                    return new int[]{f, c};
                }
            }
        }

        return null;
    }

    public String guardarMotor(int cilindraje, String tipo, String serie) {
        if (ubicarMotor(serie) != null) {
            return "Ya existe un motor con esa serie.";
        }

        try {
            Motor nuevo = new Motor(cilindraje, tipo, serie);

            for (int f = 0; f < motores.length; f++) {
                for (int c = 0; c < motores[f].length; c++) {
                    if (motores[f][c] == null) {
                        motores[f][c] = nuevo;
                        return "Motor registrado en [" + f + "][" + c + "].";
                    }
                }
            }

            return "La matriz de motores esta llena (maximo 9).";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String consultarMotores() {
        StringBuilder texto = new StringBuilder();

        for (int f = 0; f < motores.length; f++) {
            for (int c = 0; c < motores[f].length; c++) {
                texto.append("[")
                     .append(f).append("][")
                     .append(c).append("] ")
                     .append(motores[f][c] == null
                         ? "(Vacio)" : motores[f][c])
                     .append('\n');
            }
        }

        return texto.toString();
    }

    public String consultarMotor(String serie) {
        int[] p = ubicarMotor(serie);

        return p == null
            ? "Motor no encontrado."
            : motores[p[0]][p[1]].toString();
    }

    public String modificarMotor(String serie, int cilindraje, String tipo) {
        int[] p = ubicarMotor(serie);

        if (p == null) {
            return "Motor no encontrado.";
        }

        try {
            motores[p[0]][p[1]]
                .cambiarCaracteristicas(cilindraje, tipo);

            return "Motor actualizado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String borrarMotor(String serie) {
        int[] p = ubicarMotor(serie);

        if (p == null) {
            return "Motor no encontrado.";
        }

        if (utilizadoEnViajes(motores[p[0]][p[1]])) {
            return "No se puede eliminar: motor asociado a un viaje.";
        }

        motores[p[0]][p[1]] = null;
        return "Motor eliminado correctamente.";
    }

    // =========================================
    //               CHOFERES
    // =========================================

    private Chofer localizarChofer(String cedula) {
        if (cedula == null) return null;

        for (Chofer chofer : choferes) {
            if (chofer.getCedula().equals(cedula.trim())) {
                return chofer;
            }
        }

        return null;
    }

    public String guardarChofer(String nombre, String cedula, String licencia) {
        if (localizarChofer(cedula) != null) {
            return "Ya existe un chofer con esa cedula.";
        }

        try {
            choferes.add(new Chofer(nombre, cedula, licencia));
            return "Chofer registrado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String consultarChoferes() {
        if (choferes.isEmpty()) {
            return "No hay choferes registrados.";
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < choferes.size(); i++) {
            texto.append(i + 1)
                 .append(". ")
                 .append(choferes.get(i))
                 .append('\n');
        }

        return texto.toString();
    }

    public String consultarChofer(String cedula) {
        Chofer ch = localizarChofer(cedula);

        return ch == null
            ? "Chofer no encontrado."
            : ch.toString();
    }

    public String modificarChofer(String cedula, String nombre,
                                 String licencia) {
        Chofer ch = localizarChofer(cedula);

        if (ch == null) {
            return "Chofer no encontrado.";
        }

        try {
            ch.actualizarDatos(nombre, licencia);
            return "Chofer actualizado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String borrarChofer(String cedula) {
        Chofer ch = localizarChofer(cedula);

        if (ch == null) {
            return "Chofer no encontrado.";
        }

        if (utilizadoEnViajes(ch)) {
            return "No se puede eliminar: chofer asociado a un viaje.";
        }

        choferes.remove(ch);
        return "Chofer eliminado correctamente.";
    }

    // =========================================
    //               PASAJEROS
    // =========================================

    private Pasajero localizarPasajero(String cedula) {
        if (cedula == null) return null;

        for (Pasajero pasajero : pasajeros) {
            if (pasajero.getCedula().equals(cedula.trim())) {
                return pasajero;
            }
        }

        return null;
    }

    public String guardarPasajero(String nombre, String apellido,
                                 String cedula, String telefono) {
        if (localizarPasajero(cedula) != null) {
            return "Ya existe un pasajero con esa cedula.";
        }

        try {
            pasajeros.add(new Pasajero(nombre, apellido, cedula, telefono));
            return "Pasajero registrado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String consultarPasajeros() {
        if (pasajeros.isEmpty()) {
            return "No hay pasajeros registrados.";
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < pasajeros.size(); i++) {
            texto.append(i + 1)
                 .append(". ")
                 .append(pasajeros.get(i))
                 .append('\n');
        }

        return texto.toString();
    }

    public String consultarPasajero(String cedula) {
        Pasajero p = localizarPasajero(cedula);

        return p == null
            ? "Pasajero no encontrado."
            : p.toString();
    }

    public String modificarPasajero(String cedula, String nombre,
                                   String apellido, String telefono) {
        Pasajero p = localizarPasajero(cedula);

        if (p == null) {
            return "Pasajero no encontrado.";
        }

        try {
            p.actualizarDatos(nombre, apellido, telefono);
            return "Pasajero actualizado correctamente.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String borrarPasajero(String cedula) {
        Pasajero p = localizarPasajero(cedula);

        if (p == null) {
            return "Pasajero no encontrado.";
        }

        if (utilizadoEnViajes(p)) {
            return "No se puede eliminar: pasajero asociado a un viaje.";
        }

        pasajeros.remove(p);
        return "Pasajero eliminado correctamente.";
    }

    // =========================================
    //               VIAJES
    // =========================================

    public String guardarViaje(String placa, String serie,
                               String cedulaChofer, String cedulaPasajero) {

        int[] pc = ubicarCarro(placa);

        if (pc == null) {
            return "No se puede registrar: el carro no existe.";
        }

        int[] pm = ubicarMotor(serie);

        if (pm == null) {
            return "No se puede registrar: el motor no existe.";
        }

        Chofer chofer = localizarChofer(cedulaChofer);

        if (chofer == null) {
            return "No se puede registrar: el chofer no existe.";
        }

        Pasajero pasajero = localizarPasajero(cedulaPasajero);

        if (pasajero == null) {
            return "No se puede registrar: el pasajero no existe.";
        }

        RegistroViaje viaje = new RegistroViaje(
            carros[pc[0]][pc[1]],
            motores[pm[0]][pm[1]],
            chofer,
            pasajero
        );

        viajes.add(viaje);

        return "Viaje registrado correctamente. Numero: " + viajes.size();
    }

    public String consultarViajes() {
        if (viajes.isEmpty()) {
            return "No hay viajes registrados.";
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < viajes.size(); i++) {
            texto.append("--- Viaje ")
                 .append(i + 1)
                 .append(" ---\n")
                 .append(viajes.get(i))
                 .append("\n\n");
        }

        return texto.toString();
    }

    // =========================================
    //        VALIDAR RELACIONES DE VIAJES
    // =========================================

    private boolean utilizadoEnViajes(Object elemento) {
        for (RegistroViaje viaje : viajes) {
            if (viaje.getCarro() == elemento ||
                viaje.getMotor() == elemento ||
                viaje.getChofer() == elemento ||
                viaje.getPasajero() == elemento) {

                return true;
            }
        }

        return false;
    }
}
