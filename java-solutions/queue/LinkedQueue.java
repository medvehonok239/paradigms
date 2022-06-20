package queue;

import java.util.Objects;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public class LinkedQueue extends AbstractQueue {
    private Node t, h;

    // Pred: node != null
    // Post: size += 1, add element
    @Override
    public void enqueue(Object object) {
        var n = new Node(object, null);
        if (size == 0) t = h = n;
        else t.parent = t = n;

        size += 1;
    }

    // Pred: size > 0
    // Post: R is head.data;
    @Override
    public Object element() {
        return h.data;
    }

    // Pred: size =- 1
    // Post: R is h.data
    @Override
    public Object dequeue() {
        var obj = h;
        if (obj != null) h = h.parent;
        size -= 1;

        return Objects.requireNonNull(obj).data;
    }

    // Pred: True
    // Post t = h = size = null
    @Override
    public void clear() {
        t = h = null;
        size = 0;
    }

    private static final class Node {
        Node parent;
        Object data;

        public Node(Object data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
