import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Generics generics = new Generics();
        generics.addFruit();
        generics.printFruit();

        Pair p = new Pair("hello", "world");
        Pair p1 = new Pair(1, 2);
        Pair p2 = new Pair(1, "hello"); // weird pairs since we use generic types
        Pair p3 = new Pair("hello", 1); // weird pairs since we use generic types
        String s = (String) p.getFirst(); // need to cast and it may throw exceptions, not type safe

        // GenericPair T-> String
        GenericPair<String> gp = new GenericPair<>("hello", "world");
        // GenericPair T-> Integer
        GenericPair<Integer> gp1 = new GenericPair<>(1, 2);
        Integer i = gp1.getFirst(); // no need to cast, it is type safe
        String s1 = gp.getFirst(); // no need to cast, it is type safe
        var GenericPair = new GenericPair<>(1, 2); // it will be inferred as GenericPair<Integer> by the compiler
        var GenericPair1 = new GenericPair<>("hello", "world"); // it will be inferred as GenericPair<String> by the compiler
        Integer i1 = GenericPair.getFirst(); // no need to cast, it is type safe
        String s2 = GenericPair1.getFirst(); // no need to cast, it is type safe
        //GenericPair<Integer> gp2 = new GenericPair<>(1, "hello"); // weird pairs since we use generic types, IT WILL THROW COMPILE TIME ERROR
        //GenericPair<String> gp3 = new GenericPair<>("hello", 1); // weird pairs since we use generic types, IT WILL THROW COMPILE TIME ERROR
    }

}

class Generics {
    List fruit = new ArrayList<>(); // not type safe no generic used
    List<String> fruit1 = new ArrayList<>(); // type safe
    public void addFruit() {
        fruit.add("apple");
        fruit.add("banana");
        //fruit.add(1); // not type safe , will throw run time casting error, it's better to always catch errors in compile time
        fruit1.add("apple");
        fruit1.add("banana");
        //fruit1.add(1); // type safe
    }
    public void printFruit() {
        for (Iterator it = fruit.iterator(); it.hasNext(); ) {
           String f = (String) it.next(); // need to cast be
            System.out.println(f);
        }

        for(String f : fruit1) { // no need to cast
            System.out.println(f);
        }
    }
}

class Pair {
    private final Object first;
    private final Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }
    public Object getFirst() {
        return first;
    }
    public Object getSecond() {
        return second;
    }
}
class GenericPair<T> { // an upper case is always used for parameterized types
    private final T first;
    private final T second;
 // no need to cast, it is type safe, it will throw compile time error if we try to add different types
    public GenericPair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    public T getFirst() {
        return first;
    }
    public T getSecond() {
        return second;
    }
}