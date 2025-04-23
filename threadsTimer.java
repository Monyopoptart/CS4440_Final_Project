class CountdownTimer implements Runnable {
    private int seconds;

    public CountdownTimer(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        while (seconds > 0) {
            System.out.println("Time remaining: " + seconds + " seconds");
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted");
                return;
            }
            seconds--;
        }
        System.out.println("Countdown complete!");
    }
}

public class threadsTimer{
    public static void main(String[] args) {
        int duration = 60; // Countdown duration in seconds

        // Create a thread for the countdown timer
        Thread timerThread = new Thread(new CountdownTimer(duration));

        // Start the timer thread
        timerThread.start();

        System.out.println("The timer has started!");
    }
}