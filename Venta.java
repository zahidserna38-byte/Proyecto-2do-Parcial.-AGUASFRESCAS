import java.util.Locale;


public class Venta {
    private final int folio;
    private final String sabor;
    private final int cantidad;
    private final double precioUnitario;

    public Venta(int folio, String sabor, int cantidad, double precioUnitario) {
        this.folio = folio;
        this.sabor = sabor;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getFolio() {
        return folio;
    }

    public String getSabor() {
        return sabor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Folio: %d | Sabor: %s | Cantidad: %d | Precio unitario: $%.2f | Total: $%.2f",
                folio, sabor, cantidad, precioUnitario, getTotal());
    }
}