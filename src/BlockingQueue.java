import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int size;

    public BlockingQueue() {
        this.queue = new LinkedList<>();
        this.size = 3;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == size) {
            System.out.println("Queue is full. Waiting for space...");
            wait();
        }
        queue.add(item);
        System.out.println(item + " was added");
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Waiting for elements...");
            wait();
        }
        T item = queue.remove();
        System.out.println(item + " was removed");
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
