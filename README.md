# Generics in Java

This project demonstrates the use of generics in Java, showcasing various generic classes, methods, and their applications. Below is an explanation of the main methods and their functionality.

## Main Methods Overview

### 1. **Raw Types and Legacy Code**
- **Class:** `Generics`
- **Methods:**
  - `addFruit()`: Adds fruits to a raw `List` and a type-safe `List<String>`.
  - `printFruit()`: Prints the fruits from both lists. The raw list requires casting, while the type-safe list does not.

### 2. **Generic Pair Classes**
- **Class:** `Pair`
  - A non-generic class that stores two `Object` types. Requires casting when retrieving values.
- **Class:** `GenericPair<T>`
  - A generic class that ensures type safety for both elements of the pair.
  - Example:
    ```java
    GenericPair<String> gp = new GenericPair<>("hello", "world");
    String first = gp.getFirst(); // No casting required
    ```
- **Class:** `MutablePair<T>`
  - A generic class with mutable fields and a `copyFrom` method to copy values from another `MutablePair` of the same type.

### 3. **Bounded Type Parameters**
- **Class:** `MutableNumberPair<T extends Number>`
  - A generic class restricted to `Number` types.
  - **Method:** `<U extends T> void copyFrom(MutableNumberPair<U> pair)`
    - Copies values from a `MutableNumberPair` of a subtype.

### 4. **Multiple Type Parameters**
- **Class:** `KeyValuePair<K extends Number & Comparable<K>, V>`
  - A generic class with two type parameters:
    - `K`: Must extend `Number` and implement `Comparable`.
    - `V`: Can be any type.
  - **Method:** `boolean keyGreaterThan(KeyValuePair<K, V> pair)`
    - Compares the keys of two `KeyValuePair` objects using `compareTo`.

### 5. **Generic Utility Methods**
- **Class:** `GenericUtil`
  - **Method:** `long sumInt(List<Integer> numberList)`
    - Sums a list of integers.
  - **Method:** `double sumFloat(List<Double> numberList)`
    - Sums a list of doubles.
  - **Method:** `<T extends Number> double sumGeneric(List<T> numberList)`
    - A generic method to sum a list of any `Number` type.
  - **Method:** `<K extends Number & Comparable<K>, V> Optional<KeyValuePair<K, V>> keyGreaterThan(KeyValuePair<K, V> pair1, KeyValuePair<K, V> pair2)`
    - Compares the keys of two `KeyValuePair` objects and returns the one with the greater key.

## Key Features
- Demonstrates the use of raw types and their limitations.
- Showcases type-safe generics for better compile-time error checking.
- Explains bounded type parameters and their applications.
- Implements generic utility methods for reusable functionality.

## How to Run
1. Clone the repository.
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA).
3. Run the `main` method in `src/Main.java`.

## Example Output
The program demonstrates:
- Type safety with generics.
- Copying values between generic objects.
- Summing lists of numbers using generic methods.
- Comparing keys in a generic key-value pair.

Feel free to explore and modify the code to understand the power of generics in Java!
