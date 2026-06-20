// EXERCISE 41: Executor Service and Callable
// Objective: Efficiently manage a pool of threads and retrieve results concurrently using Futures.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 1. Define a Task that implements Callable instead of Runnable
// The generic type <Integer> specifies what data type this task will return when finished
class FactorialTask implements Callable<Integer> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    // 2. Implement the call() method (Notice it returns a value and can throw Exceptions!)
    @Override
    public Integer call() throws Exception {
        System.out.println("⚙️ [THREAD " + Thread.currentThread().getName() + "] Starting factorial calculation for: " + number);
        
        // Simulate a heavy computational delay (e.g., 1 second)
        Thread.sleep(1000); 

        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        System.out.println("✅ [THREAD " + Thread.currentThread().getName() + "] Finished calculating factorial for: " + number);
        return result;
    }
}

public class ExecutorServiceExample41 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== High-Performance ExecutorService Thread Pool ===");

        // 3. Prompt the user for parameters to make the execution dynamic
        System.out.print("Enter how many tasks you want to submit (e.g., 3): ");
        int taskCount = scanner.nextInt();

        // 4. Initialize a Fixed Thread Pool with a maximum of 2 concurrent worker threads
        // If we submit 3 or more tasks, the extra tasks will wait cleanly in a queue!
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Keep a collection of Future receipts so we can collect the results later
        List<Future<Integer>> futureReceipts = new ArrayList<>();

        System.out.println("\n🚀 Submitting tasks to the thread pool factory...");
        
        for (int i = 1; i <= taskCount; i++) {
            System.out.print("Enter target number for Task #" + i + " (e.g., 5): ");
            int inputNum = scanner.nextInt();

            // Create the callable task assignment
            Callable<Integer> task = new FactorialTask(inputNum);

            // 5. Submit the task to the pool. This returns a Future object instantly without blocking!
            Future<Integer> receipt = executor.submit(task);
            futureReceipts.add(receipt);
        }

        System.out.println("\n📢 All tasks submitted successfully! The main thread is free to do other operations.");
        System.out.println("⏳ Main thread is now going to fetch results (this will block until workers finish)...");

        // 6. Iterate through our receipts to claim the results
        System.out.println("\n=== Final Compilation Dashboard ===");
        for (int i = 0; i < futureReceipts.size(); i++) {
            Future<Integer> receipt = futureReceipts.get(i);
            try {
                // .get() blocks the current thread until the specific background task completes and returns its value!
                Integer finalAnswer = receipt.get();
                System.out.println("📊 Task #" + (i + 1) + " Result Compiled ➔ " + finalAnswer);
            } catch (Exception e) {
                System.out.println("❌ Task #" + (i + 1) + " failed with error: " + e.getMessage());
            }
        }

        // 7. CRITICAL: Always shut down the executor service, otherwise your program will never exit!
        System.out.println("\n🛑 Shutting down thread pool channels cleanly.");
        executor.shutdown();
    }
}