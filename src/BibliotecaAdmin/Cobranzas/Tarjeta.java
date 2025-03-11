package BibliotecaAdmin.Cobranzas;

import BibliotecaAdmin.Cliente;
import BibliotecaAdmin.Venta;

public class Tarjeta extends Cobranza{
        private String numTarjeta;
        private String fechaVencimiento;
        private String codSeguridad;
        private Cliente clienteNombre;


    public Tarjeta(Venta ventas, String numTarjeta, String fechaVencimiento, String codSeguridad, Cliente clienteNombre) {
        super(ventas);
        this.numTarjeta = numTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.codSeguridad = codSeguridad;
        this.clienteNombre = clienteNombre;
    }

    public String getNumTarjeta() {
            return numTarjeta;
        }

        public void setNumTarjeta(String numTarjeta) {
            this.numTarjeta = numTarjeta;
        }

        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public String getCodSeguridad() {
            return codSeguridad;
        }

        public void setCodSeguridad(String codSeguridad) {
            this.codSeguridad = codSeguridad;
        }

        public Cliente getClienteNombre() {
            return clienteNombre;
        }

        public void setClienteNombre(Cliente clienteNombre) {
            this.clienteNombre = clienteNombre;
        }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "numTarjeta='" + numTarjeta + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", codSeguridad='" + codSeguridad + '\'' +
                ", clienteNombre=" + clienteNombre +
                '}';
    }
}
