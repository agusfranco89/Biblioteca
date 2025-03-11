package BibliotecaAdmin.Cobranzas;

import BibliotecaAdmin.Cliente;
import BibliotecaAdmin.Venta;

public class TarjetaDebito extends Tarjeta {


    public TarjetaDebito(Venta ventas, String numTarjeta, String fechaVencimiento, String codSeguridad, Cliente clienteNombre) {
        super(ventas, numTarjeta, fechaVencimiento, codSeguridad, clienteNombre);
    }

    public void pagarConDebito(Venta v){
        double pago = v.getMonto();
        System.out.println("Se ha pagado con Debito el monto de $" +pago);
    }
}
