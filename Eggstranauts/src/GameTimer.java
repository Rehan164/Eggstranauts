import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    Timer timer = new Timer();
    TimerCallback callback;
    int secondDuration;
    int secondsPassed = 0;

    TimerTask task = new TimerTask() {
        public void run() {
            secondsPassed++;
            System.out.println(secondsPassed);

            if (secondsPassed >= secondDuration) {
                callback.onTimerEnd();
                timer.cancel();
            }
        }
    };

    public void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public GameTimer(int secondDuration, TimerCallback callback) {
        this.secondDuration = secondDuration;
        this.callback = callback;
    }

    public int secondsElapsed() {
        return secondsPassed;
    }
}