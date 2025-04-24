package threadgame;

public class TimerThread implements Runnable {
    private final int seconds;
    private final Thread gameThread;

    public TimerThread(int seconds, Thread gameThread) {
        this.seconds = seconds;
        this.gameThread = gameThread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(seconds * 1000);
            if (gameThread.isAlive()) {
                System.out.println("\n⏰ Time's up!");
                gameThread.interrupt();
            }
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}