package ec.edu.intsuperior.controlador;

import ec.edu.intsuperior.vista.VistaAdministrarUsuarios;
import ec.edu.intsuperior.vista.VistaLogin;
import ec.edu.intsuperior.vista.VistaRegistro;
import ec.edu.intsuperior.vista.VistaVehiculo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ControladorLogin {

    private VistaLogin vistaLogin;

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;

        this.vistaLogin.btnIniciarSesion.addActionListener(e -> validarCredenciales());
        this.vistaLogin.btnRegistrar.addActionListener(e -> mostrarVentanaRegistro());

        this.vistaLogin.setLocationRelativeTo(null);
    }

    private void validarCredenciales() {
        String usuario = vistaLogin.txtUsuario.getText();
        String password = new String(vistaLogin.txtPassword.getPassword());

        if (usuario.equals("admin") && password.equals("123")) {
            abrirInterfazAdmin();
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] credenciales = linea.split(":");
                if (credenciales[0].equals(usuario) && credenciales[1].equals(password)) {
                    abrirInterfazUsuario();
                    return;
                }
            }
            vistaLogin.lblMensaje.setText("Credenciales incorrectas");
        } catch (IOException e) {
            vistaLogin.lblMensaje.setText("Error al leer usuarios");
        }
    }

    private void abrirInterfazAdmin() {
        vistaLogin.dispose();
        VistaVehiculo vistaVehiculo = new VistaVehiculo();
        ControladorVehiculo controladorVehiculo = new ControladorVehiculo(vistaVehiculo);

        vistaVehiculo.btnAdministrarUsuarios.setVisible(true);
        vistaVehiculo.btnAdministrarUsuarios.addActionListener(e -> mostrarVentanaAdministrarUsuarios());

        vistaVehiculo.btnCerrarSesion.addActionListener(e -> {
            vistaVehiculo.dispose();
            new ControladorLogin(new VistaLogin()).vistaLogin.setVisible(true);
        });

        vistaVehiculo.setLocationRelativeTo(null);
        vistaVehiculo.setVisible(true);
    }

    private void abrirInterfazUsuario() {
        vistaLogin.dispose();
        VistaVehiculo vistaVehiculo = new VistaVehiculo();
        ControladorVehiculo controladorVehiculo = new ControladorVehiculo(vistaVehiculo);

        vistaVehiculo.btnCerrarSesion.addActionListener(e -> {
            vistaVehiculo.dispose();
            new ControladorLogin(new VistaLogin()).vistaLogin.setVisible(true);
        });

        vistaVehiculo.setLocationRelativeTo(null);
        vistaVehiculo.setVisible(true);
    }

    private void mostrarVentanaAdministrarUsuarios() {
        VistaAdministrarUsuarios vistaAdmin = new VistaAdministrarUsuarios();

        DefaultTableModel modelo = (DefaultTableModel) vistaAdmin.tablaUsuarios.getModel();
        cargarUsuariosEnTabla(modelo);

        vistaAdmin.btnEliminarUsuario.addActionListener(e -> eliminarUsuarioSeleccionado(vistaAdmin, modelo));
        vistaAdmin.setLocationRelativeTo(null);
        vistaAdmin.setVisible(true);
    }

    private void cargarUsuariosEnTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] credenciales = linea.split(":");
                if (!credenciales[0].equals("admin")) {
                    modelo.addRow(new Object[]{credenciales[0]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios.");
        }
    }

    private void eliminarUsuarioSeleccionado(VistaAdministrarUsuarios vistaAdmin, DefaultTableModel modelo) {
        int filaSeleccionada = vistaAdmin.tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vistaAdmin, "Seleccione un usuario para eliminar.");
            return;
        }

        String usuarioAEliminar = (String) modelo.getValueAt(filaSeleccionada, 0);

        int confirmacion = JOptionPane.showConfirmDialog(
                vistaAdmin,
                "¿Está seguro de que desea eliminar al usuario " + usuarioAEliminar + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {

                File archivoOriginal = new File("usuarios.txt");
                File archivoTemp = new File("usuarios_temp.txt");

                try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal)); BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {

                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] credenciales = linea.split(":");
                        if (!credenciales[0].equals(usuarioAEliminar)) {
                            bw.write(linea);
                            bw.newLine();
                        }
                    }
                }

                if (archivoOriginal.delete()) {
                    archivoTemp.renameTo(archivoOriginal);
                    modelo.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(vistaAdmin, "Usuario eliminado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(vistaAdmin, "No se pudo eliminar el archivo original.");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(vistaAdmin, "Error al eliminar usuario.");
            }
        }
    }

    private void mostrarVentanaRegistro() {
        VistaRegistro vistaRegistro = new VistaRegistro();
        vistaRegistro.btnGuardarUsuario.addActionListener(e -> registrarNuevoUsuario(vistaRegistro));
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
    }

    private void registrarNuevoUsuario(VistaRegistro vistaRegistro) {
        String nuevoUsuario = vistaRegistro.txtNuevoUsuario.getText();
        String nuevaPassword = new String(vistaRegistro.txtNuevaPassword.getPassword());

        if (nuevoUsuario.isEmpty() || nuevaPassword.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro, "Debe llenar ambos campos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            long usuariosExistentes = br.lines().count();
            if (usuariosExistentes >= 10) {
                JOptionPane.showMessageDialog(vistaRegistro, "No se pueden registrar más de 10 usuarios.");
                return;
            }
        } catch (IOException e) {
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            bw.write(nuevoUsuario + ":" + nuevaPassword);
            bw.newLine();
            JOptionPane.showMessageDialog(vistaRegistro, "Usuario registrado exitosamente.");
            vistaRegistro.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vistaRegistro, "Error al registrar usuario.");
        }
    }
}
