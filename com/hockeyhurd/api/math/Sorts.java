package com.hockeyhurd.api.math;

/**
 * Helper sorting class used to sort things (hopefully) quickly.
 *
 * @author hockeyhurd
 * @version 1/27/16
 */
public final class Sorts {

    /**
     * SortType enumeration.
     *
     * @author hockeyhurd
     * @version 1/27/16
     */
    public enum SortType {
        ASCENDING, DESCENDING;
    }

    private Sorts() {
    }

    /**
     * Selction sorts int array.
     *
     * @param arr Int array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSorti(int[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        int value;

        for (int y = arr.length - 1; y > 0; y--) {
            insertIndex = 0;

            for (int x = 0; x <= y; x++) {
                if (sortType == SortType.ASCENDING && arr[x] > arr[insertIndex]) insertIndex = x;
                else if (sortType == SortType.DESCENDING && arr[x] < arr[insertIndex]) insertIndex = x;
            }

            value = arr[y];
            arr[y] = arr[insertIndex];
            arr[insertIndex] = value;
        }

        return true;
    }

    /**
     * Selction sorts long array.
     *
     * @param arr Long array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortl(long[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        long value;

        for (int y = arr.length - 1; y > 0; y--) {
            insertIndex = 0;

            for (int x = 0; x <= y; x++) {
                if (sortType == SortType.ASCENDING && arr[x] > arr[insertIndex]) insertIndex = x;
                else if (sortType == SortType.DESCENDING && arr[x] < arr[insertIndex]) insertIndex = x;
            }

            value = arr[y];
            arr[y] = arr[insertIndex];
            arr[insertIndex] = value;
        }

        return true;
    }

    /**
     * Selction sorts float array.
     *
     * @param arr Float array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortf(float[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        float value;

        for (int y = arr.length - 1; y > 0; y--) {
            insertIndex = 0;

            for (int x = 0; x <= y; x++) {
                if (sortType == SortType.ASCENDING && arr[x] > arr[insertIndex]) insertIndex = x;
                else if (sortType == SortType.DESCENDING && arr[x] < arr[insertIndex]) insertIndex = x;
            }

            value = arr[y];
            arr[y] = arr[insertIndex];
            arr[insertIndex] = value;
        }

        return true;
    }

    /**
     * Selction sorts double array.
     *
     * @param arr Double array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortd(double[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        double value;

        for (int y = arr.length - 1; y > 0; y--) {
            insertIndex = 0;

            for (int x = 0; x <= y; x++) {
                if (sortType == SortType.ASCENDING && arr[x] > arr[insertIndex]) insertIndex = x;
                else if (sortType == SortType.DESCENDING && arr[x] < arr[insertIndex]) insertIndex = x;
            }

            value = arr[y];
            arr[y] = arr[insertIndex];
            arr[insertIndex] = value;
        }

        return true;
    }

    /**
     * Selction sorts a comparable object array.
     *
     * @param comparables A comparable array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    @SuppressWarnings("unchecked")
    public static boolean selectionSort(Comparable[] comparables, SortType sortType) {
        if (comparables == null || comparables.length == 0) return false;

        int insertIndex;
        Comparable value;

        for (int y = comparables.length - 1; y > 0; y--) {
            insertIndex = 0;

            for (int x = 0; x <= y; x++) {
                if (sortType == SortType.ASCENDING && comparables[x].compareTo(comparables[insertIndex]) == 1)
                    insertIndex = x;
                else if (sortType == SortType.DESCENDING && comparables[x].compareTo(comparables[insertIndex]) == -1)
                    insertIndex = x;
            }

            value = comparables[y];
            comparables[y] = comparables[insertIndex];
            comparables[insertIndex] = value;
        }

        return true;
    }

    /**
     * Quick sorts an int array.
     * @param arr Int array.
     * @param lower Lower index bound.
     * @param higher Upper index bound.
     * @param sortType SortType to use.
     * @return Result; true if success, false if failure.
     */
    public static boolean quickSorti(int[] arr, final int lower, final int higher, SortType sortType) {
        if (arr == null || arr.length == 0 || lower > higher) return false;

        int i = lower;
        int j = higher;
        final int pivot = arr[lower + (higher - lower) / 2];

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                // Sawp values.
                final int value = arr[i];
                arr[i] = arr[j];
                arr[j] = value;

                i++;
                j--;
            }
        }

        // Call recursively.
        if (lower < j) quickSorti(arr, lower, j, sortType);
        if (i < higher) quickSorti(arr, i, higher, sortType);

        return true;
    }

    /**
     * Quick sorts an long array.
     * @param arr Long array.
     * @param lower Lower index bound.
     * @param higher Upper index bound.
     * @param sortType SortType to use.
     * @return Result; true if success, false if failure.
     */
    public static boolean quickSortl(long[] arr, final int lower, final int higher, SortType sortType) {
        if (arr == null || arr.length == 0 || lower > higher) return false;

        int i = lower;
        int j = higher;
        final long pivot = arr[lower + (higher - lower) / 2];

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                // Sawp values.
                final long value = arr[i];
                arr[i] = arr[j];
                arr[j] = value;

                i++;
                j--;
            }
        }

        // Call recursively.
        if (lower < j) quickSortl(arr, lower, j, sortType);
        if (i < higher) quickSortl(arr, i, higher, sortType);

        return true;
    }

    /**
     * Quick sorts an float array.
     * @param arr Float array.
     * @param lower Lower index bound.
     * @param higher Upper index bound.
     * @param sortType SortType to use.
     * @return Result; true if success, false if failure.
     */
    public static boolean quickSortf(float[] arr, final int lower, final int higher, SortType sortType) {
        if (arr == null || arr.length == 0 || lower > higher) return false;

        int i = lower;
        int j = higher;
        final float pivot = arr[lower + (higher - lower) / 2];

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                // Sawp values.
                final float value = arr[i];
                arr[i] = arr[j];
                arr[j] = value;

                i++;
                j--;
            }
        }

        // Call recursively.
        if (lower < j) quickSortf(arr, lower, j, sortType);
        if (i < higher) quickSortf(arr, i, higher, sortType);

        return true;
    }

    /**
     * Quick sorts an double array.
     * @param arr Double array.
     * @param lower Lower index bound.
     * @param higher Upper index bound.
     * @param sortType SortType to use.
     * @return Result; true if success, false if failure.
     */
    public static boolean quickSortd(double[] arr, final int lower, final int higher, SortType sortType) {
        if (arr == null || arr.length == 0 || lower > higher) return false;

        int i = lower;
        int j = higher;
        final double pivot = arr[lower + (higher - lower) / 2];

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                // Sawp values.
                final double value = arr[i];
                arr[i] = arr[j];
                arr[j] = value;

                i++;
                j--;
            }
        }

        // Call recursively.
        if (lower < j) quickSortd(arr, lower, j, sortType);
        if (i < higher) quickSortd(arr, i, higher, sortType);

        return true;
    }

    /**
     * Quick sorts a comparable object array.
     *
     * @param comparables Comparable object array.
     * @param lower Lower index bound.
     * @param higher Upper index bound.
     * @param sortType SortType to use.
     * @return Result; true if success, false if failure.
     */
    @SuppressWarnings("unchecked")
    public static boolean quickSort(Comparable[] comparables, final int lower, final int higher, SortType sortType) {
        if (comparables == null || comparables.length == 0 || lower > higher) return false;

        int i = lower;
        int j = higher;
        final Comparable pivot = comparables[lower + (higher - lower) / 2];

        while (i <= j) {
            while (comparables[i].compareTo(pivot) == -1) i++;
            while (comparables[j].compareTo(pivot) == 1) j--;

            if (i <= j) {
                // Swap values.
                final Comparable value = comparables[i];
                comparables[i] = comparables[j];
                comparables[j] = value;

                i++;
                j--;
            }
        }

        // Call recursively.
        if (lower < j) quickSort(comparables, lower, j, sortType);
        if (i < higher) quickSort(comparables, i, higher, sortType);

        return true;
    }

    /**
     * Insertion sorts an int array.
     *
     * @param arr Int array to sort.
     * @param sortType SortType to use.
     * @return Result; true if successful, else false.
     */
    public static boolean insertionSorti(int[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        int k;
        int next;

        for (int i = 0; i < arr.length; i++) {
            next = arr[i];
            insertIndex = 0;
            k = i;

            while (k > 0 && insertIndex == 0) {
                if (sortType == SortType.ASCENDING && next > arr[k - 1]) insertIndex = k;
                else if (sortType == SortType.DESCENDING && next < arr[k - 1]) insertIndex = k;
                else arr[k] = arr[k - 1];

                k--;
            }

            arr[insertIndex] = next;
        }

        return true;
    }

    /**
     * Insertion sorts an long array.
     *
     * @param arr Long array to sort.
     * @param sortType SortType to use.
     * @return Result; true if successful, else false.
     */
    public static boolean insertionSortl(long[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        int k;
        long next;

        for (int i = 0; i < arr.length; i++) {
            next = arr[i];
            insertIndex = 0;
            k = i;

            while (k > 0 && insertIndex == 0) {
                if (sortType == SortType.ASCENDING && next > arr[k - 1]) insertIndex = k;
                else if (sortType == SortType.DESCENDING && next < arr[k - 1]) insertIndex = k;
                else arr[k] = arr[k - 1];

                k--;
            }

            arr[insertIndex] = next;
        }

        return true;
    }

    /**
     * Insertion sorts an float array.
     *
     * @param arr Float array to sort.
     * @param sortType SortType to use.
     * @return Result; true if successful, else false.
     */
    public static boolean insertionSortf(float[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        int k;
        float next;

        for (int i = 0; i < arr.length; i++) {
            next = arr[i];
            insertIndex = 0;
            k = i;

            while (k > 0 && insertIndex == 0) {
                if (sortType == SortType.ASCENDING && next > arr[k - 1]) insertIndex = k;
                else if (sortType == SortType.DESCENDING && next < arr[k - 1]) insertIndex = k;
                else arr[k] = arr[k - 1];

                k--;
            }

            arr[insertIndex] = next;
        }

        return true;
    }

    /**
     * Insertion sorts an double array.
     *
     * @param arr Double array to sort.
     * @param sortType SortType to use.
     * @return Result; true if successful, else false.
     */
    public static boolean insertionSortd(double[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;

        int insertIndex;
        int k;
        double next;

        for (int i = 0; i < arr.length; i++) {
            next = arr[i];
            insertIndex = 0;
            k = i;

            while (k > 0 && insertIndex == 0) {
                if (sortType == SortType.ASCENDING && next > arr[k - 1]) insertIndex = k;
                else if (sortType == SortType.DESCENDING && next < arr[k - 1]) insertIndex = k;
                else arr[k] = arr[k - 1];

                k--;
            }

            arr[insertIndex] = next;
        }

        return true;
    }

    /**
     * Insertion sorts a comparable object array.
     *
     * @param comparables Comparable array to sort.
     * @param sortType SortType to use.
     * @return Result; true if successful, else false.
     */
    @SuppressWarnings("unchecked")
    public static boolean insertionSort(Comparable[] comparables, SortType sortType) {
        if (comparables == null || comparables.length == 0) return false;

        int insertIndex;
        int k;
        Comparable next;

        for (int i = 0; i < comparables.length; i++) {
            next = comparables[i];
            insertIndex = 0;
            k = i;

            while (k > 0 && insertIndex == 0) {
                if (sortType == SortType.ASCENDING && next.compareTo(comparables[k - 1]) == 1) insertIndex = k;
                else if (sortType == SortType.DESCENDING && next.compareTo(comparables[k - 1]) == -1) insertIndex = k;
                else comparables[k] = comparables[k - 1];

                k--;
            }

            comparables[insertIndex] = next;
        }

        return true;
    }

}
