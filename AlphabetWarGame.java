import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlphabetWarGame {
    // Strengths for letters
    private Map<Character, Integer> leftStrengths;
    private Map<Character, Integer> rightStrengths;

    // Default Constructor
    public AlphabetWarGame() {
        this.leftStrengths = createDefaultLeftStrengths();
        this.rightStrengths = createDefaultRightStrengths();
    }

    // Parameterized Constructor for custom strengths
    public AlphabetWarGame(Map<Character, Integer> leftStrengths, Map<Character, Integer> rightStrengths) {
        this.leftStrengths = leftStrengths;
        this.rightStrengths = rightStrengths;
    }

    // Method to create default strengths for left-side letters
    private Map<Character, Integer> createDefaultLeftStrengths() {
        Map<Character, Integer> strengths = new HashMap<>();
        strengths.put('w', 4); // super strong
        strengths.put('p', 3); // quite strong
        strengths.put('b', 2); // okay strong
        strengths.put('s', 1); // kinda strong
        return strengths;
    }

    // Method to create default strengths for right-side letters
    private Map<Character, Integer> createDefaultRightStrengths() {
        Map<Character, Integer> strengths = new HashMap<>();
        strengths.put('m', 4); // super strong
        strengths.put('q', 3); // quite strong
        strengths.put('d', 2); // okay strong
        strengths.put('z', 1); // kinda strong
        return strengths;
    }

    // Method to determine the winner based on a single word
    public String alphabetWar(String word) {
        int leftScore = 0;
        int rightScore = 0;

        for (char ch : word.toCharArray()) {
            if (leftStrengths.containsKey(ch)) {
                leftScore += leftStrengths.get(ch);
            } else if (rightStrengths.containsKey(ch)) {
                rightScore += rightStrengths.get(ch);
            }
        }

        return determineWinner(leftScore, rightScore);
    }

    // Method to determine the winner based on separate left and right words
    public String alphabetWar(String leftWord, String rightWord) {
        return alphabetWar(leftWord + rightWord);
    }

    // Helper method to determine the winner based on scores
    private String determineWinner(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input custom strengths from user
        System.out.println("Do you want to use default strengths? (yes/no)");
        String choice = scanner.nextLine().trim().toLowerCase();

        AlphabetWarGame game;

        if (choice.equals("no")) {
            Map<Character, Integer> leftStrengths = new HashMap<>();
            Map<Character, Integer> rightStrengths = new HashMap<>();

            // Input custom strengths for left-side letters
            System.out.println("Enter strengths for left-side letters (e.g., w:4 p:3 b:2 s:1):");
            String[] leftInput = scanner.nextLine().split(" ");
            for (String s : leftInput) {
                char letter = s.charAt(0);
                int strength = Integer.parseInt(s.split(":")[1]);
                leftStrengths.put(letter, strength);
            }

            // Input custom strengths for right-side letters
            System.out.println("Enter strengths for right-side letters (e.g., m:4 q:3 d:2 z:1):");
            String[] rightInput = scanner.nextLine().split(" ");
            for (String s : rightInput) {
                char letter = s.charAt(0);
                int strength = Integer.parseInt(s.split(":")[1]);
                rightStrengths.put(letter, strength);
            }

            // Create the game instance with custom strengths
            game = new AlphabetWarGame(leftStrengths, rightStrengths);
        } else {
            // Create a game instance with default strengths
            game = new AlphabetWarGame();
        }

        // Play the game with user input
        System.out.println("Enter a word for the Alphabet War:");
        String word = scanner.nextLine();
        System.out.println(game.alphabetWar(word));

        // Example of method overloading with separate words
        System.out.println("Enter a left-side word:");
        String leftWord = scanner.nextLine();
        System.out.println("Enter a right-side word:");
        String rightWord = scanner.nextLine();
        System.out.println(game.alphabetWar(leftWord, rightWord));

        scanner.close();
    }
}