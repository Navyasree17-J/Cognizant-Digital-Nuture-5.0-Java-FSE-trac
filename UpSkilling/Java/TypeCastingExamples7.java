// EXERCISE 7: Type Casting Example
// Objective: Practice type casting between different data types.

public class TypeCastingExamples7 {
    public static void main(String[] args) {
        System.out.println("=== Java Type Casting Demonstration ===");

        double originalDouble = 45.87;

        int castedInt = (int) originalDouble;

        System.out.println("--- Narrowing (Double to Int) ---");
        System.out.println("Original Double value: " + originalDouble);
        System.out.println("Casted Integer value:  " + castedInt);
        System.out.println("Note: The decimal portion (.87) was completely truncated, not rounded!");
        System.out.println();

        int originalInt = 45;
        double castedDouble = originalInt;

        System.out.println("--- Widening (Int to Double) ---");
        System.out.println("Original Integer value: " + originalInt);
        System.out.println("Automatically Casted Double value: " + castedDouble);
    }
}