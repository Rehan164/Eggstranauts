import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    Timer timer = new Timer();
    Game game;
    int secondDuration;
    int secondsPassed = 0;

    TimerTask task = new TimerTask() {
        public void run() {
            secondsPassed++;

            if (secondsPassed >= secondDuration) {
                game.onTimerEnd();
                timer.cancel();
            }
        }
    };

    public void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public GameTimer(int secondDuration, Game game) {
        this.secondDuration = secondDuration;
        this.game = game;
    }

    public int secondsElapsed() {
        return secondsPassed;
    }
}