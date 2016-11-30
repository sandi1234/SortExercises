package mergeSort.Comparator;

import java.util.Comparator;

public class MergeSort {
    /** The method for sorting the numbers */
    public static <E> void mergeSort(E[] list, Comparator<? super E> comp) {
        if (list.length > 1) {
            // Merge sort the first half
            E[] firstHalf = (E[]) new Object[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf, comp);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[]) new Object[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf, comp);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list, comp);
        }
    }

    /** Merge two sorted lists */
    public static <E> void merge(E[] list1, E[] list2, E[] temp, Comparator<? super E> comp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            E e1 = list1[current1];
            E e2 = list2[current2];
            if (comp.compare(e1, e2) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /** A test method */
    public static  void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};

        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer d1, Integer d2) {
                return d1.compareTo(d2);
            }
        };

        mergeSort(list, comp);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}