import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CoinChangeMultithreaded {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Input number of coins and sum
        System.out.println("Enter the number of coins (N): ");
        int N = scanner.nextInt();

        System.out.println("Enter the sum: ");
        int sum = scanner.nextInt();

        System.out.println("Enter the coin denominations: ");
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = scanner.nextInt();
        }

        // Multithreaded computation
        List<List<Integer>> result = getAllCombinationsMultithreaded(coins, sum);

        // Output results
        System.out.println("Number of ways to make the sum: " + result.size());
        System.out.println("Possible combinations: ");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }

        scanner.close();
    }

    // Function to get all combinations using multithreading
    private static List<List<Integer>> getAllCombinationsMultithreaded(int[] coins, int target) throws Exception {
        HashSet<List<Integer>> uniqueCombinations = new HashSet<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<List<List<Integer>>>> futures = new ArrayList<>();

        // Split computation by assigning a thread to each coin
        for (int i = 0; i < coins.length; i++) {
            final int startCoinIndex = i;
            futures.add(executor.submit(() -> getCombinations(coins, target, startCoinIndex)));
        }

        // Collect results from threads
        for (Future<List<List<Integer>>> future : futures) {
            uniqueCombinations.addAll(future.get());
        }

        executor.shutdown();
        return new ArrayList<>(uniqueCombinations);
    }

    // Function to find combinations for a given starting coin index
    private static List<List<Integer>> getCombinations(int[] coins, int target, int startCoinIndex) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(coins, target, new ArrayList<>(), result, startCoinIndex);
        return result;
    }

    // Recursive helper function to find combinations
    private static void findCombinations(int[] coins, int remaining, List<Integer> current, List<List<Integer>> result, int startCoinIndex) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = startCoinIndex; i < coins.length; i++) { // Ensure combinations are sorted
            if (remaining - coins[i] >= 0) {
                current.add(coins[i]);
                findCombinations(coins, remaining - coins[i], current, result, i);
                current.remove(current.size() - 1);
            }
        }
    }
}