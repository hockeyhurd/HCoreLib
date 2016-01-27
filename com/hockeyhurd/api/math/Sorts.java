package com.hockeyhurd.api.math;

/**
 * Helper sorting class used to sort things (hopefully) quickly.
 *
 * @author hockeyhurd
 * @version 1/27/16
 */
public final class Sorts {

    public enum SortType {
        ASCENDING, DESCENDING;
    }

    private Sorts() {
    }

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
            }
        }

        // Call recursively.
        if (lower < j) quickSorti(arr, lower, j, sortType);
        if (i < higher) quickSorti(arr, i, higher, sortType);

        return true;
    }

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
            }
        }

        // Call recursively.
        if (lower < j) quickSortl(arr, lower, j, sortType);
        if (i < higher) quickSortl(arr, i, higher, sortType);

        return true;
    }

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
            }
        }

        // Call recursively.
        if (lower < j) quickSortf(arr, lower, j, sortType);
        if (i < higher) quickSortf(arr, i, higher, sortType);

        return true;
    }

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
            }
        }

        // Call recursively.
        if (lower < j) quickSortd(arr, lower, j, sortType);
        if (i < higher) quickSortd(arr, i, higher, sortType);

        return true;
    }

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
            }
        }

        // Call recursively.
        if (lower < j) quickSort(comparables, lower, j, sortType);
        if (i < higher) quickSort(comparables, i, higher, sortType);

        return true;
    }

}
