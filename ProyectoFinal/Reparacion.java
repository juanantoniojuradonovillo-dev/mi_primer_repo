package ProyectoFinal;

import java.time.LocalDate;
// Clase que representa una reparación realizada a un vehículo.
// Almacena el tipo, la fecha, los kilómetros y el coste. Es inmutable — no tiene setters.

public class Reparacion {

    private int kilometros;
    private LocalDate fecha;
    private double coste;
    private TipoReparacion tipoReparacion;
    // Constructor que inicializa todos los datos de la reparación.

    public Reparacion(int kilometros, LocalDate fecha, double coste, TipoReparacion tipoReparacion) {
        this.kilometros = kilometros;
        this.fecha = fecha;
        this.coste = coste;
        this.tipoReparacion = tipoReparacion;
    }

    public int getKilometros() {
        return kilometros;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getCoste() {
        return coste;
    }

    public TipoReparacion getTipoReparacion() {
        return tipoReparacion;
    }
    // Devuelve una representación textual de la reparación para mostrar en la interfaz.

    @Override
    public String toString() {
        return this.tipoReparacion + " - " + this.fecha + " - " + this.kilometros + " km - " + this.coste + "€";
    }
}
