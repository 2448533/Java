import java.util.LinkedList;
import java.util.Queue;


class ETEpracticaltest2 {

    static final int queSize = 10;
    static final int totalorder = 15;

    public static void main(String[] args)
    {
        Queue<String> orderQue = new LinkedList<>();

        Thread chef = new Thread(new Chef(orderQue,totalorder));
        Thread waiter = new Thread(new Waiter(orderQue,totalorder));

        chef.start();
        waiter.start();

        System.out.println("All orders processed");
    }

}

class Chef implements Runnable
{
    Queue<String> orderQue;
    int totalOrders;
    int prepared = 0;

    public Chef(Queue<String> orderQue, int totalOrders) {
        this.orderQue = orderQue;
        this.totalOrders = totalOrders;
    }

    public void run() {
       
            while (prepared < totalOrders) {

                    String order = "Order " + (prepared + 1);
                    orderQue.add(order);
                    System.out.println("Chef prepared: " + order);
                    prepared++;
                    notifyAll();
                
            }
    }

}

class Waiter implements Runnable
{
    Queue<String> orderQueue;
    int totalOrders;
    delivered = 0;

    public Waiter(Queue<String> orderQueue,int totalOrders) {
        this.orderQueue = orderQueue;
        this.totalOrders = totalOrders;
    }

    public void run() {
        
                    while (orderQueue.isEmpty()) {
                        wait();
                    }

                    String order = orderQueue.poll();
                    System.out.println("Waiter delivered: " + order);
                    delivered++;
                    notifyAll();
    }
}












