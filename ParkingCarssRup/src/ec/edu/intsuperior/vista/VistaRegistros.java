package ec.edu.intsuperior.vista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaRegistros extends JFrame {

    public JTable tablaRegistros;
    public JButton btnBorrarRegistros;
    public JButton btnCalcularValorTotal;
    public JLabel lblTotalValor;

    public VistaRegistros() {
        setTitle("Registros de Vehículos");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tablaRegistros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaRegistros);
        add(scrollPane, BorderLayout.CENTER);

        btnBorrarRegistros = new JButton("Borrar todos los registros");
        btnBorrarRegistros.addActionListener(e -> borrarRegistros());
        add(btnBorrarRegistros, BorderLayout.SOUTH);

        btnCalcularValorTotal = new JButton("Calcular valor total de registros");
        btnCalcularValorTotal.addActionListener(e -> calcularValorTotal());
        add(btnCalcularValorTotal, BorderLayout.NORTH);

        lblTotalValor = new JLabel("Total a pagar: $0.00");
        add(lblTotalValor, BorderLayout.WEST);

        tablaRegistros.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Placa", "Hora Entrada", "Hora Salida", "Valor a pagar"}
        ));

        setLocationRelativeTo(null);
    }

    public void cargarRegistros(String[][] registros) {
        DefaultTableModel modelo = (DefaultTableModel) tablaRegistros.getModel();
        modelo.setRowCount(0);

        for (String[] registro : registros) {

            String placa = registro[0];
            String horaEntrada = registro[1].substring(0, 5);
            String horaSalida = registro[2].substring(0, 5);
            String valorAPagar = registro[3];

            modelo.addRow(new Object[]{placa, horaEntrada, horaSalida, valorAPagar});
        }
    }

    private void borrarRegistros() {

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas borrar todos los registros?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            DefaultTableModel modelo = (DefaultTableModel) tablaRegistros.getModel();
            modelo.setRowCount(0);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt"))) {
                writer.write("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar los registros del archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "Todos los registros han sido borrados.");
        }
    }

    private void calcularValorTotal() {
        DefaultTableModel modelo = (DefaultTableModel) tablaRegistros.getModel();
        double total = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                double valorAPagar = Double.parseDouble((String) modelo.getValueAt(i, 3));
                total += valorAPagar;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al calcular el valor total: un valor no es numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        lblTotalValor.setText("Total a pagar: $" + String.format("%.2f", total));
        JOptionPane.showMessageDialog(this, "El valor total de todos los registros es: $" + String.format("%.2f", total));
    }
}
