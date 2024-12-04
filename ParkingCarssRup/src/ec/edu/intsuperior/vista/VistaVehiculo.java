package ec.edu.intsuperior.vista;

import javax.swing.*;
import java.awt.*;

public class VistaVehiculo extends JFrame {

    public JTextField txtPlaca;
    public JCheckBox chkFeriado;
    public JSpinner spnHoraEntrada, spnHoraSalida;
    public JButton btnCalcular, btnNuevoVehiculo, btnCerrarSesion, btnAdministrarUsuarios, btnVerRegistros;
    public JLabel lblResultado, lblContadorVehiculos, lblDiaSemana;

    public VistaVehiculo() {
        setTitle("Control de Vehículos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(20, 20, 100, 20);
        lblPlaca.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(120, 20, 200, 20);
        add(txtPlaca);

        chkFeriado = new JCheckBox("Es feriado");
        chkFeriado.setBounds(120, 50, 100, 20);
        add(chkFeriado);

        JLabel lblHoraEntrada = new JLabel("Hora Entrada:");
        lblHoraEntrada.setBounds(20, 80, 100, 20);
        lblHoraEntrada.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblHoraEntrada);

        spnHoraEntrada = new JSpinner(new SpinnerDateModel());
        spnHoraEntrada.setBounds(120, 80, 100, 20);
        spnHoraEntrada.setEditor(new JSpinner.DateEditor(spnHoraEntrada, "HH:mm"));
        add(spnHoraEntrada);

        JLabel lblHoraSalida = new JLabel("Hora Salida:");
        lblHoraSalida.setBounds(20, 110, 100, 20);
        lblHoraSalida.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblHoraSalida);

        spnHoraSalida = new JSpinner(new SpinnerDateModel());
        spnHoraSalida.setBounds(120, 110, 100, 20);
        spnHoraSalida.setEditor(new JSpinner.DateEditor(spnHoraSalida, "HH:mm"));
        add(spnHoraSalida);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(20, 150, 100, 30);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 12));
        add(btnCalcular);

        btnNuevoVehiculo = new JButton("Nuevo Vehículo");
        btnNuevoVehiculo.setBounds(140, 150, 150, 30);
        btnNuevoVehiculo.setFont(new Font("Arial", Font.BOLD, 12));
        add(btnNuevoVehiculo);

        lblResultado = new JLabel("Costo: ");
        lblResultado.setBounds(20, 190, 300, 20);
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblResultado);

        lblContadorVehiculos = new JLabel("Vehículos ingresados: 0");
        lblContadorVehiculos.setBounds(20, 220, 300, 20);
        lblContadorVehiculos.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblContadorVehiculos);

        lblDiaSemana = new JLabel("Hoy es: ");
        lblDiaSemana.setBounds(20, 250, 300, 20);
        lblDiaSemana.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblDiaSemana);

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(400, 20, 150, 30);
        btnCerrarSesion.setBackground(new Color(255, 99, 71));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnCerrarSesion);

        btnAdministrarUsuarios = new JButton("Administrar Usuarios");
        btnAdministrarUsuarios.setBounds(400, 60, 150, 30);
        btnAdministrarUsuarios.setBackground(new Color(60, 179, 113));
        btnAdministrarUsuarios.setForeground(Color.WHITE);
        btnAdministrarUsuarios.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdministrarUsuarios.setVisible(false);
        add(btnAdministrarUsuarios);

        btnVerRegistros = new JButton("Ver Registros");
        btnVerRegistros.setBounds(400, 100, 150, 30);
        btnVerRegistros.setBackground(new Color(70, 130, 180));
        btnVerRegistros.setForeground(Color.WHITE);
        btnVerRegistros.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnVerRegistros);

        JLabel imagenPequena = new JLabel(new ImageIcon("C:\\Users\\Alexi\\Videos\\parking cars imagenes\\pequena.png"));
        imagenPequena.setBounds(500, 300, 80, 80);
        add(imagenPequena);

        getContentPane().setBackground(new Color(230, 240, 250));
    }
}
