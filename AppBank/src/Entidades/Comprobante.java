package Entidades;

public class Comprobante {
    private String descripcion;
    private double monto;

    public Comprobante(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "- Comprobante: " + descripcion + " por S/" + monto;
    }
}

