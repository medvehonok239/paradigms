package queue;

/*
 Model:
       [a_1, a_2, a_3 … a_N]
       n - Queue's size
*/

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public abstract class AbstractQueue implements Queue {
    protected static final HashMap<Object, Integer> objectIntegerHashMap = new HashMap<>();

    protected final int capacity = 100_000;
    protected Object[] array = new Object[capacity];
    protected int start = 0, end = 0, size = 0;

    // Pred: obj != null && array != null
    // Post: size += 1, arr[size - 1] = obj
    @Override
    public abstract void enqueue(Object element);

    // Pred: size > 0
    // Post: R is array[start]
    @Override
    public abstract Object element();

    // Pred: size =- 1
    // Post: R is array[start], array[i] = array[i + 1]
    @Override
    public abstract Object dequeue();

    // Pred: True
    // Post: R is size
    @Override
    public int size() {
        return size;
    }

    // Pred: True
    // R = (size == 0)
    @Override
    public boolean isEmpty() {
        return Objects.equals(size, 0);
    }

    // Pred: True
    // Post: size = start = end = 0 && map.size == 0
    @Override
    public void clear() {
        start = size = end = 0;
        objectIntegerHashMap.clear();
    }

    ;

    @Override
    public int countIf(Predicate<Object> objectPredicate) {
        var c = new AtomicInteger();

        IntStream.range(0, size).mapToObj(j -> dequeue()).forEach(dequeue -> {
            if (objectPredicate.test(dequeue)) c.getAndIncrement();
            enqueue(dequeue);
        });

        return c.get();
    }

    public int count(Object obj) {
        return objectIntegerHashMap.getOrDefault(obj, 0);
    }
}

