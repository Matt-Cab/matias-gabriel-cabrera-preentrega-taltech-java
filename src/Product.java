public class Product {
  private String name;
  private double price;
  private int stock;

  public Product(String name, double price, int stock) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
    }
    if (price <= 0) {
      throw new IllegalArgumentException("El precio debe ser mayor que 0.");
    }
    if (stock < 0) {
      throw new IllegalArgumentException("El stock no puede ser negativo.");
    }

    this.name = name.trim();
    this.price = price;
    this.stock = stock;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public int getStock() {
    return this.stock;
  }

  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
    }
    this.name = name.trim();
  }

  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("El precio debe ser mayor que 0.");
    }
    this.price = price;
  }

  public void setStock(int stock) {
    if (stock < 0) {
      throw new IllegalArgumentException("El stock no puede ser negativo.");
    }
    this.stock = stock;
  }

  public void reduceStock(int quantity) throws InsufficientStockException {
    if (quantity > stock) {
      throw new InsufficientStockException(
          String.format("Stock insuficiente para '%s'. Disponible: %d, solicitado: %d",
              name, stock, quantity)
      );
    }
    this.stock -= quantity;
  }

  @Override
  public String toString() {
    return String.format("Producto: %s | Precio: $%.2f | Stock: %d", name, price, stock);
  }
}
