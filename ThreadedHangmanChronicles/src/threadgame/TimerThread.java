package threadgame;

public class TimerThread implements Runnable {
    private final int seconds;
    private final Thread gameThread;

    public TimerThread(int seconds){
        this.seconds = seconds;
        this.gameThread = new Thread();
    }
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
                gameThread.stop();//Kills the game. With a gun
            }
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}