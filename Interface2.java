import java.util.Scanner;

// Interface that defines the method for calculating trapped water
interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

// Abstract class implementing WaterConservationSystem
abstract class RainySeasonConservation implements WaterConservationSystem {
    // This class can provide shared functionality, but for now, we leave it abstract.
}

// Concrete class implementing the method to calculate trapped water
class CityBlockConservation extends RainySeasonConservation {

    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        if (blockHeights == null || blockHeights.length <= 2) {
            return 0; // No space to trap water between blocks
        }
        
        int n = blockHeights.length;
        int totalWater = 0;
        
        // Create two arrays to store the maximum heights from the left and right
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Fill leftMax array where leftMax[i] is the highest bar to the left of i including i
        leftMax[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
        }
        
        // Fill rightMax array where rightMax[i] is the highest bar to the right of i including i
        rightMax[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
        }
        
        // Calculate the trapped water by finding the minimum of leftMax[i] and rightMax[i] minus the height of the block
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - blockHeights[i];
        }
        
        return totalWater;
    }
}

// Main class to test the functionality
public class Interface2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take the input of the block heights
        System.out.println("Enter the number of blocks:");
        int n = scanner.nextInt();
        
        int[] blockHeights = new int[n];
        
        System.out.println("Enter the heights of the blocks:");
        for (int i = 0; i < n; i++) {
            blockHeights[i] = scanner.nextInt();
        }
        
        // Create an instance of CityBlockConservation and calculate the trapped water
        CityBlockConservation conservation = new CityBlockConservation();
        int totalTrappedWater = conservation.calculateTrappedWater(blockHeights);
        
        System.out.println("Total trapped water: " + totalTrappedWater);
        
        scanner.close();
    }
}