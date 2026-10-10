
public class ListaVentas {
    private Nodo cabeza;
    private int siguienteFolio = 1;
    private int cantidadVentas;


    public int agregar(String sabor, int cantidad, double precioUnitario) {
        Venta nuevaVenta = new Venta(siguienteFolio, sabor, cantidad, precioUnitario);
        Nodo nuevo = new Nodo(nuevaVenta);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        cantidadVentas++;
        return siguienteFolio++;
    }


    public boolean eliminarPorFolio(int folio) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getVenta().getFolio() == folio) {
            cabeza = cabeza.getSiguiente();
            cantidadVentas--;
            return true;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.getSiguiente();
        while (actual != null) {
            if (actual.getVenta().getFolio() == folio) {
                anterior.setSiguiente(actual.getSiguiente());
                cantidadVentas--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }


    public void mostrar() {
        if (cabeza == null) {
            System.out.println("Todavía no hay ventas registradas.");
            return;
        }

        System.out.println("\n--- Ventas registradas ---");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getVenta());
            actual = actual.getSiguiente();
        }
    }


    public double calcularIngresosTotales() {
        double ingresos = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            ingresos += actual.getVenta().getTotal();
            actual = actual.getSiguiente();
        }
        return ingresos;
    }


    public int calcularVasosVendidos() {
        int vasos = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            vasos += actual.getVenta().getCantidad();
            actual = actual.getSiguiente();
        }
        return vasos;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
}