import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

class CountdownTimer implements Runnable {
    private int seconds;
    private int originalSeconds;
    private AtomicBoolean reset = new AtomicBoolean(false);
    private volatile boolean running = true;

    public CountdownTimer(int seconds) {
        this.seconds = seconds;
        this.originalSeconds = seconds;
    }

    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();
            long endTime = startTime + (seconds * 1000);
            
            while (System.currentTimeMillis() < endTime && running) {
                if (reset.getAndSet(false)) {
                    System.out.println("Timer reset!");
                    seconds = originalSeconds;
                    startTime = System.currentTimeMillis();
                    endTime = startTime + (seconds * 1000);
                }
                
                long remainingMillis = endTime - System.currentTimeMillis();
                int remainingSeconds = (int)(remainingMillis / 1000);
                
                if (remainingSeconds % 10 == 0 && remainingSeconds > 0) {
                    System.out.println("Time remaining: " + remainingSeconds + " seconds");
                }
                
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    System.out.println("Timer interrupted");
                    return;
                }
            }
            
            if (running) {
                System.out.println("Countdown complete!");
                running = false;
                System.exit(0); 
            }
        }
    }

    public void resetTimer() {
        reset.set(true);
    }
}

public class threadsTimer {
    public static void main(String[] args) {
        int duration = 60;
        CountdownTimer countdownTimer = new CountdownTimer(duration);

        Thread timerThread = new Thread(countdownTimer);

        timerThread.start();

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter any character to reset the timer:");
                String input = scanner.nextLine();
                countdownTimer.resetTimer();
            }
        });

        inputThread.setDaemon(true); 
        inputThread.start();

        System.out.println("The timer has started!");
    }
}
