package Entidades;


public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public void retirar(double monto) {
        if (getSaldo() >= monto) {
            depositar(-monto);
        } else {
            System.out.println("No tiene suficiente saldo en la cuenta corriente.");
        }
    }
}

