package lab5;


import java.util.Timer;
import java.util.TimerTask;

public class Device {
	private long startTime;
    private long stopTime;
    private long timeLeft;
    private long currTime;
    private Timer timer;

    private boolean isTurnedOn = false;
    private boolean currTimerMode = false;

    String name;
    EventListener eventListener;

    public Device(EventListener eventListener, String name){
        this.eventListener = eventListener;
        this.name = name;
    }

    public boolean turnOn() {
        if (!isTurnedOn) {
            isTurnedOn = true;
            eventListener.update(name + " has been turned on");
            return true;
        }
        return false;
    }

    public boolean turnOff() {
        if (isTurnedOn) {
            isTurnedOn = false;
            eventListener.update(name + " has been turned off");
            return true;
        }
        return false;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public String getName() {
        return name;
    }

    public boolean isTimerModeOn() {
        return currTimerMode;
    }

    public boolean startTimer(final boolean isOnTimer, long seconds) {

        if (isTurnedOn && isOnTimer || !isTurnedOn && !isOnTimer) {
            return false;
        }

        updateTimeLeft();
        currTimerMode = isOnTimer;
        startTime = System.currentTimeMillis();
        stopTime = startTime + seconds * 1000;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTimeLeft();
                if (timeLeft <= 0) {
                    stopTimer();
                    if (isOnTimer) {
                        turnOn();
                    } else {
                        turnOff();
                    }
                }
            }
        }, 0, 1000);

        return isTimerOn();
    }

    public boolean stopTimer() {
        if (timer == null) {
            return false;
        }
        startTime = stopTime = currTime = timeLeft = 0;
        timer.cancel();

        return true;
    }

    public boolean isTimerOn() {
        return timeLeft != 0;
    }

    private void updateTimeLeft() {
        currTime = System.currentTimeMillis();
        timeLeft = stopTime - currTime;
    }

    public long getTimeLeft() {
        return timeLeft <= 0 ? 0 :
                timeLeft % 1000 == 0 ? timeLeft :
                        timeLeft - (timeLeft % 1000) + 1000;
    }
}

