public class ShareTrader {
    // Static variable to store the maximum profit
    private static int maxProfit;

    // Static method to calculate the maximum profit with at most 2 transactions
    public static int findMaxProfit(int[] prices) {
        int n = prices.length;

        // Edge case: If there are fewer than 2 prices, no transactions can be made
        if (n == 0) return 0;

        // Arrays to store the maximum profit
        int[] leftProfit = new int[n];  // Profit from 1st transaction up to each day
        int[] rightProfit = new int[n]; // Profit from 2nd transaction after each day

        // Step 1: Calculate the maximum profit for the first transaction (left side)
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
        }

        // Step 2: Calculate the maximum profit for the second transaction (right side)
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
        }

        // Step 3: Combine both transactions to find the overall maximum profit
        maxProfit = 0;
        int bestBuy1 = 0, bestSell1 = 0, bestBuy2 = 0, bestSell2 = 0;  // To store best transactions

        for (int i = 0; i < n; i++) {
            if (leftProfit[i] + rightProfit[i] > maxProfit) {
                maxProfit = leftProfit[i] + rightProfit[i];
               
                // Find corresponding buy/sell points for the two transactions
                bestSell1 = i;
                bestBuy1 = findBestBuy(prices, 0, bestSell1, leftProfit[bestSell1]);

                bestBuy2 = i + 1;  // Start buying for the second transaction after the first sell
                bestSell2 = findBestSell(prices, bestBuy2, n - 1, rightProfit[bestBuy2]);
            }
        }

        System.out.println("Buy at " + prices[bestBuy1] + ", sell at " + prices[bestSell1]);
        if (bestBuy2 < n) {
            System.out.println("Buy at " + prices[bestBuy2] + ", sell at " + prices[bestSell2]);
        }
        return maxProfit;
    }

    // Helper method to find the best buy point for the first transaction
    private static int findBestBuy(int[] prices, int start, int end, int targetProfit) {
        int minPrice = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (prices[end] - prices[i] == targetProfit) {
                return i;
            }
        }
        return start;
    }

    // Helper method to find the best sell point for the second transaction
    private static int findBestSell(int[] prices, int start, int end, int targetProfit) {
        int maxPrice = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (prices[i] - prices[start] == targetProfit) {
                return i;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = {10, 22, 5, 75, 65, 80};
        System.out.println("Test Case 1 Max Profit: " + findMaxProfit(prices1));  // Output: 87
        System.out.println();

        // Test case 2
        int[] prices2 = {2, 30, 15, 10, 8, 25, 80};
        System.out.println("Test Case 2 Max Profit: " + findMaxProfit(prices2));  // Output: 100
    }
}
