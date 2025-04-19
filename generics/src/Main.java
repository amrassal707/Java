import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

        // here we can copy the values from one pair to another
        MutablePair<String> pair = new MutablePair<>("hello", "world");
        MutablePair<String> pair1 = new MutablePair<>("1", "2");

        pair.copyFrom(pair1);


        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());


        //Using bounded type parameter
        MutableNumberPair<Integer> pair2 = new MutableNumberPair<>(1, 2);
        MutableNumberPair<Float> pair3 = new MutableNumberPair<>(3.3F, 4.2F);

        BigDecimal bd1 = new BigDecimal("1.0");
        BigDecimal bd2 = new BigDecimal("2.0");
        MutableNumberPair<BigDecimal> pair4 = new MutableNumberPair<>(bd1, bd2);

        // Mutable String not allowed since String is not a subclass of Number
        // MutableNumberPair<String> pair5 = new MutableNumberPair<>("hello", "world"); // IT WILL THROW COMPILE TIME ERROR


        // multiple pair can be created with different types
        // full declaration
//        KeyValuePair<String, String> keyValuePair = new KeyValuePair<String, String>("hello", "world");


        // diamond operator
        KeyValuePair<Integer, String> keyValuePair1 = new KeyValuePair<>(1, "world");

        // using var
        var keyValuePair2 = new KeyValuePair<>(1, "world"); // it will be inferred as KeyValuePair<Integer, String> by the compiler


        // we can use the compareTO
        // keyValuePair1.keyGreaterThan(keyValuePair2); // it will throw compile time error since we don't know the type of K
        // we can use the compareTO since K is bounded by Comparable
        System.out.println(keyValuePair1.keyGreaterThan(keyValuePair2)); // it will throw compile time error since we don't know the type of K


        // if we use atomic long, it will throw compilation error since it doesn't implement Comparable

        //var KeyValuePair3 = new KeyValuePair<>(new AtomicLong(1), "world"); // it will throw compile time error since we don't know the type of K


        // generic util for generic methods
        GenericUtil genericUtil = new GenericUtil();
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println(genericUtil.sumInt(intList));
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3);
        System.out.println(genericUtil.sumFloat(doubleList));


        // using partemrized bounded method paramters

        KeyValuePair keyValuePair = new KeyValuePair<Integer, String>(1, "world");
        KeyValuePair keyValuePair3 = new KeyValuePair<Integer, String>(2, "world");

        genericUtil.keyGreaterThan(keyValuePair, keyValuePair3); // it will compare the keys and return the one that is greater


        MutableNumberPair pair5 = new MutableNumberPair<Integer>(1, 2);
        MutableNumberPair pair6 = new MutableNumberPair<Float>(3.F, 4.F);

        // we can copy the values from one pair to another
        pair5.copyFrom(pair6); // since U extends T and T extends Number, it will work since MutableNumberPair<U> extends MutableNumberPair<T>


        MutableNumberPair<Integer> pair7 = new MutableNumberPair<>(1, 2);
        genericUtil.resetToZero(pair7);
        MutableNumberPair<Number> pair8 = new MutableNumberPair<>(3.3F, 4.2F);
        genericUtil.resetToZero(pair8); // it will work since Number is a super class of Integer


        // example of wild cards, we can use wild cards to allow any type that extends Number
        // example like addAll in collections framework
        List<Number> integerList = List.of(1, 2, 3);
        List<Double> doubleList1 = List.of(1.1, 2.2, 3.3);
        integerList.addAll(doubleList1);

        // dont use wildcards when you consume and produce in the same method like replaceALl in collections framework
        // boolean <T> replaceAll(List<T> list, T oldVal, T newVal)

    }

}


