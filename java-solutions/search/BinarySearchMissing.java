package search;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 **/
public class BinarySearchMissing {
    // Pred: args != null, args[i] - is Number
    //
    // Post:
    //      arr.size == args.size - 1
    //      arr[i] = Integer.parseInt(args[i])
    //      (arr contains 'x') -> R is i, where i: x = arr[i]
    //      else:
    //           R is (- 1 - i), where i: x > arr[i]
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
        // Post:
        //      (arr contains 'x') -> R is i, where i: x = arr[i]
        //      else:
        //           R is (- 1 - i), where i: x > arr[i]
        if (array.length > 0) {
            if (hasElement(array, x)) {
                System.out.println(recurBinSearch(array, x, 0, array.length - 1));
            } else {
                System.out.println(-findIndex(array, x) - 1);
            }
        } else System.out.println(-1);


        //System.out.println(iterativeBinarySearch(array, x));
    }

    // Pred: arr != null && arr[i] == key
    // Post: R is i, where i: arr[i] = key
    //                  || R is arr.size, if arr[j] != key
    public static int findIndex(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (key < arr[i]) {
                return i;
            }
        }

        return arr.length;
    }

    // Pred: arr != null && key != null
    // Post: R == (arr contains key)
    public static boolean hasElement(int[] arr, int key) {
        for (int j : arr) {
            if (j == key) {
                return true;
            }
        }

        return false;
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
                left = middle;
            } else right = middle;
        }

        // Pred:  array.length != 0
        //        && array.length != right
        //        && array[right] == x
        //
        // Post: R is {right, (-right - 1)
        return array.length != 0
                && array.length != right
                && array[right] == x
                ? right : (-right - 1);
    }

    // Pred: x is in array
    // Post: R is -1.
    public static int recurBinSearch(final int[] array, int x, int left, int right) {
        // Pred: True
        // Post: array[j] != x -> R is -1.
        if (x < array[left] || x > array[right] || left > right) return -1;

        var middle = (left + right) / 2;

        // Pred: mid = (l + r) / 2 && left != null && right != null
        // Post:
        //          array[left] == x -> R is left
        //          array[right] == x ? R is right : R is (-left)

        if (left == middle) {
            if (array[left] == x) return left;
            if (array[right] == x) return right;
            return -left;
        }

        if (array[middle] < x) {
            left = middle;
        } else right = middle;

        return recurBinSearch(array, x, left, right);
    }
}
