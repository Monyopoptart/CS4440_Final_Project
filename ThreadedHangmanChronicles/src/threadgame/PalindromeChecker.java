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
            System.out.println("\nThe word is a palindrome!\n");
        } else {
            System.out.println("\nThe word is not a palindrome.\n");
        }
    }
}