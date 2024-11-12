import java.util.Scanner;

abstract class Robber {
    // Abstract method that must be implemented by subclasses
    public abstract void RowHouses(int[] rowHouse);
    public abstract void RoundHouses(int[] roundHouse);
    public abstract void SquareHouse(int[] squareHouse);
    public abstract void MultiHouseBuilding(int[]... buildings);

    // Default method
    public void MachineLearning() {
        System.out.println("I love MachineLearning");
    }

    // Method in the abstract class
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    // Utility function to find max amount robbed without robbing adjacent houses
    protected int robLineHouses(int[] houses) {
        if (houses == null || houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        int prev1 = 0, prev2 = 0;
        for (int money : houses) {
            int temp = prev1;
            prev1 = Math.max(prev2 + money, prev1);
            prev2 = temp;
        }
        return prev1;
    }
}

class JAVAProfessionalRobber extends Robber {
    // RowHouses method
    public void RowHouses(int[] rowHouse) {
        System.out.println("Maximum amount robbed from Row Houses: " + robLineHouses(rowHouse));
    }

    // RoundHouses method
    public void RoundHouses(int[] roundHouse) {
        if (roundHouse.length == 1) {
            System.out.println("Maximum amount robbed from Round Houses: " + roundHouse[0]);
            return;
        }
        int maxRob = Math.max(
            robLineHouses(java.util.Arrays.copyOfRange(roundHouse, 0, roundHouse.length - 1)),
            robLineHouses(java.util.Arrays.copyOfRange(roundHouse, 1, roundHouse.length))
        );
        System.out.println("Maximum amount robbed from Round Houses: " + maxRob);
    }

    // SquareHouse method
    public void SquareHouse(int[] squareHouse) {
        System.out.println("Maximum amount robbed from Square Houses: " + robLineHouses(squareHouse));
    }

    // MultiHouseBuilding method
    public void MultiHouseBuilding(int[]... buildings) {
        int totalMax = 0;
        for (int[] building : buildings) {
            totalMax += robLineHouses(building);
        }
        System.out.println("Maximum amount robbed from Multi-Type Building: " + totalMax);
    }
}

public class RobberTest {
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        robber.RobbingClass();
        robber.MachineLearning();

        Scanner scanner = new Scanner(System.in);
        
        // Input for RowHouses
        System.out.print("Enter Row Houses money values: ");
        int[] rowHouses = parseInput(scanner.nextLine());
        robber.RowHouses(rowHouses);

        // Input for RoundHouses
        System.out.print("Enter Round Houses money values: ");
        int[] roundHouses = parseInput(scanner.nextLine());
        robber.RoundHouses(roundHouses);

        // Input for SquareHouse
        System.out.print("Enter Square Houses money values: ");
        int[] squareHouses = parseInput(scanner.nextLine());
        robber.SquareHouse(squareHouses);

        // Input for MultiHouseBuilding
        System.out.print("Enter Multi House Buildings money values (separate each row by ';'): ");
        String[] multiHouseInputs = scanner.nextLine().split(";");
        int[][] multiHouseBuildings = new int[multiHouseInputs.length][];
        for (int i = 0; i < multiHouseInputs.length; i++) {
            multiHouseBuildings[i] = parseInput(multiHouseInputs[i]);
        }
        robber.MultiHouseBuilding(multiHouseBuildings);
        
        scanner.close();
    }

    // Helper method to parse input line into integer array
    private static int[] parseInput(String input) {
        String[] tokens = input.trim().split("\\s+");
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            result[i] = Integer.parseInt(tokens[i]);
        }
        return result;
    }
}
