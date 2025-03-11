package BibliotecaAdmin.Cobranzas;

import BibliotecaAdmin.Venta;

public class Efectivo extends Cobranza{

    public Efectivo(Venta ventas) {
        super(ventas);
    }

    public void pagarConEfectivo(Venta v){
        double pago = v.getMonto();
        System.out.println("Se ha pagado con efectivo el monto de $" +pago);
    }

}
