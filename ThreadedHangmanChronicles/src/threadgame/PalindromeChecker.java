package threadgame;

public class PalindromeChecker implements Runnable {
    private final String word;

    public PalindromeChecker(String word) {
        this.word = word;
    }

    @Override
    public void run() {
        String reversed = new StringBuilder(word).reverse().toString();
        if (word.equalsIgnoreCase(reversed)) {
            System.out.println("\n\nThe word is a palindrome!");
        } else {
            System.out.println("\n\nThe word is not a palindrome.");
        }
    }
}