package threadgame;

public class TimerThread implements Runnable {
    private final int seconds;
    private final Thread gameThread;
    private int displayseconds;

    public TimerThread(int seconds){
        this.seconds = seconds;
        this.gameThread = new Thread();
    }
    public TimerThread(int seconds, Thread gameThread) {
        this.seconds = seconds;
        this.gameThread = gameThread;
    }
    public int returnDisplaySeconds(){
        return displayseconds;
    }
    @Override
    public void run() {
        displayseconds = seconds;
        try {
            while(displayseconds>0){
                Thread.sleep(1000);
                displayseconds--;
                System.out.print("\rTime Remaining: " + displayseconds + " ");
                if (!gameThread.isAlive()){
                    displayseconds = 0;
                }

            }
            //Thread.sleep(seconds * 1000); //Seconds variable is convereted to actual seconds, then thread sleeps for that long.
            if (gameThread.isAlive()) {
                System.out.println("\n⏰ Time's up!");
                gameThread.interrupt();//Kills the game. With a gun
            }
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}