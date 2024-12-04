package ec.edu.intsuperior.vista;

import javax.swing.*;

public class VistaRegistro extends JFrame {
    public JTextField txtNuevoUsuario;
    public JPasswordField txtNuevaPassword;
    public JButton btnGuardarUsuario;

    public VistaRegistro() {
        setTitle("Registro de Usuario");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        txtNuevoUsuario = new JTextField(15);
        txtNuevaPassword = new JPasswordField(15);
        btnGuardarUsuario = new JButton("Guardar");

        // Layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nuevo Usuario:"));
        panel.add(txtNuevoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtNuevaPassword);
        panel.add(btnGuardarUsuario);

        add(panel);
    }
}