// when to use raw types? when dealing or calling legacy code that didn't have generics at the time jdk < 5
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

        for (String f : fruit1) { // no need to cast
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

class MutablePair<T> {
    private T first;
    private T second;


    public MutablePair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    // now we can copy and instantiate different types, not thread safe, apply synchronized
    public void copyFrom(MutablePair<T> pair) {
        this.first = pair.getFirst();
        this.second = pair.getSecond();
    }

}


// bounded type parameter T extends Number
// where we decide the types of parameters
class MutableNumberPair<T extends Number> {
    private T first;
    private T second;

    public MutableNumberPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }
    public void setSecond(T second) {
        this.second = second;
    }

    // now we solved inheritance problem , when list<Number> and list<Integer> can be copied to MutableNumberPair
    public <U extends T> void copyFrom(MutableNumberPair<U> pair) {
        this.first = pair.getFirst();
        this.second = pair.getSecond();
    }


    // wild card allowing any type that extends T to work with the copyFrom method
    public void copyFromWildCard(MutableNumberPair<? extends T> pair) {
        this.first = pair.getFirst();
        this.second = pair.getSecond();
    }


}


// multiple parameters
class KeyValuePair<K extends Number & Comparable<K>, V> {
    private K key;
    private V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean keyGreaterThan(KeyValuePair<K, V> pair) {


        // return this.key > pair.getKey(); will not work, since we don't know the type of K

        // we can use compareTo method since K is bounded by Comparable
        return this.key.compareTo(pair.getKey()) > 0;
    }

}


class GenericUtil {

    public long sumInt(List<Integer> numberList) {
        long sum = 0;
        for (Integer number : numberList) {
            sum += number;
        }
        return sum;
    }

    public double sumFloat(List<Double> numberList) {
        double sum = 0;
        for (Double number : numberList) {
            sum += number;
        }
        return sum;
    }

    // generic level method signature
    // T extends Number means that T can be any subclass of Number
    // instead of having 2 methods like above we can have 1 generic method
    public <T extends Number> double sumGeneric(List<T> numberList) {
        double sum = 0;
        for (T number : numberList) {
            sum += number.doubleValue();
        }
        return sum;
    }


    // why not just List<Number> as the parameter??
    // because List<Integer> doesn't extend List<Number> , Integer is not a subclass of Number in case of List types.
    // List<Number> is not a super type of List<Integer>

    // K and V are method level generic types instead of class level
    // K has multiple bounds, it must be a subclass of Number and implement Comparable
    // V is a subclass of Object
    // return type is optional of KeyValuePair
    public <K extends Number & Comparable<K>, V> Optional<KeyValuePair<K, V>> keyGreaterThan(KeyValuePair<K, V> pair1, KeyValuePair<K, V> pair2) {
        if (pair1.getKey().compareTo(pair2.getKey()) > 0) {
            return Optional.of(pair1);
        } else if (pair1.getKey().compareTo(pair2.getKey()) < 0) {
            return Optional.of(pair2);
        }
        return Optional.empty();


        // lower bound wildcards
        // ? super Number means that the list can be any super class of Number

        }

    public void resetToZero(MutableNumberPair<? super Integer> pair) {
        pair.setFirst(0);
        pair.setSecond(0);
    }


    // using wild cards and understanding the difference between List<Number> and List<?>
    public double sumWildCard(List<?> numberList) {
        double sum = 0;
        for (Object number : numberList) {
            if (number instanceof Number number1) {
                sum += number1.doubleValue();
            } else {
                throw new IllegalArgumentException("List must contain only numbers");
            }
        }
        return sum;
    }

    // wild cards    // ? is a wild card, it can be any type
    // ? extends Number means that the list can be any subclass of Number
    public void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    // using wild cards and understanding the difference between List<Number> and List<?> using upper bound
    public double sumdWildCardWithUpperBound(List<? extends Number> numberList) {
        double sum = 0;
        for (Number number : numberList) {
            sum += number.doubleValue();

        }
        return sum;
    }

    // when NOT to use wildcards ???
    // when there is dependency between the types, for example, if we have a method that takes a List<T> and returns a List<T>, we should not use wildcards
    // when there is no dependency between the types, for example, if we have a method that takes a List<T> and returns a List<U>, we should use wildcards

    // WHEN TO USE WILD CARDS WITH BOUNDED TYPE
    // PECS - PRODUCER EXTENDS CONSUMER SUPER
    // when we want to produce a value, we should use extends
    // when we want to consume a value, we should use super
    // use upper bound wildcards when we want to read from a list
    // use lower bound wildcards when we want to write to a list
    // use wildcards when we don't care about the type of the list


    // example like addAll in collections framework
    public void addAll(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
}
