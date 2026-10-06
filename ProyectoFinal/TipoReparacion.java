package ProyectoFinal;

//Enum para tipificar diferentes reparaciones
public enum TipoReparacion {
    CAMBIO_ACEITE(10000), 
    CAMBIO_FILTRO_AIRE(20000), 
    PASTILLAS_FRENOS(40000), 
    OTROS(15000);

    private final int limiteKm;

    TipoReparacion(int limiteKm) {
        this.limiteKm = limiteKm;
    }

    public int getLimiteKm() {
        return limiteKm;
    }
}


