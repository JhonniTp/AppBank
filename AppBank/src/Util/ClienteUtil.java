package Util;

import Entidades.Cliente;
import java.util.HashMap;
import java.util.Map;

public class ClienteUtil {
    private static Map<String, Cliente> clientes = new HashMap<>();

    public static void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getCedula(), cliente);
    }

    public static Cliente buscarCliente(String cedula) {
        return clientes.get(cedula);
    }

    public static void imprimirInformacionPersonal(Cliente cliente) {
        System.out.println("\nInformación personal:");
        System.out.println(cliente.getInformacionPersonal());
    }

    public static void imprimirHistorialPagos(Cliente cliente) {
        System.out.println("\nHistorial de pagos de servicios:");
        System.out.println(cliente.getHistorialPagos());
    }

    public static Cliente buscarClientePorNumeroCuenta(String numeroCuenta) {
        for (Cliente cliente : clientes.values()) {
            String cuenta = cliente.getNumeroCuenta();
            if (cuenta != null && cuenta.equals(numeroCuenta)) {
                return cliente;
            }
        }
        return null;
    }

    public static Cliente buscarClientePorCedula(String cedula) {
    for (Cliente cliente : clientes.values()) {
        if (cliente.getCedula().equals(cedula)) {
            return cliente;
        }
    }
    return null;
}


    public static boolean validarCredenciales(String cedula, String contrasena) {
        Cliente cliente = buscarCliente(cedula);
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            return true;
        }
        return false;
    }
}



