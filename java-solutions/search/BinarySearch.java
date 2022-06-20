
package search;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public class BinarySearch {
    // Pred: args != null, args[i] - is Number
    //
    // Post:
    //      arr.size == args.size - 1
    //      arr[i] = Integer.parseInt(args[i])
    //      R is i, where i: x = arr[i]
    public static void main(String[] args) {
        // Pred: True
        // Post: x = int(args[0)
        int x = Integer.parseInt(args[0]);

        // Pred: args.length >= 1
        // Post: array.size = args.size - 1 && array is empty
        int[] array = new int[args.length - 1];

        // Pred: True
        // Post: array[i] = Integer.parseInt(args[i]), where i = 0 .. array.size
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }

        // Pred: True
        // Post: R is i, where i: x = arr[i]
        System.out.println(recursiveBinarySearch(array, x, -1, array.length));
    }

    // Pred: x is in array
    // Post: R is i, where i: x = arr[i]
    public static int iterativeBinarySearch(int[] array, int x) {
        // Pred: True
        // Post: left = -1 && right = arr.size()
        int left = -1;
        int right = array.length;

        // Pred: left = -1 && right = arr.size()
        // Post: R is i, where i: x = arr[i]
        while (left < right - 1) {
            int middle = (left + right) / 2;
            if (array[middle] < x) {
                right = middle;
            } else left = middle;
        }
        return right;
    }

    // Pred: x is in array
    // Post: R is i, where i: x = arr[i]
    private static int recursiveBinarySearch(int[] array, int x, int left, int right) {
        // Pred: r = 1 + l
        // Post: R is right
        if (right == 1 + left) return right;

        var middle = (left + right) / 2;

        // Pred: mid = (l + r) / 2
        // Post: array[m] > x ? left = m : right = m
        if (array[middle] > x) {
            left = middle;
        } else right = middle;

        return recursiveBinarySearch(array, x, left, right);
    }
}
