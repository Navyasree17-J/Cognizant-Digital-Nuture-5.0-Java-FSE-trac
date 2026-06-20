// EXERCISE 28: Generic Class
// Objective: Define and instantiate a custom type-safe Generic container class.

class Box<T> {
    // This variable will automatically match whatever type 'T' becomes
    private T content;

    // Method to put an item into our box
    public void add(T content) {
        this.content = content;
    }

    // Method to fetch the item out of our box
    public T getContent() {
        return this.content;
    }

    public void inspectType() {
        System.out.println("Box Stored Type: " + content.getClass().getName());
    }
}

public class GenericsExample28 {
    public static void main(String[] args) {
        System.out.println("=== Type-Safe Generics Demonstration ===\n");

        Box<String> stringBox = new Box<>();
        stringBox.add("Hello Java Generics!");
        
        System.out.println("String Box Value: " + stringBox.getContent());
        stringBox.inspectType();
        System.out.println();

        Box<Integer> integerBox = new Box<>();
        integerBox.add(42); // Java automatically converts primitive 42 into an Integer Object (Autoboxing)

        System.out.println("Integer Box Value: " + integerBox.getContent());
        integerBox.inspectType();
        System.out.println();

        Box<Double> doubleBox = new Box<>();
        doubleBox.add(3.14159);

        System.out.println("Double Box Value: " + doubleBox.getContent());
        doubleBox.inspectType();
    }
}