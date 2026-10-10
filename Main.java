import java.util.Scanner;

/** Punto de entrada y menú de consola del registro de ventas. */
public class Main {
    private static final String[] SABORES = {
            "Fruta", "Horchata", "Limón", "Maracuyá", "Piña colada"
    };

    public static void main(String[] args) {
        ListaVentas ventas = new ListaVentas();

        try (Scanner teclado = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                System.out.print("Selecciona una opción: ");
                String opcion = teclado.nextLine().trim();

                switch (opcion) {
                    case "1":
                        registrarVenta(teclado, ventas);
                        break;
                    case "2":
                        eliminarVenta(teclado, ventas);
                        break;
                    case "3":
                        ventas.mostrar();
                        break;
                    case "4":
                        mostrarResumen(ventas);
                        break;
                    case "0":
                        continuar = false;
                        System.out.println("Programa terminado.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Aguas frescas La Michoacana ===");
        System.out.println("1. Registrar venta");
        System.out.println("2. Eliminar venta por folio");
        System.out.println("3. Mostrar ventas");
        System.out.println("4. Ver resumen de ventas e ingresos (operación extra)");
        System.out.println("0. Salir");
    }

    private static void registrarVenta(Scanner teclado, ListaVentas ventas) {
        System.out.println("\n--- Registrar venta ---");
        String sabor = seleccionarSabor(teclado);
        int cantidad = leerEnteroPositivo(teclado, "Cantidad de vasos: ");
        double precio = leerPrecioPositivo(teclado, "Precio por vaso: $");

        int folio = ventas.agregar(sabor, cantidad, precio);
        System.out.println("Venta registrada. Folio: " + folio
                + " | Total: $" + String.format(java.util.Locale.US, "%.2f", cantidad * precio));
    }

    private static String seleccionarSabor(Scanner teclado) {
        while (true) {
            System.out.println("Sabores disponibles:");
            for (int i = 0; i < SABORES.length; i++) {
                System.out.println((i + 1) + ". " + SABORES[i]);
            }
            System.out.print("Elige un sabor: ");
            String entrada = teclado.nextLine().trim();
            try {
                int opcion = Integer.parseInt(entrada);
                if (opcion >= 1 && opcion <= SABORES.length) {
                    return SABORES[opcion - 1];
                }
            } catch (NumberFormatException ignored) {
                // Se vuelve a mostrar el menú de sabores.
            }
            System.out.println("Elige un número del 1 al " + SABORES.length + ".");
        }
    }

    private static void eliminarVenta(Scanner teclado, ListaVentas ventas) {
        System.out.println("\n--- Eliminar venta ---");
        int folio = leerEnteroPositivo(teclado, "Folio de la venta que deseas eliminar: ");
        if (ventas.eliminarPorFolio(folio)) {
            System.out.println("Venta eliminada correctamente.");
        } else {
            System.out.println("No se encontró una venta con ese folio.");
        }
    }

    private static void mostrarResumen(ListaVentas ventas) {
        System.out.println("\n--- Resumen ---");
        System.out.println("Ventas registradas: " + ventas.getCantidadVentas());
        System.out.println("Vasos vendidos: " + ventas.calcularVasosVendidos());
        System.out.println("Ingresos totales: $"
                + String.format(java.util.Locale.US, "%.2f", ventas.calcularIngresosTotales()));
    }

    private static int leerEnteroPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim();
            try {
                int numero = Integer.parseInt(entrada);
                if (numero > 0) {
                    return numero;
                }
            } catch (NumberFormatException ignored) {
                // Se muestra el mensaje de validación debajo.
            }
            System.out.println("Escribe un número entero mayor que cero.");
        }
    }

    private static double leerPrecioPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim().replace(',', '.');
            try {
                double precio = Double.parseDouble(entrada);
                if (precio > 0 && !Double.isInfinite(precio) && !Double.isNaN(precio)) {
                    return precio;
                }
            } catch (NumberFormatException ignored) {
                // Se muestra el mensaje de validación debajo.
            }
            System.out.println("Escribe un precio válido mayor que cero.");
        }
    }
}