
package Main;

import Modelo.Api;
import Controlador.ControladorGeneral;
import Vista.VistaGeneral;

public class Main {

    public static void main(String[] args) {

        Api datos = new Api();

        ControladorGeneral controlador =
                new ControladorGeneral(datos);

        VistaGeneral vista =
                new VistaGeneral(controlador);

        System.out.println("Sistema iniciado correctamente.");

        vista.iniciar();
    }
}
