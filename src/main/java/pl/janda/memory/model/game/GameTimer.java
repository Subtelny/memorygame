package pl.janda.memory.model.game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class GameTimer {

    private Timer timerTask;

    private int counter;

    private Consumer<Integer> listener;

    public boolean isRunning() {
        return timerTask != null;
    }

    public void setListener(Consumer<Integer> listener) {
        if (listener == null) {
            throw new IllegalStateException("Listener cannot be null");
        }
        this.listener = listener;
    }

    public void start() {
        if (isRunning()) {
            throw new IllegalStateException("Timer is already running");
        }
        if (listener == null) {
            throw new IllegalStateException("Listener is not initialized yet");
        }
        timerTask = new Timer();
        timerTask.schedule(new TimerTask() {
            @Override
            public void run() {
                counter += 1;
                listener.accept(counter);
            }
        }, 0, 1000);
    }

    public void stop() {
        if (isRunning()) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public int getCounter() {
        return counter;
    }
}
