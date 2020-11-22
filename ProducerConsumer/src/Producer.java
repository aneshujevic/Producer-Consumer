public class Producer implements Runnable {
    private final BoundedBuffer buffer;
    private final MainForm mainForm;

    public Producer(BoundedBuffer buffer, MainForm mainForm) {
        this.buffer = buffer;
        this.mainForm = mainForm;
    }

    public void run() {
        int i = 0;
        boolean awokenByPauseButton = false;
        while (true) {
            mainForm.resetProducerValues();
            // nap for awhile
            // produce an item & enter it into the buffer
            buffer.insert(i);
            mainForm.setProducerValue(i % 5, i++);
            buffer.insert(i);
            mainForm.setProducerValue(i % 5, i++);
            buffer.insert(i);
            mainForm.setProducerValue(i % 5, i++);
            buffer.insert(i);
            mainForm.setProducerValue(i % 5, i++);
            buffer.insert(i);
            mainForm.setProducerValue(i % 5, i++);

            if (awokenByPauseButton) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        System.out.println("*Producer gets interrupted*");
                        System.out.println("Producer: Oh, I'm awake now :)");
                    }
                }
            }

            awokenByPauseButton = SleepUtilities.nap(5);
        }

    }
}