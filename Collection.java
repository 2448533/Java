import java.util.*;

class Customer {
    private String id;
    private String name;
    private int loyaltyPoints;
    private Set<String> purchasedProducts = new HashSet<>();

    public Customer(String id, String name, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public boolean addPurchasedProduct(String productId) {
        return purchasedProducts.add(productId);
    }

    public Set<String> getPurchasedProducts() {
        return purchasedProducts;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + id + ", Name=" + name + ", Loyalty Points=" + loyaltyPoints + "]";
    }
}

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "Product [ID=" + id + ", Name=" + name + ", Price=" + price + "]";
    }
}

class Order {
    private String id;
    private String customerId;
    private String productId;
    private Date orderDate;

    public Order(String id, String customerId, String productId, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order [ID=" + id + ", CustomerID=" + customerId + ", ProductID=" + productId + ", OrderDate=" + orderDate + "]";
    }
}

public class Collection {
    private static Map<String, Customer> customerMap = new HashMap<>();
    private static Map<String, Product> productMap = new HashMap<>();
    private static List<Order> orders = new ArrayList<>();
    private static TreeSet<Product> sortedProducts = new TreeSet<>(Comparator.comparingDouble(Product::getPrice));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Menu Application ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. View Customers (Sorted by Loyalty)");
            System.out.println("5. View Products (Sorted by Price)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    placeOrder(scanner);
                    break;
                case 4:
                    viewCustomers();
                    break;
                case 5:
                    viewProducts();
                    break;
                case 6:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Loyalty Points: ");
        int points = scanner.nextInt();

        Customer customer = new Customer(id, name, points);
        customerMap.put(id, customer);
        System.out.println("Customer added successfully.");
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        Product product = new Product(id, name, price);
        productMap.put(id, product);
        sortedProducts.add(product);
        System.out.println("Product added successfully.");
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        Customer customer = customerMap.get(customerId);
        Product product = productMap.get(productId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (!customer.addPurchasedProduct(productId)) {
            System.out.println("Product already purchased by this customer.");
            return;
        }

        orders.add(new Order(id, customerId, productId, new Date()));
        customer.addLoyaltyPoints(10);
        System.out.println("Order placed successfully.");
    }

    private static void viewCustomers() {
        System.out.println("\n--- Customers Sorted by Loyalty Points ---");
        customerMap.values().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints()))
                .forEach(System.out::println);
    }

    private static void viewProducts() {
        System.out.println("\n--- Products Sorted by Price ---");
        sortedProducts.forEach(System.out::println);
    }
}