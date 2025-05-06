package threadgame;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HangmanAnimator implements Runnable {
    private int state = 0;
    private boolean running = true;

    private String word;
    private final Set<Character> guessed = new HashSet<>();
    private int wrongGuesses = 0;
    //private HangmanAnimator animator;

    public HangmanAnimator(String word){
        this.word = word.toLowerCase();
    }

    /*public HangmanAnimator(String word, HangmanAnimator animatorpass) {
        this.word = word.toLowerCase();
        this.animator = animatorpass;
    }*/

    private final String[] stages = {
        "",
        "  O",
        "  O\n  |",
        "  O\n /|",
        "  O\n /|\\",
        "  O\n /|\\\n /",
        "  O\n /|\\\n / \\"
    };

    public void setState(int state) {
        this.state = Math.min(state, stages.length - 1);
    }

    public void stopThread() {
        running = false;
    }
    public void displayguesses(){
        System.out.println("Guessed: ");
        for (Character character : guessed) {
            System.out.print(character+ " ");
        }
        System.out.println("\n");
    }
    

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                System.out.println("\033[H\033[2J");//Reset the text box
                if (wrongGuesses < 6) {

                    System.out.println("\nHangman:"); //Display Hangman
                    System.out.println(stages[state]); // Display the status of the hang man
                    displayWord(); // display blank words and correct letters
                    displayguesses(); // Display guessed letters
                    System.out.print("Guess a letter: \n");
                    String input = scanner.nextLine().toLowerCase();
                    
                    if (input.length() != 1 || !Character.isLetter(input.charAt(0))) { //Ensures guess is only 1 character long
                        System.out.println("Please enter a single letter.");
                        
                    }
   
                    char guess = input.charAt(0); //Checks if input has been guessed already
                    if (guessed.contains(guess)) {
                        System.out.println("Already guessed.");
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException ignored){}
                        continue;
                    }
   
                    guessed.add(guess);    // Adds the input to the guess hash table if it doesn't exist
                    if (!word.contains(String.valueOf(guess))) {
                        wrongGuesses++;
                        this.setState(wrongGuesses);
                        System.out.println("Wrong guess! Attempts left: " + (6 - wrongGuesses));
                    }
   
                    if (word.chars().allMatch(c -> guessed.contains((char) c))) { //Guesses have been checked
                        System.out.println("You guessed it! The word was: " + word);
                        stopThread();
                    }
                }
                if (wrongGuesses == 6){ //Too many wrong guesses
                    System.out.println("Game Over! The word was: " + word);
                    stopThread();
                }
                try {
                    Thread.sleep(1000);
                    
                } catch (InterruptedException ignored) {}
            }
        }
    }

    private void displayWord() {
        StringBuilder display = new StringBuilder();
        for (char c : word.toCharArray()) {
            display.append(guessed.contains(c) ? c : "_").append(" ");
        }
        System.out.println(display.toString().trim());
    }
}