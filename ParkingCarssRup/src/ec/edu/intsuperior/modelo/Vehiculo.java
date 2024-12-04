package ec.edu.intsuperior.modelo;

import java.time.Duration;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Vehiculo {

    private String placa;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private boolean esFeriado;

    // Constructor
    public Vehiculo(String placa, LocalTime horaEntrada, LocalTime horaSalida, boolean esFeriado) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.esFeriado = esFeriado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isEsFeriado() {
        return esFeriado;
    }

    public void setEsFeriado(boolean esFeriado) {
        this.esFeriado = esFeriado;
    }

    public double calcularCosto() {
        long minutosTotales = Duration.between(horaEntrada, horaSalida).toMinutes();
        if (minutosTotales <= 0) {
            throw new IllegalArgumentException("La hora de salida debe ser posterior a la hora de entrada.");
        }

        DayOfWeek diaSemana = java.time.LocalDate.now().getDayOfWeek();
        double tarifaPorHora;

        if (esFeriado || diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            tarifaPorHora = 1.00;
        } else {
            tarifaPorHora = 0.50;
        }

        if (minutosTotales < 60) {
            return tarifaPorHora;
        }

        double costo = Math.ceil(minutosTotales / 60.0) * tarifaPorHora;
        return costo;
    }

    @Override
    public String toString() {
        return String.format("Placa: %s | Entrada: %s | Salida: %s | Feriado: %s",
                placa, horaEntrada, horaSalida, esFeriado ? "Sí" : "No");
    }
}
