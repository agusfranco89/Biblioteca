package BibliotecaAdmin.Cobranzas;

import BibliotecaAdmin.Cliente;
import BibliotecaAdmin.Venta;

public class TarjetaCredito extends Tarjeta {
    private int cuota;

    public TarjetaCredito(Venta ventas, String numTarjeta, String fechaVencimiento, String codSeguridad, Cliente clienteNombre) {
        super(ventas, numTarjeta, fechaVencimiento, codSeguridad, clienteNombre);
    }

    public TarjetaCredito(Venta ventas, String numTarjeta, String fechaVencimiento, String codSeguridad, Cliente clienteNombre, int cuota) {
        super(ventas, numTarjeta, fechaVencimiento, codSeguridad, clienteNombre);
        this.cuota = cuota;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    //CALCULAR PRECIO EN CASO DE CUOTAS
    public double agregarRecargo (int cuota,Venta venta){
        double recargo = 0.15;
        return venta.getMonto()*(recargo * cuota);
    }

    public void pagarConCredito (Venta venta){
        double mostoXcuota = agregarRecargo(this.cuota,venta) / this.cuota;
        System.out.println("Usted debe abonar "+cuota+" cuotas de $"+mostoXcuota);
    }



    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "cuota=" + cuota +
                "} " + super.toString();
    }
}
