package BibliotecaAdmin.Cobranzas;

import BibliotecaAdmin.Venta;

public class Cobranza {
    private Venta Ventas;

    public Cobranza(Venta ventas) {
        Ventas = ventas;
    }

    public Venta getVentas() {
        return Ventas;
    }

    public void setVentas(Venta ventas) {
        Ventas = ventas;
    }
}
