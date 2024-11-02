import java.util.Scanner;

public class InventarioTienda {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Definimos los vectores y variables a utilizar, los vectores tienen una capacidad de 50 espacios, para almacenar 50 productos
        int[] codigos = new int[50];
        String[] nombres = new String[50];
        float[] precios = new float[50];
        int[] cantidades = new int[50];
        int contador = 0;
        int opcion;

        do {//Se abre el menu para seleccionar las opciones concedidas
            System.out.println("---------------------------------------------");
            System.out.println("SELECCIONE UNA OPCIÓN:                      |");
            System.out.println("1- Agregar un nuevo producto                |");
            System.out.println("2- Actualizar stock de un producto          |");
            System.out.println("3- Mostrar todos los productos              |");
            System.out.println("4- Buscar producto por código               |");
            System.out.println("5- Mostrar productos con stock bajo         |");
            System.out.println("6- Mostrar producto más caro y más barato   |");
            System.out.println("0- Salir                                    |");
            System.out.println("---------------------------------------------");
            opcion = input.nextInt();

            switch (opcion) {//En este switch se nos da a elegir que haremos con el producto, desde crearlo hasta actualizarlo etc...
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

    public static int agregarProducto(int[] codigos, String[] nombres, float[] precios, int[] cantidades, int contador, Scanner input) {
        if (contador >= 50) {//En este metodo utilizamos utilizamos un if para controlar el vector en caso de que el usuario llegue a poner el maximo de productos
            System.out.println("No se pueden agregar más productos.");
            return contador;
        }
        //En esta parte se va creando el producto pidiendole informacion al usuario, a diferencia de la linea anterior, en este caso si existe un producto con un mismo codigo le informara al usuario de que no puede usar ese codigo
        System.out.println("Introduce el código del producto:");
        int codigo = input.nextInt();
        for (int i = 0; i < contador; i++) {
            if (codigos[i] == codigo) {
                System.out.println("El código ya existe.");
                return contador;
            }
        }
        System.out.println("Introduce el nombre del producto:"); //Le pedimos al usuario que introduzca el nombre del producto
        nombres[contador] = input.next();

        System.out.println("Introduce el precio del producto:"); // Le pedimos al usuario que introduzca el precio del producto (puede ser decimal)
        float precio = input.nextFloat();
        if (precio <= 0) { //Si el usuario ingresa un precio menor o igual a 0, sale un error
            System.out.println("El precio debe ser mayor a 0.");
            return contador;
        }

        System.out.println("Introduce la cantidad en stock:"); // Le pedimos al usuario que ingrese la cantidad de productos en stock
        int cantidad = input.nextInt();
        if (cantidad < 0) { //Si el usuario ingresa una cantidad  negativa de stock,sale un error
            System.out.println("La cantidad debe ser positiva.");
            return contador;
        }

        codigos[contador] = codigo;
        precios[contador] = precio;
        cantidades[contador] = cantidad;

        contador++; //Si todas las anteriores comprobaciones salen bien, el producto se agrega con éxito
        System.out.println("Producto agregado con éxito.");
        return contador;
    }

    public static void actualizarStock(int[] codigos, int[] cantidades, Scanner input) {
        System.out.println("Introduce el código del producto:");
        int codigo = input.nextInt();
        int aux=0;
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i] == codigo) {
                System.out.println("Introduce la nueva cantidad en stock:");
                aux=input.nextInt();
                if (aux > 0 ){ //En esta parte se nos ofrece la posibilidad de cambiar el stock del producto que seleccionemos, pero si la cantidad del stock elegido es negativa no se cambiara.
                    cantidades[i] = aux;
                    System.out.println("Stock actualizado.");
                }
                else {
                    System.out.println("ERROR:El stock no puede ser negativo");
                }
                return;
            }}
    }

    public static void mostrarProductos(int[] codigos, String[] nombres, float[] precios, int[] cantidades, int contador) {
        if (contador == 0) { //Si no hay productos en el inventario se muestra la advertencia
            System.out.println("No hay productos en el inventario.");
            return;
        } //Si hay productos se muestran en pantalla
        for (int i = 0; i < contador; i++) {
            System.out.println("Código: " + codigos[i] + ", Nombre: " + nombres[i] + ", Precio: " + precios[i] + ", Stock: " + cantidades[i]);
        }
    }

    public static void buscarProductoPorCodigo(int[] codigos, String[] nombres, float[] precios, int[] cantidades, Scanner input) {
        System.out.println("Introduce el código del producto:"); // En este metodo se le pide al usuario el codigo de un producto,luego se lo busca y se lo muestra, si no es encontrado se muestra una advertencia
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
        boolean hayBajoStock = false; //En este metodo deducimos si un producto tiene stock bajo si tiene menos de 10 unidades en stock, si no hay productos con bajo stock, se muestra un mensaje
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

    public static void mostrarProductoMasCaroYMasBarato(String[] nombres, float[] precios, int contador) {
        if (contador == 0) { //En este metodo si no hay productos en el inventario se muestra un mensaje, si hay productos se muestra el precio del mas caro y del mas barato
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
        System.out.println(
                "Producto más barato: " + nombres[indiceBarato] + " con un precio de " + precios[indiceBarato]);
    }
}
