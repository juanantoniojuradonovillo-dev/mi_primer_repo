package ProyectoFinal;


// Interfaz que define el comportamiento de aviso de mantenimiento para los vehículos.
// Es implementada por la clase Vehiculo.
public interface AvisoMantenimiento {

    // Devuelve true si el vehículo necesita revisión según los km recorridos desde la última reparación.
    boolean necesitaRevision();
    // Muestra un aviso por pantalla indicando si el vehículo necesita revisión o no.

    void mostrarAviso();
}
