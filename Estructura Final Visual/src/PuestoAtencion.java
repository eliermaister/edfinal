import java.util.LinkedList;
import java.util.Queue;

public class PuestoAtencion {
    private int id;
    private Queue<Cliente> cola;
    private int tiempoPromedio;
    private int clientesAtendidos;
    private int tiempoTotalAtencion;
    private TipoPuesto tipo;

    public PuestoAtencion(int id, int tiempoPromedio, TipoPuesto tipo) {
        this.id = id;
        this.cola = new LinkedList<>();
        this.tiempoPromedio = tiempoPromedio;
        this.clientesAtendidos = 0;
        this.tiempoTotalAtencion = 0;
        this.tipo = tipo;
    }

    public boolean puedeAtender(TipoTramite tipoTramite) {
        // Devuelve true si el puesto de atención puede atender el tipo de trámite especificado
        if (tipoTramite == TipoTramite.PAGO || tipoTramite == TipoTramite.EXTRACCION){
            return true; // El puesto de atención puede atender pagos y extracciones
        } else if (tipoTramite == TipoTramite.COBRO_CHEQUE && this.tipo == TipoPuesto.ESPECIALIZADO) {
            return true; // El puesto de atención especializado puede atenderSigo con la clase `PuestoAtencion` corregida:
          //  el cobro de cheques
        } else if (tipoTramite == TipoTramite.CAMBIO_CUENTA && this.tipo == TipoPuesto.BANCA_PERSONAL) {
            return true; // La banca personal puede atender cambios de cuenta
        }
        else {
            return false; // El puesto de atención no puede atender el tipo de trámite especificado
        }
    }

    public void addClienteAtendido() {
        this.clientesAtendidos++;
    }

    public void addTiempoAtencion(int tiempoAtencion) {
        this.tiempoTotalAtencion += tiempoAtencion;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Queue<Cliente> getCola() {
        return cola;
    }

    public void setCola(Queue<Cliente> cola) {
        this.cola = cola;
    }

    public int getTiempoPromedio() {
        return tiempoPromedio;
    }

    public void setTiempoPromedio(int tiempoPromedio) {
        this.tiempoPromedio = tiempoPromedio;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(int clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    public int getTiempoTotalAtencion() {
        return tiempoTotalAtencion;
    }

    public void setTiempoTotalAtencion(int tiempoTotalAtencion) {
        this.tiempoTotalAtencion = tiempoTotalAtencion;
    }

    public TipoPuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuesto tipo) {
        this.tipo = tipo;
    }
}