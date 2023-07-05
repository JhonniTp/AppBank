import Entidades.Cliente;
import Util.ClienteUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Cliente clienteActual;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de gestión de cuentas bancarias.");

        // Crear usuario predeterminado
        Cliente clientePredeterminado = new Cliente("Yoni Nando", "123456789", 1000.0, 500.0, 500.0, "12345");
        ClienteUtil.agregarCliente(clientePredeterminado);
        clienteActual = clientePredeterminado; // Asignar el usuario predeterminado a clienteActual

        // Crear nuevo cliente
        Cliente katiuska = new Cliente("Katiusca", "987654321", 2000.0, 1000.0, 1000.0, "54321");
        ClienteUtil.agregarCliente(katiuska);

        boolean salir = false;

        while (!salir) {
            System.out.println("\nBienvenido a AppBanco");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        registrarUsuario(scanner);
                        break;
                    case 2:
                        iniciarSesion(scanner);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Gracias por usar AppBanco. ¡Hasta luego!");
    }

    private static void registrarUsuario(Scanner scanner) {
        System.out.println("\nRegistro de usuario:");

        String nombre = ingresarInformación("Ingrese su nombre: ", scanner);
        String cedula = ingresarInformación("Ingrese su cédula: ", scanner);

        // Verificar si la cédula ya está en uso
        if (ClienteUtil.buscarCliente(cedula) != null) {
            System.out.println("La cédula ingresada ya está en uso. Por favor, intente con una cédula diferente.");
            return;
        }

        double saldo = ingresarNumero("Ingrese su saldo inicial: ", scanner);
        double saldoAhorros = ingresarNumero("Ingrese su saldo de ahorros: ", scanner);
        double saldoCorriente = ingresarNumero("Ingrese su saldo corriente: ", scanner);

        String contrasena = ingresarInformación("Ingrese su contraseña: ", scanner);

        clienteActual = new Cliente(nombre, cedula, saldo, saldoAhorros, saldoCorriente, contrasena);
        ClienteUtil.agregarCliente(clienteActual);

        System.out.println("\n¡Registro exitoso! Ahora puede iniciar sesión.");
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.println("\nInicio de sesión:");

        System.out.print("Ingresetu cédula: ");
        String cedula = scanner.nextLine();

        System.out.print("Ingrese tu contraseña: ");
        String contrasena = scanner.nextLine();

        if (ClienteUtil.validarCredenciales(cedula, contrasena)) {
            Cliente clienteEncontrado = ClienteUtil.buscarCliente(cedula);

            if (clienteEncontrado != null) {
                clienteActual = clienteEncontrado;
                System.out.println("................................");
                System.out.println("\nInicio de sesión exitoso.");
                System.out.println("................................");
                boolean sesionActiva = true;
                while (sesionActiva) {
                    System.out.println("\nInformación personal:");
                    System.out.println(clienteActual.getInformacionPersonal());
                    System.out.println("\nHistorial de pagos de servicios:");
                    System.out.println(clienteActual.getHistorialPagos());

                    System.out.println("\nOpciones:");
                    System.out.println("1. Depósito de dinero");
                    System.out.println("2. Retiro de dinero");
                    System.out.println("3. Cerrar sesión");
                    System.out.print("Seleccione una opción: ");

                    int opcion;
                    try {
                        opcion = scanner.nextInt();
                        scanner.nextLine(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Error: entrada inválida. Por favor, ingrese un número.");
                        scanner.nextLine(); 
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            realizarDeposito(scanner);
                            break;
                        case 2:
                            realizarRetiro(scanner);
                            break;
                        case 3:
                            sesionActiva = false;
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }
                }
            } else {
                System.out.println("\nInicio de sesión fallido. La cédula ingresada no coincide con ningún usuario registrado.");
            }
        } else {
            System.out.println("\nInicio de sesión fallido. Las credenciales ingresadas son incorrectas.");
        }
    }

    private static void realizarDeposito(Scanner scanner) {
        if (clienteActual != null) {
            System.out.print("\nIngrese el monto a depositar: ");
            double monto = ingresarNumero(scanner);

            clienteActual.depositar(monto);

            System.out.println("\nDepósito realizado correctamente.");
            System.out.println("\nInformación actualizada:");
            System.out.println(clienteActual.getInformacionPersonal());
            System.out.println("\nHistorial de pagos de servicios actualizado:");
            System.out.println(clienteActual.getHistorialPagos());
        } else {
            System.out.println("\nDebe iniciar sesión para realizar un depósito.");
        }
    }

    private static void realizarRetiro(Scanner scanner) {
        if (clienteActual != null) {
            System.out.print("\nIngrese el monto a retirar: ");
            double monto = ingresarNumero(scanner);

            if (clienteActual.getSaldo() >= monto) {
                clienteActual.retirar(monto);
                System.out.println("\nRetiro realizado correctamente.");
                System.out.println("\nInformación actualizada:");
                System.out.println(clienteActual.getInformacionPersonal());
                System.out.println("\nHistorial de pagos de servicios actualizado:");
                System.out.println(clienteActual.getHistorialPagos());
            } else {
                System.out.println("\nNo tiene saldo suficiente para realizar el retiro.");
            }
        } else {
            System.out.println("\nDebe iniciar sesión para realizar un retiro.");
        }
    }

    private static String ingresarInformación(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static double ingresarNumero(String mensaje, Scanner scanner) {
        double numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);

            try {
                numero = Double.parseDouble(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: entrada inválida. Por favor, ingrese un número válido.");
            }
        }

        return numero;
    }

    private static double ingresarNumero(Scanner scanner) {
        double numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextDouble();
                scanner.nextLine(); 
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); 
            }
        }

        return numero;
    }
}

