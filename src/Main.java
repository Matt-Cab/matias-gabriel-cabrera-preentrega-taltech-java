import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Product> productsList = new ArrayList<>();
    ArrayList<Order> ordersList = new ArrayList<>();

    fillProductsList(productsList);
    int userOption = -1;

    while (userOption != 0) {
      showMenu();
      userOption = scanner.nextInt();
      scanner.nextLine(); // limpiar buffer

      manageOptions(userOption, productsList, ordersList);
    }

    scanner.close();
  }

  public static void showMenu() {
    System.out.println("""
    \n##############################################################
    
    ----- MENÚ DE OPCIONES -----
    Ingrese un número correspondiente a la opción deseada:
    
      0: Salir.
      1: Crear producto.
      2: Mostrar productos.
      3: Buscar producto por nombre.
      4: Editar producto.
      5: Borrar producto.
      6: Crear pedido.
      7: Mostrar pedidos.
    
    ##############################################################
    """);
  }

  public static void manageOptions(int userOption, ArrayList<Product> productsList, ArrayList<Order> ordersList) {
    switch (userOption) {
      case 0 -> System.out.println("Gracias por usar esta app.");
      case 1 -> { createProduct(productsList); pause(); }
      case 2 -> { showProducts(productsList); pause(); }
      case 3 -> { getProductsByName(productsList); pause(); }
      case 4 -> { editProduct(productsList); pause(); }
      case 5 -> { deleteProduct(productsList); pause(); }
      case 6 -> { createOrder(productsList, ordersList); pause(); }
      case 7 -> { showOrders(ordersList); pause(); }
      default -> {
        System.out.println("Ingrese una opción válida.");
        pause();
      }
    }
  }

  // --- CRUD Productos ---
  public static void createProduct(ArrayList<Product> productsList) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el nombre del nuevo producto: ");
    String name = scanner.nextLine().trim();

    System.out.print("Ingrese el precio: ");
    double price = scanner.nextDouble();

    System.out.print("Ingrese el stock: ");
    int stock = scanner.nextInt();

    try {
      Product newProduct = new Product(name, price, stock);
      productsList.add(newProduct);
      System.out.println("Producto agregado con éxito.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error al crear producto: " + e.getMessage());
    }
  }

  public static void showProducts(ArrayList<Product> productsList) {
    if (productsList.isEmpty()) {
      System.out.println("No hay productos disponibles.");
      return;
    }

    System.out.println("Listado de productos:");
    for (Product p : productsList) {
      System.out.println(p);
    }
  }

  public static void getProductsByName(ArrayList<Product> productsList) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el nombre del producto a buscar: ");
    String search = scanner.nextLine().toLowerCase();

    ArrayList<Product> results = new ArrayList<>();
    for (Product p : productsList) {
      if (p.getName().toLowerCase().contains(search)) {
        results.add(p);
      }
    }

    if (results.isEmpty()) System.out.println("No se encontraron productos.");
    else showProducts(results);
  }

  public static void editProduct(ArrayList<Product> productsList) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el nombre del producto a editar: ");
    String name = scanner.nextLine();

    Product p = getProductByName(productsList, name);
    if (p == null) {
      System.out.println("Producto no encontrado.");
      return;
    }

    System.out.print("Nuevo nombre (ENTER para mantener): ");
    String newName = scanner.nextLine().trim();

    System.out.print("Nuevo precio (0 para mantener): ");
    double newPrice = scanner.nextDouble();

    System.out.print("Nuevo stock (-1 para mantener): ");
    int newStock = scanner.nextInt();

    try {
      if (!newName.isEmpty()) p.setName(newName);
      if (newPrice > 0) p.setPrice(newPrice);
      if (newStock >= 0) p.setStock(newStock);
      System.out.println("Producto actualizado.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error al intentar actualizar el producto: " + e.getMessage());
    }
  }

  public static void deleteProduct(ArrayList<Product> productsList) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el nombre del producto a eliminar: ");
    String name = scanner.nextLine();

    Product p = getProductByName(productsList, name);
    if (p == null) {
      System.out.println("Producto no encontrado.");
      return;
    }

    System.out.printf("¿Desea eliminar '%s'? (1 = Sí / 0 = No): ", p.getName());
    int option = scanner.nextInt();
    if (option == 1) {
      productsList.remove(p);
      System.out.println("Producto eliminado.");
    } else {
      System.out.println("Operación cancelada.");
    }
  }

  // --- Pedidos ---
  public static void createOrder(ArrayList<Product> productsList, ArrayList<Order> ordersList) {
    if (productsList.isEmpty()) {
      System.out.println("No hay productos disponibles para crear un pedido.");
      return;
    }

    Scanner scanner = new Scanner(System.in);
    Order order = new Order();

    System.out.println("CREANDO NUEVO PEDIDO...");
    String keep;
    do {
      showProducts(productsList);
      System.out.print("\nIngrese el nombre del producto: ");
      String productName = scanner.nextLine().trim();

      Product p = getProductByName(productsList, productName);
      if (p == null) {
        System.out.println("Producto no encontrado.");
      } else {
        System.out.printf("Ingrese cantidad (disponible: %d): ", p.getStock());
        int quantity = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        try {
          order.addProduct(p, quantity);
          System.out.println("Producto agregado al pedido.");
        } catch (InsufficientStockException | IllegalArgumentException e) {
          System.out.println("ERROR: " + e.getMessage());
        }
      }

      System.out.print("¿Desea agregar otro producto? (s/n): ");
      keep = scanner.nextLine().trim().toLowerCase();
    } while (keep.equalsIgnoreCase("s"));

    // Evitar pedidos vacíos
    if (order.isEmpty()) {
      System.out.println("No se puede crear un pedido vacío. Operación cancelada.");
      return;
    }

    ordersList.add(order);
    System.out.println("\nPedido creado exitosamente:");
    System.out.println(order);
  }


  public static void showOrders(ArrayList<Order> ordersList) {
    if (ordersList.isEmpty()) {
      System.out.println("No hay pedidos registrados.");
      return;
    }

    System.out.println("LISTADO DE PEDIDOS:");
    for (Order order : ordersList) {
      System.out.println(order);
    }
  }

  // --- Utilidades ---
  public static Product getProductByName(ArrayList<Product> productsList, String name) {
    if (name == null || name.trim().isEmpty()) return null;

    String search = name.trim().toLowerCase();
    ArrayList<Product> matches = new ArrayList<>();

    for (Product p : productsList) {
      if (p.getName().toLowerCase().contains(search)) {
        matches.add(p);
      }
    }

    if (matches.isEmpty()) {
      return null;
    } else if (matches.size() == 1) {
      return matches.getFirst();
    } else {
      System.out.println("\nSe encontraron varios productos que coinciden:");
      for (int i = 0; i < matches.size(); i++) {
        System.out.printf("%d. %s\n", i + 1, matches.get(i).getName());
      }

      Scanner scanner = new Scanner(System.in);
      System.out.print("Ingrese el número del producto que desea seleccionar: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // limpiar buffer

      if (choice >= 1 && choice <= matches.size()) {
        return matches.get(choice - 1);
      } else {
        System.out.println("Selección inválida.");
        return null;
      }
    }
  }

  public static void pause() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nPresione ENTER para continuar...");
    scanner.nextLine();
  }

  public static void fillProductsList(ArrayList<Product> productsList) {
    productsList.add(new Product("Procesador AMD Ryzen 7 7800X3D", 499.99, 8));
    productsList.add(new Product("Motherboard ASUS ROG Strix B650E-F", 289.99, 6));
    productsList.add(new Product("Memoria RAM Corsair Vengeance 32GB DDR5", 159.99, 12));
    productsList.add(new Product("Placa de Video NVIDIA RTX 4070 Ti", 849.99, 4));
    productsList.add(new Product("Disco SSD Samsung 990 PRO 2TB NVMe", 179.99, 10));
    productsList.add(new Product("Fuente de Poder Corsair RM850x 850W", 139.99, 7));
    productsList.add(new Product("Gabinete NZXT H7 Flow", 129.99, 5));
    productsList.add(new Product("Monitor ASUS TUF Gaming 27'' 165Hz", 299.99, 9));
    productsList.add(new Product("Teclado Mecánico HyperX Alloy Origins", 119.99, 15));
    productsList.add(new Product("Mouse Logitech G Pro X Superlight", 149.99, 20));
  }
}
