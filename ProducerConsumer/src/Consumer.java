public class Consumer implements Runnable {
    private final Buffer buffer;
    private final MainForm mainForm;

    public Consumer(Buffer buffer, MainForm mainForm) {
        this.buffer = buffer;
        this.mainForm = mainForm;
    }

    public void run() {
        Integer message;
        int i = 0;
        boolean awokenByPauseButton;
        while (true) {
            // nap for awhile
            awokenByPauseButton = SleepUtilities.nap(2);
            // consume an item from the buffer
            message = (Integer) buffer.remove();
            mainForm.setConsumerValue(i++, message);
            message = (Integer) buffer.remove();
            mainForm.setConsumerValue(i++, message);
            message = (Integer) buffer.remove();
            mainForm.setConsumerValue(i++, message);
            i %= 3;

            if (awokenByPauseButton) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        System.out.println("*Consumer gets interrupted*");
                        System.out.println("Consumer: Oh, I'm awake now :)");
                    }
                }
            }
        }
    }
}
