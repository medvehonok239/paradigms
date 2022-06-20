package queue;

import java.util.concurrent.atomic.AtomicInteger;

/*
 Model:
       [a_1, a_2, a_3 … a_N]
       n - Queue's size
 I:
       n >= 0 && for i = 1..n : a[i] != null
*/

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public class ArrayQueue extends AbstractQueue {
    // Pred: obj != null && array != null
    // Post: size += 1, arr[size - 1] = obj
    public void enqueue(Object obj) {
        simpleFunc(obj, true);
        array[end++] = obj;
        end = end % array.length;
        size += 1;
    }

    private void simpleFunc(Object obj, boolean flag) {
        if (objectIntegerHashMap.containsKey(obj)) {
            var c = new AtomicInteger(objectIntegerHashMap.get(obj));
            if (flag) objectIntegerHashMap.put(obj, c.incrementAndGet());
            else objectIntegerHashMap.put(obj, c.decrementAndGet());
        } else objectIntegerHashMap.put(obj, 1);
    }

    // Pred: size > 0
    // Post: R is array[start]
    public Object element() {
        return array[start];
    }

    // Pred: size =- 1
    // Post: R is array[start], array[i] = array[i + 1]
    public Object dequeue() {
        simpleFunc(array[start], false);
        var o = array[start];
        array[start++] = null;
        start = start % array.length;
        size -= 1;

        return o;
    }
}
