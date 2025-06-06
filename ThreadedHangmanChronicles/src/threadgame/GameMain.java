package threadgame;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
        // Start timer thread (optional: stop game after time)
        TimerThread timer = new TimerThread(120, animationThread); // 2 minutes
        Thread timerThread = new Thread(timer);
        timerThread.start();
        
        
        // Palindrome check after game ends
        PalindromeChecker checker = new PalindromeChecker(word);
        Thread palindromeThread = new Thread(checker);
        while(animationThread.isAlive()){

            if(!animationThread.isAlive()){
                //System.out.println("\nTesting the Palindrome Thread:");
                palindromeThread.start();
                //System.out.println("\nJoining the PalindromeThread");
                palindromeThread.join();
                
            }
            else{
                
            }
        }
            //Thread.sleep(timer.returnDisplaySeconds()*1000);
        // Wait for game to finish
        //gameThread.join();
        //animator.stop(); // stop animation
        //animationThread.join();

    }
}