package ec.edu.intsuperior.controlador;

import ec.edu.intsuperior.modelo.Vehiculo;
import ec.edu.intsuperior.vista.VistaVehiculo;
import ec.edu.intsuperior.vista.VistaRegistros;
import javax.swing.*;
import java.io.*;
import java.time.LocalTime;

public class ControladorVehiculo {

    private final VistaVehiculo vista;
    private int contadorVehiculos = 0;

    public ControladorVehiculo(VistaVehiculo vista) {
        this.vista = vista;

        vista.btnCalcular.addActionListener(e -> calcularCosto());
        vista.btnNuevoVehiculo.addActionListener(e -> reiniciarCampos());
        vista.btnVerRegistros.addActionListener(e -> verRegistros());
        iniciarReloj();
    }

    private void iniciarReloj() {
        vista.lblDiaSemana.setText("Hoy es: " + java.time.LocalDate.now().getDayOfWeek());
    }

    public void calcularCosto() {
        try {
            String placa = vista.txtPlaca.getText().trim();
            if (placa.isEmpty() || !placa.matches("[A-Za-z0-9-]+")) {
                throw new IllegalArgumentException("Ingrese una placa válida.");
            }

            boolean esFeriado = vista.chkFeriado.isSelected();
            LocalTime horaEntrada = ((SpinnerDateModel) vista.spnHoraEntrada.getModel()).getDate()
                    .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            LocalTime horaSalida = ((SpinnerDateModel) vista.spnHoraSalida.getModel()).getDate()
                    .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();

            if (horaEntrada.isAfter(horaSalida)) {
                throw new IllegalArgumentException("La hora de entrada no puede ser posterior a la salida.");
            }

            Vehiculo vehiculo = new Vehiculo(placa, horaEntrada, horaSalida, esFeriado);
            double costo = vehiculo.calcularCosto();

            contadorVehiculos++;
            vista.lblResultado.setText("Costo: $" + String.format("%.2f", costo));
            vista.lblContadorVehiculos.setText("Vehículos ingresados: " + contadorVehiculos);

            registrarVehiculo(vehiculo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reiniciarCampos() {
        vista.txtPlaca.setText("");
        vista.chkFeriado.setSelected(false);
        vista.spnHoraEntrada.setValue(new java.util.Date());
        vista.spnHoraSalida.setValue(new java.util.Date());
        vista.lblResultado.setText("");
    }

    private void registrarVehiculo(Vehiculo vehiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt", true))) {
            writer.write(vehiculo.getPlaca() + "|"
                    + vehiculo.getHoraEntrada() + "|"
                    + vehiculo.getHoraSalida() + "|"
                    + vehiculo.calcularCosto() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al registrar el vehículo.");
        }
    }

    private void verRegistros() {

        VistaRegistros ventanaRegistros = new VistaRegistros();

        String[][] registros = cargarRegistros();
        ventanaRegistros.cargarRegistros(registros);

        ventanaRegistros.setVisible(true);
    }

    private String[][] cargarRegistros() {

        String[][] registros = new String[100][4];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("vehiculos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null && index < 100) {
                String[] datos = linea.split("\\|");
                if (datos.length == 4) {
                    registros[index++] = datos;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar los registros.");
        }

        String[][] registrosFiltrados = new String[index][4];
        System.arraycopy(registros, 0, registrosFiltrados, 0, index);

        return registrosFiltrados;
    }
}
