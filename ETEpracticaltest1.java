import java.util.Scanner;

//Interface that defines method to count pairs
interface Transaction {
    int cntpairs(int[] arr, int target);
}

//Transaction interface implementation
//Define the method to count pairs

class TransactionImpl implements Transaction {

    @Override
    public int cntpairs(int[] arr, int target) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    count++;
                }
            }
        }

        return count;
    }
}

public class ETEpracticaltest1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //size of the array
        System.out.print("Enter the number of items in the transaction: ");
        int n = scanner.nextInt();

        //store item prices
        int[] arr = new int[n];

        //array elements
        System.out.println("Enter the item prices:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        //target sum
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

       
        Transaction analyzer = new TransactionImpl();

        // count the pairs and display the result
        int result = analyzer.cntpairs(arr, target);
        System.out.println("Number of pairs that sum up to the target: " + result);

        scanner.close();
    }
}