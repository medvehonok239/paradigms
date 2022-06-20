package queue;

/*
 Model:
       [a_1, a_2, a_3 … a_N]
       n - Queue's size
*/


import java.util.function.Predicate;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public interface Queue {
    // Pred: obj != null && array != null
    // Post: size += 1, arr[size - 1] = obj
    void enqueue(Object element);

    // Pred: size > 0
    // Post: R is array[start]
    Object element();

    // Pred: size =- 1
    // Post: R is array[start], array[i] = array[i + 1]
    Object dequeue();

    // Pred: True
    // Post: R = size
    int size();

    // Pred: True
    // R = (size == 0)
    boolean isEmpty();

    // Pred: True
    // Post: size = start = end = 0 && map.size == 0
    void clear();

    int countIf(Predicate<Object> predicate);
}
