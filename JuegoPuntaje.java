import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoPuntaje {

    // Listas de nombres y apodos graciosos
    private static final String[] nombres = {
        "Sebastián", "Gio", "Joel", "Granados que vive en un rancho", 
        "Emanuel", "Quique", "Triste", "Mariguano", "Infiel como Gio"
    };
    private static final String[] apodos = {
        "Mariguano", "Alcohólico", "Burro", "Ludópata", "Vicioso",
        "El que Tanques", "Infiel", "El que no perdona nada", "El Comal"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String nombreUsuario = "";

        // Solicitar el nombre inicial del usuario
        System.out.print("Introduce tu nombre: ");
        String nombreEntrada = scanner.nextLine();
        System.out.println("Hola, " + nombreEntrada + ".");

        // Generar un nuevo nombre con apodo
        nombreUsuario = generarNombreConApodo(random);
        System.out.println("Tu nuevo nombre es: " + nombreUsuario);
        System.out.println("¡Ay no! ¿No te gustó el nuevo nombre, niña?");

        // Preguntar si le gusta el nuevo nombre
        System.out.print("¿Te gusta el nombre " + nombreUsuario + "? (Sí/No): ");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("sí")) {
            // Si el usuario responde que sí, cambiar el nombre y apodo
            nombreUsuario = generarNombreConApodo(random);
            System.out.println("Aquí no se hace lo que digas, " + nombreUsuario + ". ¡Vamos a trabajar!");
        } else if (respuesta.equals("no")) {
            // Si el usuario responde que no, mantener el nombre y apodo
            System.out.println("A pues me vale, " + nombreUsuario + ". ¡Vamos a seguir con esto!");
        } else {
            // Si el usuario responde algo diferente, manejarlo
            System.out.println("Respuesta no válida, pero de todos modos, tu nombre es " + nombreUsuario + ".");
        }

        // Inicialización de variables
        List<String> historial = new ArrayList<>();
        int fichasRojas, fichasAzules, fichasAmarillas;

        // Menú principal
        while (true) {
            System.out.println("==========================================");
            System.out.println("      Bienvenido a la Calculadora");
            System.out.println("      de Puntajes del Juego");
            System.out.println("==========================================");
            System.out.println("Usuario actual: " + nombreUsuario);
            System.out.println("1. Calcular puntaje");
            System.out.println("2. Ver historial de cálculos");
            System.out.println("3. Borrar un cálculo específico");
            System.out.println("4. Ver fórmula del cálculo");
            System.out.println("5. Ver estadísticas de errores");
            System.out.println("6. Cambiar nombre y apodo");
            System.out.println("7. Regresar al menú");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = obtenerRespuestaConTiempo(scanner);

            switch (opcion) {
                case 1: // Calcular puntaje
                    System.out.println("\n--- Cálculo de Puntaje ---");
                    fichasRojas = obtenerNumeroValido(scanner, "Cantidad de fichas rojas (primer lugar): ");
                    fichasAzules = obtenerNumeroValido(scanner, "Cantidad de fichas azules (segundo lugar): ");
                    fichasAmarillas = obtenerNumeroValido(scanner, "Cantidad de fichas amarillas (tercer lugar): ");

                    // Verificar si todos los números están en el rango
                    if (fichasRojas >= 1 && fichasRojas <= 10 && fichasAzules >= 1 && fichasAzules <= 10 && fichasAmarillas >= 1 && fichasAmarillas <= 10) {
                        int puntaje = calcularPuntaje(fichasRojas, fichasAzules, fichasAmarillas);
                        System.out.println("=== Resultado ===");
                        System.out.println("Fichas Rojas: " + fichasRojas + ", Fichas Azules: " + fichasAzules + ", Fichas Amarillas: " + fichasAmarillas + ", Puntaje: " + puntaje);

                        // Mostrar frases de éxito si los 3 números son correctos
                        mostrarFraseDeExito();
                    } else {
                        // Mostrar frases de error si los números no son válidos
                        mostrarFraseDeError();
                    }

                    // Guardar historial
                    String calculo = "Fichas Rojas: " + fichasRojas + ", Fichas Azules: " + fichasAzules + ", Fichas Amarillas: " + fichasAmarillas;
                    historial.add(calculo);
                    break;

                case 2: // Ver historial
                    System.out.println("\n--- Historial de Cálculos ---");
                    if (historial.isEmpty()) {
                        System.out.println("No hay ningún historial, chango.");
                    } else {
                        System.out.println("Ahi te va el historial...");
                        try {
                            for (int i = 1; i <= historial.size(); i++) {
                                System.out.println(i + ". " + historial.get(i - 1));
                            }
                        } catch (Exception e) {
                            System.out.println("Hubo un error al mostrar el historial.");
                        }
                    }
                    break;

                case 3: // Borrar cálculo específico
                    System.out.println("\n--- Borrar Cálculo Específico ---");
                    if (historial.isEmpty()) {
                        System.out.println("No hay cálculos para borrar.");
                    } else {
                        System.out.println("Selecciona el cálculo a borrar (por número): ");
                        for (int i = 0; i < historial.size(); i++) {
                            System.out.println((i + 1) + ". " + historial.get(i));
                        }
                        int indiceBorrar = scanner.nextInt() - 1;
                        if (indiceBorrar >= 0 && indiceBorrar < historial.size()) {
                            historial.remove(indiceBorrar);
                            System.out.println("Cálculo borrado exitosamente.");
                        } else {
                            System.out.println("Índice inválido. No sé si eres ciego o idiota.");
                        }
                    }
                    break;

                case 4: // Ver fórmula
                    System.out.println("\n--- Fórmula del cálculo ---");
                    System.out.println("El puntaje se calcula de la siguiente manera:");
                    System.out.println("Puntaje = (Fichas Rojas)^3 + 2*(Fichas Azules) - (Fichas Amarillas)^2");
                    break;

                case 5: // Ver estadísticas de errores
                    System.out.println("\n--- Estadísticas de Errores ---");
                    System.out.println("Errores cometidos: 42");
                    System.out.println("Desviaciones de lógica: 7");
                    System.out.println("Vidas perdidas: 1");
                    break;

                case 6: // Cambiar nombre y apodo
                    nombreUsuario = generarNombreConApodo(random);
                    System.out.println("Tu nuevo nombre es: " + nombreUsuario);
                    break;

                case 7: // Regresar al menú
                    System.out.println("Regresando al menú...");
                    break;

                case 8: // Salir
                    System.out.println("Hasta luego, " + nombreUsuario + ". ¡Te dejo pensando en mujeres!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida, " + nombreUsuario + ". ¿Acaso no sabes leer?");
            }
        }
    }

    // Función para calcular el puntaje
    public static int calcularPuntaje(int fichasRojas, int fichasAzules, int fichasAmarillas) {
        return (int) (Math.pow(fichasRojas, 3) + 2 * fichasAzules - Math.pow(fichasAmarillas, 2));
    }

    // Función para generar nombre con apodo
    public static String generarNombreConApodo(Random random) {
        String nombre = nombres[random.nextInt(nombres.length)];
        String apodo = apodos[random.nextInt(apodos.length)];
        return nombre + " el " + apodo;
    }

    // Función para mostrar frases de éxito
    public static void mostrarFraseDeExito() {
        String[] frasesExito = {
            "Bien hecho, ¡te has ganado una kawama!",
            "Así mero, ¡te has ganado un beso!",
            "Bien ahí, ¡te va el Instagram de una chiquilla toda preciosa!"
        };
        Random rand = new Random();
        System.out.println(frasesExito[rand.nextInt(frasesExito.length)]);
    }

    // Función para mostrar frases de error
    public static void mostrarFraseDeError() {
        String[] frasesError = {
            "¡Vamos, ciego, ingresa un número entre 1 y 10!",
            "¿No sabes leer? Solo números entre 1 y 10, ¿qué no entiendes?",
            "A ver si dejas de escribir tonterías y pones números correctos."
        };
        Random rand = new Random();
        System.out.println(frasesError[rand.nextInt(frasesError.length)]);
    }

    // Función para obtener un número válido
    public static int obtenerNumeroValido(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(scanner.nextLine());
                if (numero < 1 || numero > 10) {
                    System.out.println("Número fuera de rango, debe ser entre 1 y 10.");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número. ¿Es que no sabes escribir?");
            }
        }
    }

    // Función para obtener respuesta con tiempo para el menú
    public static int obtenerRespuestaConTiempo(Scanner scanner) {
        int respuesta = -1;
        while (respuesta < 1 || respuesta > 8) {
            try {
                respuesta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número.");
            }
        }
        return respuesta;
    }
}
