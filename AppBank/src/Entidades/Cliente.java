package Entidades;

public class Cliente {
    private String nombre;
    private String cedula;
    private double saldo;
    private double saldoAhorros;
    private double saldoCorriente;
    private String historialPagos;
    private String contrasena;

    public Cliente(String nombre, String cedula, double saldo, double saldoAhorros, double saldoCorriente, String contrasena) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldo = saldo;
        this.saldoAhorros = saldoAhorros;
        this.saldoCorriente = saldoCorriente;
        this.historialPagos = "";
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getInformacionPersonal() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Cédula: ").append(cedula).append("\n");
        sb.append("Saldo: S/").append(saldo).append("\n");
        sb.append("Saldo de ahorros: S/").append(saldoAhorros).append("\n");
        sb.append("Saldo corriente: S/").append(saldoCorriente);
        return sb.toString();
    }

    public double getSaldo() {
        return saldo;
    }

    public String getHistorialPagos() {
        return historialPagos;
    }

    public void depositar(double monto) {
        saldo += monto;
        historialPagos += "- Comprobante: Depósito de dinero por S/" + monto + "\n";
    }

    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            historialPagos += "- Comprobante: Retiro de dinero por S/" + monto + "\n";
        } else {
            System.out.println("No tiene suficiente saldo para realizar el retiro.");
        }
    }


    public boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public String getNumeroCuenta() {
        return cedula; // Puedes ajustar esto según tu implementación real
    }

    public void depositarEnCorriente(double monto) {
    }
}


