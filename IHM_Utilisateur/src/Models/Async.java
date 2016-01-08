package Models;


public class Async {
    public static void execute(Runnable runnable) {
        (new Thread(runnable)).start();
    }
}
