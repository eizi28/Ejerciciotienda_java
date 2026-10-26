
package Modelo;

public class RegistroViaje {

    private final Carro carro;
    private final Motor motor;
    private final Chofer chofer;
    private final Pasajero pasajero;

    // CONSTRUCTOR
    public RegistroViaje(Carro carro, Motor motor,
                         Chofer chofer, Pasajero pasajero) {

        if (carro == null || motor == null ||
            chofer == null || pasajero == null) {

            throw new IllegalArgumentException(
                "El viaje necesita un carro, motor, chofer y pasajero."
            );
        }

        this.carro = carro;
        this.motor = motor;
        this.chofer = chofer;
        this.pasajero = pasajero;
    }

    // GETTERS
    public Carro getCarro() {
        return carro;
    }

    public Motor getMotor() {
        return motor;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    // MOSTRAR DETALLES DEL VIAJE
    @Override
    public String toString() {
        return "Carro: " + carro.getPlaca()
                + "\nMotor: " + motor.getNumeroSerie()
                + "\nChofer: " + chofer.getNombre()
                + " - " + chofer.getCedula()
                + "\nPasajero: " + pasajero.getNombre()
                + " " + pasajero.getApellido()
                + " - " + pasajero.getCedula();
    }
}
