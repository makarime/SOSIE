package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerDuration {
    private final long time;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public TimerDuration() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - time;
    }

    public void show(String text) {
        System.out.println(String.format("[%s] %s: %dms", sdf.format(new Date()), text, getTime()));
    }
}
