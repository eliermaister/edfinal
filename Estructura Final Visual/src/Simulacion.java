import java.util.LinkedList;
import java.util.Queue;

public class Simulacion {
    private int numClientes;
    private int numClientesCheques;
    private int numClientesJuridicos;
    private int numPuestosNormales;
    private int numPuestosCheques;
    private int numPuestosJuridicos;
    private double frecuenciaPagos;
    private double probabilidadExtracciones;
    private double probabilidadCheques;
    private double probabilidadJuridicos;

    public Simulacion(int numClientes, int numClientesCheques, int numClientesJuridicos, int numPuestosNormales, int numPuestosCheques, int numPuestosJuridicos, double frecuenciaPagos, double probabilidadExtracciones, double probabilidadCheques, double probabilidadJuridicos) {
        this.numClientes = numClientes;
        this.numClientesCheques = numClientesCheques;
        this.numClientesJuridicos = numClientesJuridicos;
        this.numPuestosNormales = numPuestosNormales;
        this.numPuestosCheques = numPuestosCheques;
        this.numPuestosJuridicos = numPuestosJuridicos;
        this.frecuenciaPagos = frecuenciaPagos;
        this.probabilidadExtracciones = probabilidadExtracciones;
        this.probabilidadCheques = probabilidadCheques;
        this.probabilidadJuridicos = probabilidadJuridicos;
    }

    public void simular() {
        // Crear los puestos de atención
        LinkedList<PuestoAtencion> puestosAtencion = new LinkedList<>();
        for (int i = 0; i < numPuestosNormales; i++) {
            puestosAtencion.add(new PuestoAtencion(i, 3, TipoPuesto.NORMAL));
        }
        for (int i = 0; i < numPuestosCheques; i++) {
            puestosAtencion.add(new PuestoAtencion(i + numPuestosNormales, 5, TipoPuesto.ESPECIALIZADO));
        }
        for (int i = 0; i < numPuestosJuridicos; i++) {
            puestosAtencion.add(new PuestoAtencion(i + numPuestosNormales + numPuestosCheques, 10, TipoPuesto.BANCA_PERSONAL));
        }

        // Generar la cola de clientes
        Queue<Cliente> colaClientes = new LinkedList<>();
        for (int i = 0; i < numClientes; i++) {
            double tipoTramite = Math.random();
            if (tipoTramite < frecuenciaPagos) {
                colaClientes.add(new Cliente(TipoTramite.PAGO));
            } else if(tipoTramite < frecuenciaPagos + probabilidadExtracciones) {
                colaClientes.add(new Cliente(TipoTramite.EXTRACCION));
            } else if (tipoTramite < frecuenciaPagos + probabilidadExtracciones + probabilidadCheques) {
                colaClientes.add(new Cliente(TipoTramite.COBRO_CHEQUE));
            } else {
                colaClientes.add(new Cliente(TipoTramite.JURIDICO));
            }
        }

        // Asignar los clientes a los puestos de atención
        for (Cliente cliente : colaClientes) {
            PuestoAtencion puestoAtencion = null;
            int minClientes = Integer.MAX_VALUE;

            // Buscar el puesto de atención con menos clientes en la cola
            for (PuestoAtencion puesto : puestosAtencion) {
                if (puesto.puedeAtender(cliente.getTipoTramite()) && puesto.getCola().size() < minClientes) {
                    puestoAtencion = puesto;
                    minClientes = puesto.getCola().size();
                }
            }

            // Añadir el cliente a la cola correspondiente
            puestoAtencion.getCola().add(cliente);
        }

        // Simular la atención de los clientes
        while (!colaClientes.isEmpty()) {
            for (PuestoAtencion puesto : puestosAtencion) {
                if (!puesto.getCola().isEmpty()){
                    Cliente cliente = puesto.getCola().peek();
                    int tiempoAtencion = (int) (Math.random() * puesto.getTiempoPromedio() * 2) + puesto.getTiempoPromedio();

                    // Simular el tiempo de atención
                    try {
                        Thread.sleep(tiempoAtencion * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Atender al cliente y actualizar la información del puesto de atención
                    puesto.getCola().remove();
                    puesto.addClienteAtendido();
                    puesto.addTiempoAtencion(tiempoAtencion);
                    colaClientes.remove(cliente);
                }
            }
        }

        // Registrar la información de los puestos de atención
        for (PuestoAtencion puesto : puestosAtencion) {
            int clientesAtendidos = puesto.getClientesAtendidos();
            int tiempoTotalAtencion = puesto.getTiempoTotalAtencion();
            int tiempoPromedioAtencion = clientesAtendidos > 0 ? tiempoTotalAtencion / clientesAtendidos : 0;
            System.out.println("Puesto " + puesto.getId() + ": " + clientesAtendidos + " clientes atendidos, tiempo promedio de atención: " + tiempoPromedioAtencion + " segundos");
        }
    }
}