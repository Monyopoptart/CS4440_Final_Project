package threadgame;

public class HangmanAnimator implements Runnable {
    private int state = 0;
    private boolean running = true;

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

    // public String getCurrentState() {
    //     return stages[state];
    // }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("\nHangman:");
            System.out.println(stages[state]);
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ignored) {}
        }
    }
}