public class BoundedBuffer implements Buffer {
    private static final int BUFFER_SIZE = 7;
    private final MainForm mainForm;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;
    public Object[] buffer;
    private int in, out;

    // Continued on next Slide
    public BoundedBuffer(MainForm mainForm) {
        // buffer is initially empty
        in = 0;
        out = 0;
        buffer = new Object[BUFFER_SIZE];
        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
        this.mainForm = mainForm;
    }


    public void insert(Object item) {
        empty.acquire();
        mutex.acquire();
        // add an item to the buffer
        buffer[in] = item;
        mainForm.setBufferValue(in, item);
        in = (in + 1) % BUFFER_SIZE;
        mutex.release();
        full.release();
        System.out.println("Producer dodao ");
    }

    public Object remove() {
        full.acquire();
        mutex.acquire();
        // remove an item from the buffer
        Object item = buffer[out];
        mainForm.removeBufferValue(out);
        out = (out + 1) % BUFFER_SIZE;
        mutex.release();
        empty.release();
        System.out.println("Consumer uzeo ");
        return item;
    }
}
