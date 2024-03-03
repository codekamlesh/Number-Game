import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 5;
        int score = 0;
        int rounds = 0;
        boolean playAgain = true;

        System.out.println("Welcome to Guess the Number!");

        while (playAgain) {
            rounds++;
            System.out.println("\nRound " + rounds + ":");

            // Prompt user for range of numbers
            System.out.print("Enter the minimum number of the range: ");
            minRange = scanner.nextInt();
            System.out.print("Enter the maximum number of the range: ");
            maxRange = scanner.nextInt();

            // Prompt user for number of attempts
            System.out.print("Enter the number of attempts: ");
            attemptsLimit = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Game logic
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("A random number between " + minRange + " and " + maxRange + " has been generated.");
            System.out.println("You have " + attemptsLimit + " attempts to guess it.");

            int roundScore = playGame(randomNumber, attemptsLimit, scanner);
            score += roundScore;

            System.out.println("Round " + rounds + " score: " + roundScore);
            System.out.println("Total score: " + score);

            // Ask user if they want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainStr = scanner.nextLine();
            playAgain = playAgainStr.equalsIgnoreCase("yes");
        }

        // Display final statistics
        System.out.println("\nThank you for playing!");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total score: " + score);

        scanner.close();
    }

    private int playGame(int randomNumber, int attemptsLimit, Scanner scanner) {
        for (int attempt = 1; attempt <= attemptsLimit; attempt++) {
            System.out.print("Attempt " + attempt + ": Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == randomNumber) {
                System.out.println("Congratulations! Your guess is correct.");
                return attemptsLimit - attempt + 1;
            } else if (guess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

            if (attempt == attemptsLimit) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }
        }
        return 0;
    }
}
