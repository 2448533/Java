import java.util.*;

public class Numbers {
    // Static variable to store the input array of N numbers
    static int[] numbers;

    // Static method to find and print the top K frequent numbers
    public static void findTopKFrequent(int K) {
        // Map to store the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Populate the frequency map
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a list from the map and sort it by frequency and then by value
        List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
        Collections.sort(frequencyList, (a, b) -> {
            int freqComparison = b.getValue().compareTo(a.getValue()); // Sort by frequency (descending)
            if (freqComparison == 0) {
                return b.getKey().compareTo(a.getKey()); // Sort by value (descending)
            }
            return freqComparison;
        });

        // Get the top K numbers and print them
        for (int i = 0; i < K; i++) {
            System.out.print(frequencyList.get(i).getKey() + " ");
        }
        System.out.println(); // New line after output
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        numbers = new int[size];

        // Input the elements of the array
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Input the value of K
        System.out.print("Enter the value of K: ");
        int K = scanner.nextInt();

        // Call the method to find and print the top K frequent numbers
        System.out.println("Top " + K + " frequent numbers:");
        findTopKFrequent(K);

        scanner.close();
    }
}