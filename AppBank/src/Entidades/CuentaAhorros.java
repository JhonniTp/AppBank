package Entidades;

public class CuentaAhorros extends Cuenta {
    public CuentaAhorros(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    public void retirar(double monto) {
        if (getSaldo() >= monto) {
            double nuevoSaldo = getSaldo() - monto;
            if (nuevoSaldo >= 0) {
                setSaldo(nuevoSaldo);
            } else {
                System.out.println("No puede retirar más dinero del saldo actual de la cuenta de ahorros.");
            }
        } else {
            System.out.println("No tiene suficiente saldo en la cuenta de ahorros.");
        }
    }

    private void setSaldo(double nuevoSaldo) {
        saldo = nuevoSaldo;
    }
}

