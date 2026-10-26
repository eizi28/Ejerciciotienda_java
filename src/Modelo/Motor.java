
package Modelo;

public class Motor {

    private int cilindraje;
    private String tipo;
    private final String numeroSerie;

    public Motor(int cilindraje, String tipo, String numeroSerie) {
        this.numeroSerie = validarSerie(numeroSerie);
        cambiarCaracteristicas(cilindraje, tipo);
    }

    private String validarSerie(String serie) {
        if (serie == null || serie.trim().length() < 10
                || serie.trim().length() > 20) {
            throw new IllegalArgumentException(
                "La serie debe tener entre 10 y 20 caracteres."
            );
        }

        return serie.trim().toUpperCase();
    }

    private String validarTipo(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(
                "Debe indicar un tipo de motor."
            );
        }

        String valor = tipo.trim();

        if (!valor.equalsIgnoreCase("Diesel")
                && !valor.equalsIgnoreCase("Gasolina")
                && !valor.equalsIgnoreCase("Hibrido")
                && !valor.equalsIgnoreCase("Electrico")) {
            throw new IllegalArgumentException(
                "Tipo permitido: Diesel, Gasolina, Hibrido o Electrico."
            );
        }

        return valor;
    }

    public void cambiarCaracteristicas(int nuevoCilindraje,
                                        String nuevoTipo) {
        if (nuevoCilindraje < 500 || nuevoCilindraje > 20000) {
            throw new IllegalArgumentException(
                "El cilindraje debe estar entre 500 y 20000."
            );
        }

        String tipoValidado = validarTipo(nuevoTipo);

        this.cilindraje = nuevoCilindraje;
        this.tipo = tipoValidado;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    @Override
    public String toString() {
        return "Serie: " + numeroSerie
                + " | Tipo: " + tipo
                + " | Cilindraje: " + cilindraje + " cc";
    }
}
