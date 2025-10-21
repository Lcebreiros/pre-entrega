import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Producto> productos = getIniciales();
        int nextId = productos.stream().mapToInt(Producto::getId).max().orElse(0) + 1;

        while (true) {
            System.out.println("""
                0 - Salir
                1 - Crear producto
                2 - Mostrar productos
                3 - Buscar por marca/modelo
                4 - Editar producto
                5 - Eliminar producto
            """);

            if (!input.hasNextInt()) { System.out.println("Opción inválida"); input.next(); continue; }
            int op = input.nextInt(); input.nextLine();

            switch (op) {
                case 1 -> { // CREAR
                    Producto p = addProduct(input);
                    p.setId(nextId++);
                    productos.add(p);
                    System.out.println("✅ Producto agregado\n");
                }
                case 2 -> listar(productos);
                case 3 -> buscar(productos, input);
                case 4 -> editar(productos, input);
                case 5 -> eliminar(productos, input);
                case 0 -> { System.out.println("👋 Hasta luego"); return; }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    static Producto addProduct(Scanner input) {
        System.out.print("Marca: ");     String marca = input.nextLine();
        System.out.print("Modelo: ");    String modelo = input.nextLine();
        System.out.print("Precio: ");    double precio = leerDouble(input);
        System.out.print("Colección: "); String coleccion = input.nextLine();
        return new Producto(0, marca, modelo, precio, coleccion);
    }

    static void listar(List<Producto> productos) {
        if (productos.isEmpty()) { System.out.println("No hay productos\n"); return; }
        productos.forEach(p -> System.out.println(p));
        System.out.println();
    }

    static void buscar(List<Producto> productos, Scanner input) {
        System.out.print("Buscar (marca o modelo): ");
        String q = input.nextLine().toLowerCase().trim();
        List<Producto> res = productos.stream()
                .filter(p -> p.getMarca().toLowerCase().contains(q) || p.getModelo().toLowerCase().contains(q))
                .toList();
        if (res.isEmpty()) System.out.println("Sin resultados\n");
        else res.forEach(p -> System.out.println(p));
        System.out.println();
    }

    static void editar(List<Producto> productos, Scanner input) {
        Producto p = pedirPorId(productos, input);
        if (p == null) return;

        System.out.printf("Editando %s%n", p);
        System.out.print("Nueva marca (enter = igual): "); String marca = input.nextLine();
        if (!marca.isBlank()) p.setMarca(marca);

        System.out.print("Nuevo modelo (enter = igual): "); String modelo = input.nextLine();
        if (!modelo.isBlank()) p.setModelo(modelo);

        System.out.print("Nuevo precio (enter = igual): "); String precioTxt = input.nextLine();
        if (!precioTxt.isBlank()) p.setPrecio(Double.parseDouble(precioTxt));

        System.out.print("Nueva colección (enter = igual): "); String col = input.nextLine();
        if (!col.isBlank()) p.setColeccion(col);

        System.out.println("✅ Producto actualizado\n");
    }

    static void eliminar(List<Producto> productos, Scanner input) {
        Producto p = pedirPorId(productos, input);
        if (p == null) return;
        productos.remove(p);
        System.out.println("Producto eliminado\n");
    }

    static Producto pedirPorId(List<Producto> productos, Scanner input) {
        if (productos.isEmpty()) { System.out.println("No hay productos\n"); return null; }
        System.out.print("ID: ");
        if (!input.hasNextInt()) { System.out.println("ID inválido\n"); input.nextLine(); return null; }
        int id = input.nextInt(); input.nextLine();
        return productos.stream().filter(x -> x.getId() == id).findFirst()
                .orElseGet(() -> { System.out.println("No existe ese ID\n"); return null; });
    }

    static double leerDouble(Scanner in) {
        while (!in.hasNextDouble()) { System.out.print("Número inválido, reintente: "); in.next(); }
        double v = in.nextDouble(); in.nextLine(); return v;
    }

    static List<Producto> getIniciales() {
        return new ArrayList<>(List.of(
                new Producto(1, "Adidas", "Metalbone 3.4", 400, "Ale Galán"),
                new Producto(2, "Nox", "AT10 12L", 395, "Agustín Tapia"),
                new Producto(3, "Babolat", "Technical Viper", 340, "Juan Lebrón"),
                new Producto(4, "Head", "Coello Pro", 395, "Arturo Coello"),
                new Producto(5, "Bullpadel", "Neuron 02 Edge", 395, "Fede Chingotto")
        ));
    }
}