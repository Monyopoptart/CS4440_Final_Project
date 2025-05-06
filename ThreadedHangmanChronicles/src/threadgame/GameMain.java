package threadgame;

//import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) throws InterruptedException {
        String word = WordLoader.loadWord();

        // Start animation thread
        HangmanAnimator animator = new HangmanAnimator(word);
        Thread animationThread = new Thread(animator);
        animationThread.start();
        
        // Start game thread
        //HangmanGameThread game = new HangmanGameThread(word, animator);
        //Thread gameThread = new Thread(game);
        //gameThread.start();
        PalindromeChecker checker = new PalindromeChecker(word);
        // Start timer thread (optional: stop game after time)
        TimerThread timer = new TimerThread(120, animationThread); // 2 minutes
        Thread timerThread = new Thread(timer);
        timerThread.start();
        
        
        Thread palindromeThread = new Thread(checker);
        palindromeThread.start();
        //Thread.sleep(timer.returnDisplaySeconds()*1000);
        palindromeThread.join();
        // Wait for game to finish
        //gameThread.join();
        //animator.stop(); // stop animation
        //animationThread.join();

        // Palindrome check after game ends
    }
}