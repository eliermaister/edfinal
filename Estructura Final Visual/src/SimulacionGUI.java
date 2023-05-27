import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SimulacionGUI {

    private JFrame frame;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JLabel labelClientesAtendidosValor;
    private JLabel labelTiempoPromedioEsperaValor;
    private JLabel labelTiempoTotalSimulacionValor;

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_TRAMITE = 1;
    private static final int COLUMN_TIEMPO_COLA = 2;

    public SimulacionGUI() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Simulación de banco");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Crear tabla de clientes
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Trámite");
            modeloTabla.addColumn("Tiempo en cola");
            tablaClientes = new JTable(modeloTabla);

            JScrollPane scrollPane = new JScrollPane(tablaClientes);

            // Crear panel de estadísticas
            JPanel panelEstadisticas = new JPanel();

            panelEstadisticas.setLayout(new GridLayout(3, 2, 5, 5));

            JLabel labelClientesAtendidos = new JLabel("Clientes atendidos:");
            JLabel labelTiempoPromedioEspera = new JLabel("Tiempo promedio de espera:");
            JLabel labelTiempoTotalSimulacion = new JLabel("Tiempo total de simulación:");

            labelClientesAtendidosValor = new JLabel("0");
            labelTiempoPromedioEsperaValor = new JLabel("0");
            labelTiempoTotalSimulacionValor = new JLabel("0");

            panelEstadisticas.add(labelClientesAtendidos);
            panelEstadisticas.add(labelClientesAtendidosValor);
            panelEstadisticas.add(labelTiempoPromedioEspera);
            panelEstadisticas.add(labelTiempoPromedioEsperaValor);
            panelEstadisticas.add(labelTiempoTotalSimulacion);
            panelEstadisticas.add(labelTiempoTotalSimulacionValor);

            // Crear botones
            JButton botonIniciar = new JButton("Iniciar");
            JButton botonDetener = new JButton("Detener");

            // Crear panel de botones
            JPanel panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());
            panelBotones.add(botonIniciar);
            panelBotones.add(botonDetener);

            // Agregar componentes al frame
            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(panelEstadisticas, BorderLayout.SOUTH);
            frame.add(panelBotones, BorderLayout.NORTH);

            // Mostrar el frame
            frame.setVisible(true);
        });
    }

    // Método para actualizar la tabla de clientes
    public void actualizarTablaClientes(Object[] fila) {
        SwingUtilities.invokeLater(() -> modeloTabla.addRow(fila));
    }

    // Método para actualizar las estadísticas
    public void actualizarEstadisticas(int clientesAtendidos, double tiempoPromedioEspera, double tiempoTotalSimulacion) {
        SwingUtilities.invokeLater(() -> {
            labelClientesAtendidosValor.setText(Integer.toString(clientesAtendidos));
            labelTiempoPromedioEsperaValor.setText(Double.toString(tiempoPromedioEspera));
            labelTiempoTotalSimulacionValor.setText(Double.toString(tiempoTotalSimulacion));
        });
    }
}