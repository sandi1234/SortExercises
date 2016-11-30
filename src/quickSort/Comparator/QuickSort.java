package quickSort.Comparator;

import java.util.Comparator;

public class QuickSort {
    public static <E> void quickSort(E[] list, Comparator<? super E> comp) {
        quickSort(list, 0, list.length - 1, comp);
    }

    private static <E> void quickSort(E[] list, int first, int last, Comparator<? super E> comp) {
        if (last > first) {
            int pivotIndex = partition(list, first, last, comp);
            quickSort(list, first, pivotIndex - 1, comp);
            quickSort(list, pivotIndex + 1, last, comp);
        }
    }

    /** Partition the array list[first..last] */
    private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comp) {
        E pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && comp.compare(list[low], pivot) <= 0)
                low++;

            // Search backward from right
            while (low <= high && comp.compare(list[high], pivot) > 0)
                high--;

            // Swap two elements in the list
            if (high > low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && comp.compare(list[high], pivot) >= 0)
            high--;

        // Swap pivot with list[high]
        if (comp.compare(pivot, list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer d1, Integer d2) {
                return d1.compareTo(d2);
            }
        };

        quickSort(list, comp);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}