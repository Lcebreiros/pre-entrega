import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Product> productDB = getInitialProducts();
        int nextId = productDB.size() + 1;
        int userOption;

        System.out.println("Bienvenido a la tienda de palas de padel n1");

        label:
        while (true) {
            System.out.println("""
                    Ingrese el numero de la opcion que desee para continuar:
                    0 - Salir
                    1 - Crear un producto
                    2 - Mostrar productos
                    3 - Buscar por nombres
                    4 - Editar un producto
                    5 - Eliminar producto
                    6 - Nueva funcion (coming soon)
                    """);
            if (!input.hasNextInt()) {
                System.out.println("Opcion incorrecta");
                input.next();
                continue;
            }
            userOption = input.nextInt();
            input.nextLine(); // consume endline

            switch (userOption) {
                case 1 -> {
                    createProduct(nextId, productDB, input);
                    nextId += 1;
                }
                case 2 -> listProducts(productDB, input);
                case 3 -> searchProductByName(productDB, input);
                case 4 -> editProduct(productDB, input);
                case 5 -> deleteProduct(productDB, input);
                case 6 -> System.out.println("Trabajando en ello");
                case 0 -> {
                    System.out.println("Gracias por visitarnos");
                    break label;
                }
                default -> System.out.println("Opcion incorrecta");
            }
        }
    }

    public static void createProduct(int id, ArrayList<Product> products, Scanner input) {
        System.out.print("Ingresa un nombre para tu product:2");
        String name = input.nextLine();
        products.add(new Product(id, name));
        pause(input);
    }

    public static void listProducts(ArrayList<Product> products, Scanner input) {
        System.out.println("|------------------|");
        System.out.println("|     Productos    |");
        System.out.println("|------------------|");

        if (products == null || products.isEmpty()) {
            System.out.println("No hay productos para mostrar");
        } else {
            for (Product product : products) {
                System.out.printf("%2d. %s%n", product.id, product.name);
            }
        }
        System.out.println("|------------------|");
    }

    public static void searchProductByName(ArrayList<Product> products, Scanner input) {
        System.out.println("Ingresa el nombre de un producto...");
        String search = input.nextLine();
        ArrayList<Product> foundProducts = new ArrayList<>();

        for (Product product : products) {
            if (isIncluded(product.name, search)) {
                foundProducts.add(product);

            }
        }
        listProducts(foundProducts, input);
    }

    public static void editProduct(List<Product> products, Scanner input) {
        Product product = getProductById(products, input);

        if (product == null) {
            System.out.println("No hay productos para editar");
            return;
        }

        System.out.printf("Editando: %s%n", product.name);
        System.out.print("Ingresa el nuevo nombre:");
        String newName = input.nextLine();

        String oldName = product.name;
        product.name = newName;

        System.out.printf("Nombre de  '%s' cambiado a '%s'%n", oldName, newName);
    }

    public static void deleteProduct(List<Product> products, Scanner input) {
        Product product = getProductById(products, input);

        if (product == null) {
            System.out.println("No hay productos que borrar");
        }

        System.out.printf("Deleting product: %s%n", product.name);
        products.remove(product);
        System.out.println("Producto eliminado correctamente");
    }

    public static Product getProductById(List<Product> products, Scanner input) {
        System.out.print("Enter the product ID: ");
        while (!input.hasNextInt()) {
            System.out.print("Please enter a valid numeric ID: ");
            input.next();
        }
        int idSearch = input.nextInt();
        input.nextLine(); // consume endline

        for (Product product : products) {
            if (product.id == idSearch) {
                return product;
            }
        }
        return null;
    }

    public static boolean isIncluded(String fullName, String partialName) {
        return formatSearch(fullName).contains(formatSearch(partialName));
    }

    public static String formatSearch(String text) {
        return text.trim().toLowerCase();
    }

    public static void pause(Scanner input) {
        System.out.println("Presiona ENTER para continuar...");
        input.nextLine();
        for (int i = 0; i < 10; ++i) System.out.println();
    }

    public static ArrayList<Product> getInitialProducts() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Pala Nox AT10 18k Genius 2026"));
        products.add(new Product(2, "Pala Nox AT10 12k Genius 2026"));
        products.add(new Product(3, "Pala Nox AT10 18k Attack 2026"));
        products.add(new Product(4, "Pala Adidas Metalbone 3.4 Ale Galan"));
        products.add(new Product(5, "Pala adidas Metalbone 3.4 HRD"));
        products.add(new Product(6, "Pala Babolat Technical Viper Juan Lebron"));
        products.add(new Product(7, "Pala Bullpadel Neuron 02 Edge Federico Chingotto 2026"));
        products.add(new Product(8, "Pala Head Coello Pro 2025"));
        products.add(new Product(9, "Pala Bullpadel Vertex 05 Geo Pablo Cardona"));
        products.add(new Product(10, "Pala Bullpadel Hack 05 2026 Paquito Navarro"));

        return products;
    }

    public static class Product {
        public int id;
        public String name;

        public Product(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}


