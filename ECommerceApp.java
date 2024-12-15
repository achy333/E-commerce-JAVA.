// Java Project: Simple E-Commerce Simulation

// 1. Main Class
public class ECommerceApp {
    public static void main(String[] args) {
        User user1 = new User(1, "Antim Pandey", "antim.pandey@example.com");
        User user2 = new User(2, "Shreyash", "shreyash@example.com");

        Product laptop = new Product(1, "Laptop", 800.00, 10);
        Product smartphone = new Product(2, "Smartphone", 500.00, 20);
        Product headphones = new Product(3, "Headphones", 50.00, 50);

        Order order1 = new Order(1, user1);
        order1.addOrderItem(laptop, 1);
        order1.addOrderItem(headphones, 1);

        Order order2 = new Order(2, user2);
        order2.addOrderItem(smartphone, 1);
        order2.addOrderItem(headphones, 1);

        order1.printOrderDetails();
        order2.printOrderDetails();
    }
}

// 2. User Class
class User {
    private int userID;
    private String userName;
    private String email;

    public User(int userID, String userName, String email) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}

// 3. Product Class
class Product {
    private int productID;
    private String productName;
    private double price;
    private int stock;

    public Product(int productID, String productName, double price, int stock) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public boolean reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
            return true;
        } else {
            System.out.println("Not enough stock for " + productName);
            return false;
        }
    }
}

// 4. Order Class
import java.util.ArrayList;
import java.util.List;

class Order {
    private int orderID;
    private User user;
    private List<OrderItem> items;
    private double totalAmount;

    public Order(int orderID, User user) {
        this.orderID = orderID;
        this.user = user;
        this.items = new ArrayList<>();
    }

    public void addOrderItem(Product product, int quantity) {
        if (product.reduceStock(quantity)) {
            OrderItem item = new OrderItem(product, quantity);
            items.add(item);
            totalAmount += item.getTotalPrice();
        }
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("User: " + user.getUserName());
        System.out.println("Items:");
        for (OrderItem item : items) {
            System.out.println("- " + item.getProduct().getProductName() + " x " + item.getQuantity() + " @ $" + item.getProduct().getPrice());
        }
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println();
    }
}

// 5. OrderItem Class
class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
