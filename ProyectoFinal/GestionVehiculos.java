package ProyectoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Clase que gestiona la informacion con listas y streams.
public class GestionVehiculos {

    private ArrayList<Vehiculo> vehiculos;

    //Crea un Array donde guarda los vehiculos.
    public GestionVehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    //agrega los vehiculos al array
    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    //elimina los vehiculos del array por matricula
    public void eliminarVehiculo(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v.getMatricula().equals(matricula)) {
                vehiculos.remove(v);
                break;
            }
        }
    }

    //busca vehiculos por matricula
    public Vehiculo buscarMatricula(String matricula) {

        return vehiculos.stream()
                .filter(v -> v.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }

    //ordena por km
    public List<Vehiculo> ordenarPorKM() {

        return vehiculos.stream()
                .sorted(Comparator.comparingInt(v -> v.getKilometros()))
                .collect(Collectors.toList());
    }

    //stream que filtra vehiculos si necesitan revision
    public long contarVehiculosConRevision() {
        return vehiculos.stream()
                .filter(v -> v.necesitaRevision())
                .count();
    }

    // Devuelve una lista con todas las matrículas de los vehículos
    public List<String> obtenerMatriculas() {
        return vehiculos.stream()
                .map(v -> v.getMatricula())
                .collect(Collectors.toList());
    }

    //muestra el array vehiculo
    public ArrayList<Vehiculo> mostrarTodos() {
        return vehiculos;
    }

    // Guarda todos los vehículos y sus reparaciones en un fichero .txt.
    // Las líneas de vehículos llevan el prefijo "V," y las reparaciones "R,"
    // para poder distinguirlos al cargar.
    public void guardarVehiculos(String nombrefichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombrefichero))) {
            for (Vehiculo v : vehiculos) {
                writer.write("V," + v.getMarca() + "," + v.getModelo() + "," + v.getMatricula() + "," + v.getTipo() + "," + v.getKilometros());
                writer.newLine();
                for (Reparacion r : v.getReparaciones()) {
                    writer.write("R," + r.getTipoReparacion() + "," + r.getFecha() + "," + r.getKilometros() + "," + r.getCoste());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Carga vehículos y reparaciones desde un fichero .txt.
    // Las líneas con "V" crean un Vehiculo y las líneas con "R" 
    // añaden una reparación al último vehículo cargado.
    public void cargarVehiculos(String nombrefichero) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombrefichero))) {
            String linea;
            Vehiculo ultimoVehiculo = null;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals("V")) {
                    ultimoVehiculo = new Vehiculo(Integer.parseInt(partes[5]), partes[1], TipoVehiculo.valueOf(partes[4]), partes[3], partes[2]);
                    vehiculos.add(ultimoVehiculo);
                } else if (partes[0].equals("R")) {
                    Reparacion r = new Reparacion(Integer.parseInt(partes[3]), LocalDate.parse(partes[2]), Double.parseDouble(partes[4]), TipoReparacion.valueOf(partes[1]));
                    ultimoVehiculo.agregarReparacion(r);
                }

            }
        } catch (IOException e) {
            System.out.println("Fichero no encontrado, se iniciará vacío.");
        }
    }

}
