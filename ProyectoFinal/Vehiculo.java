package ProyectoFinal;

import java.util.ArrayList;

//Clase que define los vehiculos y que implementa la interfaz de Aviso
public class Vehiculo implements AvisoMantenimiento {

    private String marca;
    private String modelo;
    private int kilometros;
    private TipoVehiculo tipo;
    private String matricula;
    private ArrayList<Reparacion> reparaciones;

    //Constructor que recibe los parametros necesarios
    public Vehiculo(int kilometros, String marca, TipoVehiculo tipo, String matricula, String modelo) {
        this.kilometros = kilometros;
        this.marca = marca;
        this.tipo = tipo;
        this.matricula = matricula.toUpperCase();
        this.modelo = modelo;
        this.reparaciones = new ArrayList<>();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula.toUpperCase();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public ArrayList<Reparacion> getReparaciones() {
        return reparaciones;
    }

    @Override
    public void mostrarAviso() {
        if (necesitaRevision()) {
            System.out.println("El vehículo necesita mantenimiento.");
        } else {
            System.out.println("El vehículo no necesita mantenimiento todavía.");
        }
    }

    //Para saber que tipo de revision necesita cada vehiculo segun el tipo
    @Override
    public boolean necesitaRevision() {

        int limite;
        switch (tipo) {
            case COCHE:
                limite = 15000;
                break;
            case MOTO:
                limite = 6000;
                break;
            case FURGONETA:
                limite = 20000;
                break;
            case CAMION:
                limite = 30000;
                break;
            default:
                limite = 15000;
        }
        
        if (reparaciones.isEmpty()) {
            return this.kilometros >= limite; // limite del switch
        } else {
            Reparacion ultima = reparaciones.get(reparaciones.size() - 1);
            int limiteReparacion = ultima.getTipoReparacion().getLimiteKm();
            return this.kilometros >= ultima.getKilometros() + limiteReparacion;
        }
    }

    //metodo que agrega las reparaciones necesarias
    public void agregarReparacion(Reparacion r) {
        this.reparaciones.add(r);
    }

    // Devuelve una representación textual del vehículo incluyendo el aviso de revisión si es necesario.
    @Override
    public String toString() {
        String aviso = necesitaRevision() ? " NECESITA REVISIÓN" : "";
        return marca + " " + modelo + " - " + matricula + " (" + tipo + ") - " + kilometros + " km" + aviso;
    }

}
