/** Nodo de la lista ligada; guarda una venta y apunta al siguiente nodo. */
public class Nodo {
    private Venta venta;
    private Nodo siguiente;

    public Nodo(Venta venta) {
        this.venta = venta;
    }

    public Venta getVenta() {
        return venta;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}