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
public class ArrayQueueADT {
    private final int capacity = 100_000;

    public Object[] objects = new Object[capacity];
    private int start = 0, end = 0, size = 0;

    private static final HashMap<Object, Integer> objectIntegerHashMap = new HashMap<>();

    // Pred: obj != null && array != null
    // Post: size += 1, arr[size - 1] = obj
    public static void enqueue(ArrayQueueADT arrayQueueADT, Object obj) {
        simpleFunc(obj, false);
        arrayQueueADT.objects[arrayQueueADT.end++] = obj;
        arrayQueueADT.end = arrayQueueADT.end % arrayQueueADT.objects.length;
        arrayQueueADT.size += 1;
    }

    // Pred: size > 0
    // Post: R is array[start]
    public static Object element(ArrayQueueADT arrayQueueADT) {
        return arrayQueueADT.objects[arrayQueueADT.start];
    }

    // Pred: size =- 1
    // Post: R is array[start], array[i] = array[i + 1]
    public static Object dequeue(ArrayQueueADT arrayQueueADT) {
        simpleFunc(arrayQueueADT.objects[arrayQueueADT.start], true);

        var o = arrayQueueADT.objects[arrayQueueADT.start];
        arrayQueueADT.objects[arrayQueueADT.start++] = null;
        arrayQueueADT.start = arrayQueueADT.start % arrayQueueADT.objects.length;
        arrayQueueADT.size -= 1;

        return o;
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

    // Pred: size != null
    // Post: R = size
    public static int size(ArrayQueueADT arrayQueueADT) {
        return arrayQueueADT.size;
    }

    // Pred: True
    // R = (size == 0)
    public static boolean isEmpty(ArrayQueueADT arrayQueueADT) {
        return Objects.equals(0, arrayQueueADT.size);
    }

    // Pred: True
    // Post: size = start = end = 0 && map.size == 0
    public static void clear(ArrayQueueADT arrayQueueADT) {
        arrayQueueADT.start = arrayQueueADT.size = arrayQueueADT.end = 0;

        objectIntegerHashMap.clear();
    }

    // Pred: size != 0
    // Post: R is count(obj)
    public static int count(ArrayQueueADT arrayQueueADT, Object obj) {
        return objectIntegerHashMap.getOrDefault(obj, 0);
    }
}
