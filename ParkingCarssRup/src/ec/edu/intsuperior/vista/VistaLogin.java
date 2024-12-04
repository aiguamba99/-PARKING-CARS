package ec.edu.intsuperior.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnIniciarSesion, btnRegistrar;
    public JLabel lblMensaje;

    public VistaLogin() {
        setTitle("Parking Cars - Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitulo = new JLabel("PARKING CARS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(50, 50, 150));
        lblTitulo.setBounds(100, 20, 200, 30);
        add(lblTitulo);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Alexi\\Videos\\parking cars imagenes\\Captura de pantalla 2024-11-21 010025.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Escalar imagen a 50x50 píxeles
        JLabel lblImagen = new JLabel(new ImageIcon(scaledImage));
        lblImagen.setBounds(300, 10, 50, 50);
        add(lblImagen);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setBounds(50, 80, 100, 30);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 80, 200, 30);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setBounds(50, 120, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 120, 200, 30);
        add(txtPassword);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(50, 180, 140, 30);
        btnIniciarSesion.setBackground(new Color(60, 179, 113));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnIniciarSesion);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(210, 180, 140, 30);
        btnRegistrar.setBackground(new Color(30, 144, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnRegistrar);

        lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setBounds(50, 220, 300, 30);
        add(lblMensaje);

        getContentPane().setBackground(new Color(240, 248, 255));
    }
}
