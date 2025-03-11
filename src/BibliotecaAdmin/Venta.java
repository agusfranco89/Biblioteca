package BibliotecaAdmin;

import BibliotecaAdmin.Cobranzas.MetodosPago;
import BibliotecaAdmin.Ediciones.Edicion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Venta implements Serializable {
    private Date fecha;
    private String dniPersona;
    private ArrayList<Edicion> listEdiciones;
    private double monto = 0.0;
    private MetodosPago medioPago;//EFECTIVO, TARJETA_CREDITO, TARJETA_DEBITO

    public Venta() {
        this.fecha = null;
        this.dniPersona = null;
        this.listEdiciones = new ArrayList<>();
        this.monto = 0.0;
        this.medioPago = null;
    }


    public Venta(String dniPersona, ArrayList<Edicion> listEdiciones, MetodosPago medioPago) {

        this.fecha = new Date();
        this.dniPersona = dniPersona;
        this.listEdiciones = listEdiciones;
        for (Edicion e : listEdiciones) {

            this.monto=this.getMonto()+e.getPrecio();
        }
        this.medioPago = medioPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<Edicion> getListEdiciones() {
        return listEdiciones;
    }

    public void setListEdiciones(ArrayList<Edicion> listEdiciones) {
        this.listEdiciones = listEdiciones;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public MetodosPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MetodosPago medioPago) {

        this.medioPago = medioPago;
    }

    public String getDniPersona() {
        return dniPersona;
    }

    public void setDniPersona(String dniPersona) {
        this.dniPersona = dniPersona;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void addListEdiciones(Edicion edicionNueva){
        if(this.listEdiciones.equals(edicionNueva)){
            System.out.println("Edicion existente.");
        }else{
            this.listEdiciones.add(edicionNueva);
        }
    }



    @Override
    public String toString() {
        return "\nVenta: " + '\n' +
                "Fecha de Venta: " + getFecha() + '\n' +
                "DNI Comprador: " + getDniPersona() + '\n' +
                "Ediciones compradas: "  + getListEdiciones().toString() + '\n' +
                "Monto: " + getMonto() + '\n' +
                "Medio de Pago: " + getMedioPago() + '\n' +
                "----------------------------------------------------------------" + '\n' + '\n' ;
    }

    public String generaTicket()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("************ TICKET DE VENTA ************\n");
        sb.append("Fecha: ").append(sdf.format(fecha)).append("\n");
        sb.append("DNI Persona: ").append(dniPersona).append("\n");
        sb.append("=========================\n");

        sb.append("Detalles de Ediciones:\n");
        for (Edicion edicion : listEdiciones) {
            sb.append(edicion.getTitulo()).append(" - $").append(edicion.getPrecio()).append("\n");
        }

        sb.append("=========================\n");
        sb.append("Monto Total: $").append(String.format("%.2f", monto)).append("\n");
        sb.append("Método de Pago: ").append(medioPago).append("\n");
        sb.append("=========================\n");
        sb.append("Gracias por su compra!\n");
        sb.append("*****************************************\n");

        return sb.toString();
    }
}
