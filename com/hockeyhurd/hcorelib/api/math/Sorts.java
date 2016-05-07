package com.hockeyhurd.hcorelib.api.math;

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
		ASCENDING, DESCENDING
	}

    private Sorts() {
    }

    /**
     * Selection sorts int array.
     *
     * @param arr Int array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSorti(int[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

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
     * Selection sorts long array.
     *
     * @param arr Long array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortl(long[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

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
     * Selection sorts float array.
     *
     * @param arr Float array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortf(float[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

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
     * Selection sorts double array.
     *
     * @param arr Double array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    public static boolean selectionSortd(double[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

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
     * Selection sorts a comparable object array.
     *
     * @param comparables A comparable array.
     * @param sortType SortType to use.
     * @return Result; true for success, false for error.
     */
    @SuppressWarnings("unchecked")
    public static boolean selectionSort(Comparable[] comparables, SortType sortType) {
        if (comparables == null || comparables.length == 0) return false;
        else if (comparables.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (comparables.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (arr.length == 1) return true;

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
        else if (comparables.length == 1) return true;

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

    /**
     * Radix sorts int array.
	 *
     * @param arr int array to sort.
     * @param sortType SortType to use.
     * @return Sorted array if successful, else returns false.
     */
    public static boolean radixSorti(int[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

        int[] output = new int[arr.length];

        for (int byteIndex = 0; byteIndex < 0x20; byteIndex += 0x8) {
            int[] sortOffsets = new int[0x100];

            for (int i = 0; i < arr.length; i++) {
                int radixVal = intToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;
                sortOffsets[radixPiece]++;
            }

            int total = 0;
            for (int i = 0; i < sortOffsets.length; i++) {
                int count = sortOffsets[i];
                sortOffsets[i] = total;
                total += count;
            }

            for (int i = 0; i < arr.length; i++) {
                int radixVal = intToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;
                if (sortOffsets[radixPiece] < output.length) output[sortOffsets[radixPiece]++] = arr[i];
            }

            // arr = output;

            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
        }

        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) break;
        }

        if (i > 0 && i < arr.length) {
            int posNumLen = arr.length - i;
            int negNumLen = i;
            int index;

            int[] posNums = new int[posNumLen];
            int[] negNums = new int[negNumLen];

            for (index = 0; index < posNumLen; index++) {
                posNums[index] = arr[index + i];
            }

            for (index = 0; index < negNumLen; index++) {
                negNums[index] = arr[index];
            }

            reverseArrayi(negNums);

            // Reassemble the array:
            i = 0;
            for (index = 0; index < negNumLen; index++) {
                arr[i++] = negNums[index];
            }

            for (index = 0; index < posNumLen; index++) {
                arr[i++] = posNums[index];
            }
        }

        if (sortType == SortType.DESCENDING) reverseArrayi(arr);

        return true;
    }

	/**
	 * Radix sorts float array.
	 *
	 * @param arr float array to sort.
	 * @param sortType SortType to use.
	 * @return Sorted array if successful, else returns false.
	 */
    public static boolean radixSortf(float[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

        float[] output = new float[arr.length];

        for (int byteIndex = 0; byteIndex < 0x20; byteIndex += 0x8) {
            int[] sortOffsets = new int[0x100];

            for (int i = 0; i < arr.length; i++) {
                int radixVal = floatToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;
                sortOffsets[radixPiece]++;
            }

            int total = 0;
            for (int i = 0; i < sortOffsets.length; i++) {
                int count = sortOffsets[i];
                sortOffsets[i] = total;
                total += count;
            }

            for (int i = 0; i < arr.length; i++) {
                int radixVal = floatToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;

                if (sortOffsets[radixPiece] < output.length) output[sortOffsets[radixPiece]++] = arr[i];
            }

            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
        }

        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) break;
        }

        if (i > 0 && i < arr.length) {
            int posNumLen = arr.length - i;
            int negNumLen = i;
            int index;

            float[] posNums = new float[posNumLen];
            float[] negNums = new float[negNumLen];

            for (index = 0; index < posNumLen; index++) {
                posNums[index] = arr[index + i];
            }

            for (index = 0; index < negNumLen; index++) {
                negNums[index] = arr[index];
            }

            reverseArrayf(negNums);

            // Reassemble the array:
            i = 0;
            for (index = 0; index < negNumLen; index++) {
                arr[i++] = negNums[index];
            }

            for (index = 0; index < posNumLen; index++) {
                arr[i++] = posNums[index];
            }
        }

        if (sortType == SortType.DESCENDING) reverseArrayf(arr);

        return true;
    }

	/**
	 * Radix sorts double array.
	 *
	 * @param arr double array to sort.
	 * @param sortType SortType to use.
	 * @return Sorted array if successful, else returns false.
	 */
    public static boolean radixSortd(double[] arr, SortType sortType) {
        if (arr == null || arr.length == 0) return false;
        else if (arr.length == 1) return true;

        double[] output = new double[arr.length];

        for (int byteIndex = 0; byteIndex < 0x20; byteIndex += 0x8) {
            int[] sortOffsets = new int[0x100];

            for (int i = 0; i < arr.length; i++) {
                int radixVal = doubleToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;
                sortOffsets[radixPiece]++;
            }

            int total = 0;
            for (int i = 0; i < sortOffsets.length; i++) {
                int count = sortOffsets[i];
                sortOffsets[i] = total;
                total += count;
            }

            for (int i = 0; i < arr.length; i++) {
                int radixVal = doubleToU32(arr[i]);
                int radixPiece = (radixVal >> byteIndex) & 0xff;

                if (sortOffsets[radixPiece] < output.length) output[sortOffsets[radixPiece]++] = arr[i];
            }

            for (int i = 0; i < arr.length; i++) {
                arr[i] = output[i];
            }
        }

        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) break;
        }

        if (i > 0 && i < arr.length) {
            int posNumLen = arr.length - i;
            int negNumLen = i;
            int index;

            double[] posNums = new double[posNumLen];
            double[] negNums = new double[negNumLen];

            for (index = 0; index < posNumLen; index++) {
                posNums[index] = arr[index + i];
            }

            for (index = 0; index < negNumLen; index++) {
                negNums[index] = arr[index];
            }

            reverseArrayd(negNums);

            // Reassemble the array:
            i = 0;
            for (index = 0; index < negNumLen; index++) {
                arr[i++] = negNums[index];
            }

            for (index = 0; index < posNumLen; index++) {
                arr[i++] = posNums[index];
            }
        }

        if (sortType == SortType.DESCENDING) reverseArrayd(arr);

        return true;
    }

	/**
	 * Reverses byte array.
	 *
	 * @param arr byte array to reverse.
	 */
    public static void reverseArrayb(byte[] arr) {
        if (arr == null || arr.length <= 1) return;

        byte temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses short array.
	 *
	 * @param arr short array to reverse.
	 */
    public static void reverseArrays(short[] arr) {
        if (arr == null || arr.length <= 1) return;

        short temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses int array.
	 *
	 * @param arr int array to reverse.
	 */
    public static void reverseArrayi(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses long array.
	 *
	 * @param arr long array to reverse.
	 */
    public static void reverseArrayl(long[] arr) {
        if (arr == null || arr.length <= 1) return;

        long temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses float array.
	 *
	 * @param arr float array to reverse.
	 */
    public static void reverseArrayf(float[] arr) {
        if (arr == null || arr.length <= 1) return;

        float temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses double array.
	 *
	 * @param arr double array to reverse.
	 */
    public static void reverseArrayd(double[] arr) {
        if (arr == null || arr.length <= 1) return;

        double temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Reverses object array.
	 *
	 * @param arr object array to reverse.
	 */
    public static void reverseArray(Object[] arr) {
        if (arr == null || arr.length <= 1) return;

        Object temp;

        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
    }

	/**
	 * Attempts to convert an int to a u32 int.
	 *
	 * @param val value to convert.
	 * @return converted int.
	 */
    public static int intToU32(int val) {
        int result = val;
        if (result < 0) result = ~result;
        else result |= 0x80000000;

        return result;
    }

	/**
	 * Attempts to convert an int to a u32 int.
	 *
	 * @param val value to convert.
	 * @return converted int.
	 */
    public static int floatToU32(float val) {
        int result = (int) val;
        if (result < 0) result = ~result;
        else result |= 0x80000000;

        return result;
    }

	/**
	 * Attempts to convert an int to a u32 int.
	 *
	 * @param val value to convert.
	 * @return converted int.
	 */
    public static int doubleToU32(double val) {
        int result = (int) val;
        if (result < 0) result = ~result;
        else result |= 0x80000000;

        return result;
    }

}
