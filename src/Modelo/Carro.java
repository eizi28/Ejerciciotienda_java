
package Modelo;

public class Carro {

    private String marca;
    private String placa;
    private String modelo;

    public Carro(String marca, String placa, String modelo) {
        cambiarMarca(marca);
        asignarPlaca(placa);
        cambiarModelo(modelo);
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void cambiarMarca(String nuevaMarca) {
        if (nuevaMarca == null ||
                nuevaMarca.trim().length() < 2 ||
                nuevaMarca.trim().length() > 30) {

            throw new IllegalArgumentException(
                "La marca debe tener entre 2 y 30 caracteres."
            );
        }

        this.marca = nuevaMarca.trim();
    }

    private void asignarPlaca(String nuevaPlaca) {
        if (nuevaPlaca == null ||
                !nuevaPlaca.trim().matches("[a-zA-Z0-9]{6,7}")) {

            throw new IllegalArgumentException(
                "La placa debe contener 6 o 7 caracteres alfanumericos."
            );
        }

        this.placa = nuevaPlaca.trim().toUpperCase();
    }

    public void cambiarModelo(String nuevoModelo) {
        if (nuevoModelo == null ||
                nuevoModelo.trim().length() < 2 ||
                nuevoModelo.trim().length() > 25) {

            throw new IllegalArgumentException(
                "El modelo debe tener entre 2 y 25 caracteres."
            );
        }

        this.modelo = nuevoModelo.trim();
    }

    @Override
    public String toString() {
        return "Placa: " + placa
                + " | Marca: " + marca
                + " | Modelo: " + modelo;
    }
}
