package utils;

public class TimerDuration {
    private final long time;

    public TimerDuration() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - time;
    }

    public void show(String text) {
        System.out.println(text + ": " + getTime()+"ms");
    }
}
