package ec.edu.intsuperior.controlador;

import ec.edu.intsuperior.vista.VistaLogin;

public class App {

    public static void main(String[] args) {
        VistaLogin vistaLogin = new VistaLogin();
        new ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);
    }
}

