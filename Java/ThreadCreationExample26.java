// EXERCISE 26: Thread Creation
// Objective: Implement basic multithreading by implementing the Runnable interface.

class MessagePrinter implements Runnable {
    private String threadName;

    public MessagePrinter(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        System.out.println("🚀 " + threadName + " has started running.");

        try {
            // Print a message 5 times
            for (int i = 1; i <= 5; i++) {
                System.out.println("[" + threadName + "] Printing message #" + i);
                
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            System.out.println("⚠️ " + threadName + " was interrupted.");
        }

        System.out.println("🏁 " + threadName + " has finished executing.");
    }
}

public class ThreadCreationExample26 {
    public static void main(String[] args) {
        System.out.println("=== Multithreading Demonstration ===");
        System.out.println("Main thread is setting up worker tasks...\n");

        MessagePrinter taskA = new MessagePrinter("Thread-Alpha");
        MessagePrinter taskB = new MessagePrinter("Thread-Beta");

        Thread thread1 = new Thread(taskA);
        Thread thread2 = new Thread(taskB);

        thread1.start();
        thread2.start();

        System.out.println("📢 Main thread has finished spawning workers and is resting.");
    }
}