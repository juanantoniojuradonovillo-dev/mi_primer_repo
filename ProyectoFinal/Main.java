package ProyectoFinal;
public class Main {

    public static void main(String[] args) {
        // Crea el gestor y carga los vehículos guardados anteriormente
        GestionVehiculos gestion = new GestionVehiculos();
        
        gestion.cargarVehiculos("vehiculos.txt");
        // Abre la ventana principal
        VentanaPpal ventana = new VentanaPpal(gestion);
    }
}
