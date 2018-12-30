package gup.replay;

public class Counter extends Thread {
    public static int counter;

    public void run() {
        synchronized (Replay.lock) {
            while (counter < Replay.record.getmovementRecord().size() - 1) {
                counter++;
                try {
                    Replay.lock.notifyAll();
                    Replay.lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

