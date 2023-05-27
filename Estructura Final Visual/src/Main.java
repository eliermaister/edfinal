import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion(100, 20, 10, 5, 2, 1, 0.3, 0.4, 0.2, 0.1);
        simulacion.simular();
    }
}