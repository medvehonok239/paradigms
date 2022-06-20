package queue;

import java.util.HashMap;
import java.util.Objects;

/*
 Model:
       [a_1, a_2, a_3 … a_N]
       n - Queue's size
*/

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public class ArrayQueueModule {
    private static final int capacity = 100_000;

    public static Object[] array = new Object[capacity];
    private static int start = 0;
    private static int end = 0;
    private static int size = 0;

    public static HashMap<Object, Integer> objectIntegerHashMap = new HashMap<>();

    // Pred: obj != null && array != null
    // Post: size += 1, arr[size - 1] = obj
    public static void enqueue(final Object obj) {
        simpleFunc(obj, false);

        array[end++] = obj;
        end = end % array.length;
        size += 1;
    }

    // Pred: size > 0
    // Post: R is array[start]
    public static Object element() {
        return array[start];
    }

    private static void simpleFunc(Object object, boolean flag) {
        if (objectIntegerHashMap.containsKey(object)) {
            int count = objectIntegerHashMap.get(object);
            if (flag) objectIntegerHashMap.put(object, --count);
            else objectIntegerHashMap.put(object, ++count);
        } else {
            objectIntegerHashMap.put(object, 1);
        }
    }

    // Pred: size =- 1
    // Post: R is array[start], array[i] = array[i + 1]
    public static Object dequeue() {
        simpleFunc(array[start], true);

        Object o = array[start];
        array[start++] = null;
        start = start % array.length;
        size -= 1;

        return o;
    }

    // Pred: size != null
    // Post: R = size
    public static int size() {
        return size;
    }

    // Pred: True
    // R = (size == 0)
    public static boolean isEmpty() {
        return Objects.equals(0, size);
    }

    // Pred: True
    // Post: size = start = end = 0 && map.size == 0
    public static void clear() {
        start = size = end = 0;

        objectIntegerHashMap.clear();
    }

    // Pred: size != 0
    // Post: R is count(obj)
    public static int count(Object element) {
        return objectIntegerHashMap.getOrDefault(element, 0);
    }
}
