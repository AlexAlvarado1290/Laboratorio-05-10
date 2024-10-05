import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int HOURS = 24;
    private static final int INTERVAL = 3;
    private static final String DATA_FILE = "data.dat";
    private static final char ASTERISC = '*';
    private static final char SPACE = ' ';

    public static void main(String[] args) {
//        crearArchivoSiNoExiste();
//        llenarArchivoConDatosDePrueba();
        int[] temperatures = readTemperatures();
        if (temperatures != null) {
            displayTemperatures(temperatures);
        }
    }

    private static void crearArchivoSiNoExiste() {
        File archivo = new File("data.dat");
        if (!archivo.exists()) {
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write("");
                System.out.println("Archivo creado");
            } catch (IOException e) {
                System.out.println("Error.");
            }
        }
    }

    private static void llenarArchivoConDatosDePrueba() {
        File archivo = new File("data.dat");
        try (FileWriter writer = new FileWriter(archivo)) {
            int[] datosPrueba = {-20, 0, 1, 2, 3, 4, 5, 10, 50, 100, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85};
            for (int temperatura : datosPrueba) {
                writer.write(temperatura + " ");
            }
        } catch (IOException e) {
            System.out.println("Error: No se lleno");
        }
    }

    private static int[] readTemperatures() {
        int[] temperatures = new int[HOURS];
        try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
            for (int i = 0; i < HOURS; i++) {
                if (scanner.hasNextInt()) {
                    temperatures[i] = scanner.nextInt();
                } else {
                    System.out.println("No hay suficientes");
                    return null;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No esta el archivo" + DATA_FILE);
            return null;
        }
        return temperatures;
    }

    private static void displayTemperatures(int[] temperatures) {
        System.out.println("Temperaturas para 24 horas:");
        System.out.println("  -30         0         30         60         90        120");

        for (int temperature : temperatures) {
            int numAsterisks = temperature / INTERVAL;
            System.out.print(padLeft(Integer.toString(temperature), 4, SPACE) + " |");

            for (int i = 0; i < numAsterisks; i++) {
                System.out.print(ASTERISC);
            }
            System.out.println();
        }
    }

    private static String padLeft(String s, int width, char paddingChar) {
        if (s == null) {
            return null;
        }
        int length = s.length();
        if (length >= width) {
            return s;
        }
        StringBuilder sb = new StringBuilder(width);
        for (int i = 0; i < width - length; i++) {
            sb.append(paddingChar);
        }
        sb.append(s);
        return sb.toString();
    }
}