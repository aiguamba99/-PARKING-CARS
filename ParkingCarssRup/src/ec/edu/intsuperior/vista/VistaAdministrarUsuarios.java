package ec.edu.intsuperior.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaAdministrarUsuarios extends JFrame {

    public JTable tablaUsuarios;
    public JButton btnEliminarUsuario;

    public VistaAdministrarUsuarios() {
        setTitle("Administrar Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Administrar Usuarios", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 10, 300, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        add(lblTitulo);

        tablaUsuarios = new JTable(new DefaultTableModel(new Object[]{"Usuario"}, 0));
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(20, 50, 350, 150);
        add(scrollPane);

        btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnEliminarUsuario.setBounds(120, 220, 150, 30);
        add(btnEliminarUsuario);
    }
}
