import java.util.Scanner;

public class InventarioTienda {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] codigos = new int[50];
        String[] nombres = new String[50];
        double[] precios = new double[50];
        int[] cantidades = new int[50];
        int contador = 0;
        int opcion;

        do {
            System.out.println("\nSELECCIONE UNA OPCIÓN:");
            System.out.println("1- Agregar un nuevo producto");
            System.out.println("2- Actualizar stock de un producto");
            System.out.println("3- Mostrar todos los productos");
            System.out.println("4- Buscar producto por código");
            System.out.println("5- Mostrar productos con stock bajo");
            System.out.println("6- Mostrar producto más caro y más barato");
            System.out.println("0- Salir");
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    contador = agregarProducto(codigos, nombres, precios, cantidades, contador, input);
                    break;
                case 2:
                    actualizarStock(codigos, cantidades, input);
                    break;
                case 3:
                    mostrarProductos(codigos, nombres, precios, cantidades, contador);
                    break;
                case 4:
                    buscarProductoPorCodigo(codigos, nombres, precios, cantidades, input);
                    break;
                case 5:
                    mostrarProductosConStockBajo(codigos, nombres, cantidades, contador);
                    break;
                case 6:
                    mostrarProductoMasCaroYMasBarato(nombres, precios, contador);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    public static int agregarProducto(int[] codigos, String[] nombres, double[] precios, int[] cantidades, int contador, Scanner input) {
        if (contador >= 50) {
            System.out.println("No se pueden agregar más productos.");
            return contador;
        }

        System.out.println("Introduce el código del producto:");
        int codigo = input.nextInt();
        for (int i = 0; i < contador; i++) {
            if (codigos[i] == codigo) {
                System.out.println("El código ya existe.");
                return contador;
            }
        }

        System.out.println("Introduce el nombre del producto:");
        nombres[contador] = input.next();

        System.out.println("Introduce el precio del producto:");
        double precio = input.nextDouble();
        if (precio <= 0) {
            System.out.println("El precio debe ser mayor a 0.");
            return contador;
        }

        System.out.println("Introduce la cantidad en stock:");
        int cantidad = input.nextInt();
        if (cantidad < 0) {
            System.out.println("La cantidad debe ser positiva.");
            return contador;
        }

        codigos[contador] = codigo;
        precios[contador] = precio;
        cantidades[contador] = cantidad;

        contador++;
        System.out.println("Producto agregado con éxito.");
        return contador;
    }

    public static void actualizarStock(int[] codigos, int[] cantidades, Scanner input) {
        System.out.println("Introduce el código del producto:");
        int codigo = input.nextInt();

        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i] == codigo) {
                System.out.println("Introduce la nueva cantidad en stock:");
                cantidades[i] = input.nextInt();
                System.out.println("Stock actualizado.");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public static void mostrarProductos(int[] codigos, String[] nombres, double[] precios, int[] cantidades, int contador) {
        if (contador == 0) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println("Código: " + codigos[i] + ", Nombre: " + nombres[i] + ", Precio: " + precios[i] + ", Stock: " + cantidades[i]);
        }
    }

    public static void buscarProductoPorCodigo(int[] codigos, String[] nombres, double[] precios, int[] cantidades, Scanner input) {
        System.out.println("Introduce el código del producto:");
        int codigo = input.nextInt();

        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i] == codigo) {
                System.out.println("Código: " + codigos[i] + ", Nombre: " + nombres[i] + ", Precio: " + precios[i] + ", Stock: " + cantidades[i]);
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public static void mostrarProductosConStockBajo(int[] codigos, String[] nombres, int[] cantidades, int contador) {
        boolean hayBajoStock = false;
        for (int i = 0; i < contador; i++) {
            if (cantidades[i] < 10) {
                System.out.println("Código: " + codigos[i] + ", Nombre: " + nombres[i] + ", Stock: " + cantidades[i]);
                hayBajoStock = true;
            }
        }
        if (!hayBajoStock) {
            System.out.println("No hay productos con stock bajo.");
        }
    }

    public static void mostrarProductoMasCaroYMasBarato(String[] nombres, double[] precios, int contador) {
        if (contador == 0) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        int indiceCaro = 0, indiceBarato = 0;
        for (int i = 1; i < contador; i++) {
            if (precios[i] > precios[indiceCaro]) {
                indiceCaro = i;
            }
            if (precios[i] < precios[indiceBarato]) {
                indiceBarato = i;
            }
        }

        System.out.println("Producto más caro: " + nombres[indiceCaro] + " con un precio de " + precios[indiceCaro]);
        System.out.println("Producto más barato: " + nombres[indiceBarato] + " con un precio de " + precios[indiceBarato]);
    }
}
