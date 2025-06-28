import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int MAX_ATTEMPTS = 7; // Max attempts per round
        final int LOWER_BOUND = 1;
        final int UPPER_BOUND = 100;

        int roundsPlayed = 0;
        int roundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it correctly.");

        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed++;
            int targetNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + roundsPlayed + " ---");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");
                int guess;

                // Validate input
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                    if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                        System.out.println("Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                        continue;
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                attemptsLeft--;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The correct number was: " + targetNumber);
            }

            // Ask if user wants to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();

            playAgain = response.equals("yes") || response.equals("y");
        }

        // Display final score
        System.out.println("\nGame Over!");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}
