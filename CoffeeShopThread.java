class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

class CoffeeShop {
    private final int maxCapacity = 3;
    private int coffeeCount = 0;

    public synchronized void prepareCoffee(String baristaName) throws InterruptedException {
        while (coffeeCount >= maxCapacity) {
            System.out.println(baristaName + " is waiting. Counter is full.");
            wait(); // Barista waits if the counter is full
        }
        coffeeCount++;
        System.out.println(baristaName + " prepared coffee. Counter: " + coffeeCount);
        notifyAll(); // Notify consumers or reviewer
    }

    public synchronized void pickUpCoffee(String customerName, int quantity) throws InterruptedException, CounterEmptyException {
        while (coffeeCount < quantity) {
            System.out.println(customerName + " is waiting. Not enough coffee.");
            wait(); // Customer waits if there are not enough coffees
        }
        coffeeCount -= quantity;
        System.out.println(customerName + " picked up coffee. Counter: " + coffeeCount);
        notifyAll(); // Notify baristas or reviewer
    }

    public synchronized void sampleCoffee(String reviewerName) throws InterruptedException, CounterEmptyException {
        while (coffeeCount == 0) {
            System.out.println(reviewerName + " is waiting. Counter is empty.");
            wait(); // Reviewer waits if there are no coffees
        }
        coffeeCount--;
        System.out.println(reviewerName + " sampled coffee. Counter: " + coffeeCount);
        notifyAll(); // Notify baristas or customers
    }
}

class Barista implements Runnable {
    private final CoffeeShop shop;
    private final String name;
    private final int coffeesToPrepare;

    public Barista(CoffeeShop shop, String name, int coffeesToPrepare) {
        this.shop = shop;
        this.name = name;
        this.coffeesToPrepare = coffeesToPrepare;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPrepare; i++) {
                shop.prepareCoffee(name);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer implements Runnable {
    private final CoffeeShop shop;
    private final String name;
    private final int coffeesToPickUp;

    public Customer(CoffeeShop shop, String name, int coffeesToPickUp) {
        this.shop = shop;
        this.name = name;
        this.coffeesToPickUp = coffeesToPickUp;
    }

    @Override
    public void run() {
        try {
            shop.pickUpCoffee(name, coffeesToPickUp);
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println(name + " couldn't pick up coffee: " + e.getMessage());
        }
    }
}

class Reviewer implements Runnable {
    private final CoffeeShop shop;
    private final String name;

    public Reviewer(CoffeeShop shop, String name) {
        this.shop = shop;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            shop.sampleCoffee(name);
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println(name + " couldn't sample coffee: " + e.getMessage());
        }
    }
}

public class CoffeeShopThread {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();

        // Create Baristas
        Thread barista1 = new Thread(new Barista(shop, "Barista 1", 2));
        Thread barista2 = new Thread(new Barista(shop, "Barista 2", 3));

        // Create Customers
        Thread customer1 = new Thread(new Customer(shop, "Customer 1", 1));
        Thread customer2 = new Thread(new Customer(shop, "Customer 2", 2));
        Thread customer3 = new Thread(new Customer(shop, "Customer 3", 1));

        // Create Reviewer
        Thread reviewer = new Thread(new Reviewer(shop, "Coffee Reviewer"));

        // Start threads
        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();
    }
}