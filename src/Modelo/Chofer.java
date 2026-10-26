
package Modelo;

public class Chofer {

    private String nombre;
    private final String cedula;
    private String licencia;

    public Chofer(String nombre, String cedula, String licencia) {
        this.cedula = comprobarCedula(cedula);
        actualizarDatos(nombre, licencia);
    }

    private String comprobarCedula(String cedula) {
        if (cedula == null || !cedula.matches("[0-9]{10}")) {
            throw new IllegalArgumentException(
                "La cedula debe tener exactamente 10 numeros."
            );
        }

        return cedula;
    }

    private String comprobarNombre(String nombre) {
        if (nombre == null ||
                !nombre.trim().matches("[\\p{L} ]{2,30}")) {
            throw new IllegalArgumentException(
                "El nombre debe contener de 2 a 30 letras."
            );
        }

        return nombre.trim();
    }

    private String comprobarLicencia(String licencia) {
        if (licencia == null ||
                !licencia.trim().matches("[A-Za-z0-9]{6,12}")) {
            throw new IllegalArgumentException(
                "La licencia debe tener entre 6 y 12 caracteres alfanumericos."
            );
        }

        return licencia.trim().toUpperCase();
    }

    public void actualizarDatos(String nuevoNombre,
                                String nuevaLicencia) {

        String nombreValidado = comprobarNombre(nuevoNombre);
        String licenciaValidada = comprobarLicencia(nuevaLicencia);

        this.nombre = nombreValidado;
        this.licencia = licenciaValidada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getLicencia() {
        return licencia;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + " | Cedula: " + cedula
                + " | Licencia: " + licencia;
    }
}
