import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Path ruta = encontrarDatosTxt();
        if (ruta == null) {
            System.out.println("❌ No se encontró 'datos.txt' o no se puede leer.");
            System.out.println("📌 Directorio de ejecución (user.dir): " + System.getProperty("user.dir"));
            System.out.println("👉 Asegúrate de que el archivo se llame EXACTO: datos.txt");
            sc.close();
            return;
        }

        System.out.println("✅ Leyendo: " + ruta);

        System.out.println("\nElige tipo de pila:");
        System.out.println("1) Pila con Vector/ArrayList");
        System.out.println("2) Pila basada en Lista (hecha desde 0)");
        int grupo = pedirOpcion(sc, 1, 2);

        int opcion;
        if (grupo == 1) {
            System.out.println("\nElige implementación:");
            System.out.println("1) StackVector");
            System.out.println("2) StackArrayList");
            opcion = pedirOpcion(sc, 1, 2);
        } else {
            System.out.println("\nElige tipo de Lista:");
            System.out.println("1) SinglyLinkedList");
            System.out.println("2) DoublyLinkedList");
            opcion = pedirOpcion(sc, 1, 2);
        }

        CalculadoraInfix calc = new CalculadoraInfix();

        try {
            List<String> lineas = Files.readAllLines(ruta);

            for (String expr : lineas) {
                if (expr.trim().isEmpty()) continue;

                IStack<String> pilaOps = StackFactory.crearStack(grupo, opcion);
                IStack<Double> pilaVals = StackFactory.crearStack(grupo, opcion);

                try {
                    double res = calc.evaluar(expr, pilaOps, pilaVals);
                    System.out.println(expr + " = " + res);
                } catch (InfixException e) {
                    System.out.println(expr + " = ERROR -> " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }

        sc.close();
    }

    private static boolean archivoValido(Path p) {
        try {
            return Files.exists(p) && Files.isRegularFile(p) && Files.isReadable(p);
        } catch (Exception e) {
            return false;
        }
    }

    private static Path encontrarDatosTxt() {
        String nombre = "datos.txt";

        // 1) Relativo al directorio de ejecución
        try {
            Path p1 = Paths.get(nombre).toAbsolutePath().normalize();
            if (archivoValido(p1)) return p1;
        } catch (Exception ignored) { }

        // 2) user.dir explícito
        try {
            Path p2 = Paths.get(System.getProperty("user.dir"))
                    .resolve(nombre).toAbsolutePath().normalize();
            if (archivoValido(p2)) return p2;
        } catch (Exception ignored) { }

        // 3) carpeta donde están los .class / jar
        try {
            Path base = Paths.get(Main.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
            Path dir = Files.isDirectory(base) ? base : base.getParent();
            if (dir != null) {
                Path p3 = dir.resolve(nombre).toAbsolutePath().normalize();
                if (archivoValido(p3)) return p3;
            }
        } catch (Exception ignored) { }

        // 4) Buscar ignorando mayúsculas/minúsculas en user.dir
        try {
            Path dir = Paths.get(System.getProperty("user.dir"));
            if (Files.isDirectory(dir)) {
                for (Path p : Files.list(dir).toList()) {
                    if (p.getFileName().toString().equalsIgnoreCase(nombre) && archivoValido(p)) {
                        return p.toAbsolutePath().normalize();
                    }
                }
            }
        } catch (Exception ignored) { }

        return null;
    }

    private static int pedirOpcion(Scanner sc, int min, int max) {
        while (true) {
            System.out.print("Opción: ");
            String s = sc.nextLine().trim();

            try {
                int op = Integer.parseInt(s);
                if (op >= min && op <= max) return op;
            } catch (Exception ignored) { }

            if (max - min == 1) {
                System.out.println("❌ Entrada inválida. Ingrese " + min + " o " + max + ".");
            } else {
                System.out.println("❌ Entrada inválida. Ingrese un número entre " + min + " y " + max + ".");
            }
        }
    }
}