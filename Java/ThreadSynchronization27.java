// EXERCISE 27: Thread Synchronization
// Objective: Prevent race conditions on shared resources using synchronized methods.

class BankAccount {
    private int balance = 100; 

    public synchronized void withdraw(String threadName, int amount) {
        System.out.println("💳 " + threadName + " is attempting to withdraw $" + amount);

        if (balance >= amount) {
            System.out.println("✔️ " + threadName + " checked balance. Sufficent funds available.");
            
            try { Thread.sleep(200); } catch (InterruptedException e) {}

            balance -= amount;
            System.out.println("💰 " + threadName + " successfully withdrew $" + amount);
            System.out.println("📉 Remaining Balance: $" + balance + "\n");
        } else {
            System.out.println("❌ " + threadName + " failed to withdraw. Insufficient balance! (Current: $" + balance + ")\n");
        }
    }
}

class WithdrawalTask implements Runnable {
    private BankAccount account;
    private String name;

    public WithdrawalTask(BankAccount account, String name) {
        this.account = account;
        this.name = name;
    }

    @Override
    public void run() {
        account.withdraw(name, 70);
    }
}

public class ThreadSynchronization27 {
    public static void main(String[] args) {
        System.out.println("=== Thread Synchronization Demonstration ===");
        
        BankAccount sharedAccount = new BankAccount();

        Thread person1 = new Thread(new WithdrawalTask(sharedAccount, "User-Alpha"));
        Thread person2 = new Thread(new WithdrawalTask(sharedAccount, "User-Beta"));

        person1.start();
        person2.start();
    }
}