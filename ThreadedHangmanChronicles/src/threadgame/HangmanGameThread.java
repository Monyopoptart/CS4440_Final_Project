package threadgame;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HangmanGameThread implements Runnable {
    private final String word;
    private final Set<Character> guessed = new HashSet<>();
    private int wrongGuesses = 0;
    private final HangmanAnimator animator;

    public HangmanGameThread(String word, HangmanAnimator animator) {
        this.word = word.toLowerCase();
        this.animator = animator;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (wrongGuesses < 6) {
            displayWord();
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
                continue;
            }

            char guess = input.charAt(0);
            if (guessed.contains(guess)) {
                System.out.println("Already guessed.");
                continue;
            }

            guessed.add(guess);
            if (!word.contains(String.valueOf(guess))) {
                wrongGuesses++;
                animator.setState(wrongGuesses);
                System.out.println("Wrong guess! Attempts left: " + (6 - wrongGuesses));
            }

            if (word.chars().allMatch(c -> guessed.contains((char) c))) {
                System.out.println("🎉 You guessed it! The word was: " + word);
                return;
            }
        }

        System.out.println("💀 Game Over! The word was: " + word);
    }

    private void displayWord() {
        StringBuilder display = new StringBuilder();
        for (char c : word.toCharArray()) {
            display.append(guessed.contains(c) ? c : "_").append(" ");
        }
        System.out.println(display.toString().trim());
    }
}