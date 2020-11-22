import javax.swing.*;
import java.awt.*;

public class Factory {
    public static void main(String[] args) {
        // create ui
        JFrame frame = new JFrame("Buffer");
        MainForm mainForm = new MainForm();
        frame.setContentPane(mainForm.containerPnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(532, 477));

        BoundedBuffer buffer = new BoundedBuffer(mainForm);
        // now create the producer and consumer threads
        Thread producer = new Thread(new Producer(buffer, mainForm));
        Thread consumer = new Thread(new Consumer(buffer, mainForm));
        mainForm.setProducerThread(producer);
        mainForm.setConsumerThread(consumer);
    }
}
