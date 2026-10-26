
package Modelo;

public class Pasajero {

    private String nombre;
    private String apellido;
    private final String cedula;
    private String telefono;

    // CONSTRUCTOR
    public Pasajero(String nombre, String apellido,
                    String cedula, String telefono) {

        this.cedula = validarNumero(cedula, "cedula");
        actualizarDatos(nombre, apellido, telefono);
    }

    // VALIDAR NOMBRES Y APELLIDOS
    private String validarTexto(String valor, String campo) {

        if (valor == null) {
            throw new IllegalArgumentException(
                "El campo " + campo + " es obligatorio."
            );
        }

        String texto = valor.trim();

        if (!texto.matches("[\\p{L} ]{2,20}")) {
            throw new IllegalArgumentException(
                "El " + campo
                + " debe contener entre 2 y 20 caracteres, solo letras."
            );
        }

        return texto;
    }

    // VALIDAR CEDULA Y TELEFONO
    private String validarNumero(String valor, String campo) {

        if (valor == null || !valor.trim().matches("[0-9]{10}")) {
            throw new IllegalArgumentException(
                "El campo " + campo
                + " debe contener exactamente 10 digitos."
            );
        }

        return valor.trim();
    }

    // ACTUALIZAR DATOS DEL PASAJERO
    public void actualizarDatos(String nuevoNombre,
                                String nuevoApellido,
                                String nuevoTelefono) {

        String nombreValidado =
                validarTexto(nuevoNombre, "nombre");

        String apellidoValidado =
                validarTexto(nuevoApellido, "apellido");

        String telefonoValidado =
                validarNumero(nuevoTelefono, "telefono");

        this.nombre = nombreValidado;
        this.apellido = apellidoValidado;
        this.telefono = telefonoValidado;
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    // MOSTRAR INFORMACION
    @Override
    public String toString() {
        return "Nombre: " + nombre
                + " | Apellido: " + apellido
                + " | Cedula: " + cedula
                + " | Telefono: " + telefono;
    }
}
