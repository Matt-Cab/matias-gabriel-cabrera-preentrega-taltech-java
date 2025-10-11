import java.util.ArrayList;

public class Order {
  private static int nextId = 1;

  private int id;
  private ArrayList<OrderItem> items;
  private double total;

  public Order() {
    this.id = nextId++;
    this.items = new ArrayList<>();
    this.total = 0;
  }

  public int getId() {
    return id;
  }

  public void addProduct(Product product, int quantity) throws InsufficientStockException {
    product.reduceStock(quantity); // lanza excepción si no hay stock suficiente
    OrderItem item = new OrderItem(product, quantity);
    items.add(item);
    total += item.getSubtotal();
  }

  public double getTotal() {
    return total;
  }

  public ArrayList<OrderItem> getItems() {
    return items;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Pedido #").append(id).append("\n");
    for (OrderItem item : items) {
      sb.append(" - ").append(item).append("\n");
    }
    sb.append(String.format("Total del pedido: $%.2f\n", total));
    return sb.toString();
  }

  public boolean isEmpty() {
    return items == null || items.isEmpty();
  }
}
