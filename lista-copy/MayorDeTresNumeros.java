import java.util.Scanner;

public class MayorDeTresNumeros {
    public static void main(String[] args) {
        // Crear el objeto para leer datos desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese tres números, uno por uno
        System.out.println("Ingresa el primer número: ");
        int numero1 = scanner.nextInt();

        System.out.println("Ingresa el segundo número: ");
        int numero2 = scanner.nextInt();

        System.out.println("Ingresa el tercer número: ");
        int numero3 = scanner.nextInt();

        // Declarar variables para comparar
        int mayor = numero1; // asumir que el primero es el mayor

        // Comparar el primer número con el segundo
        if (numero2 > mayor) {
            mayor = numero2; // actualizar si el segundo es mayor
        }

        // Comparar el mayor actual con el tercer número
        if (numero3 > mayor) {
            mayor = numero3; // actualizar si el tercero es mayor
        }

        // Mostrar el resultado final
        System.out.println("El número mayor es: " + mayor);
        
        // Cerrar el scanner
        scanner.close();
    }
}

