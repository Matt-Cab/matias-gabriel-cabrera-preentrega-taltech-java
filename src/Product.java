import java.util.UUID;

public class Product {
    private final String id;
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

        // generar un id "corto"
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name.trim();
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor que 0.");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo.");
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
        return String.format("ID: %s | Producto: %s | Precio: $%.2f | Stock: %d",
                id, name, price, stock);
    }
}
