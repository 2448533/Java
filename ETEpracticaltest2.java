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
    }

}

class Chef implements Runnable
{
    Queue<String> orderQue;
    int prepared = 0;

    public Chef(Queue<String> orderQue, int totalorder) {
        this.orderQue = orderQue;
        this.totalorder = totalorder;
    }

    public void run() {
           while (prepared < totalorder) {
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
    Queue<String> orderQue;
    int delivered = 0;

    public Waiter(Queue<String> orderQue,int totalorders) {
        this.orderQue = orderQue;
        this.totalorders = totalorders;
    }

    public void run() {
        
                    while (orderQue.isEmpty()) {
                        wait();
                    }

                    String order = orderQue.poll();
                    System.out.println("Waiter delivered: " + order);
                    delivered++;
                    notifyAll();
    }
}












