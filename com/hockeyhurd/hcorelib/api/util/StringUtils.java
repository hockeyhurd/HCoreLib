package com.hockeyhurd.hcorelib.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * General purpose String utility class.
 *
 * @author hockeyhurd
 * @version 12/8/15
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Shortened function call to LogicHelper class.
     *
     * @see com.hockeyhurd.hcorelib.api.util.LogicHelper#nullCheckString(String)
     *
     * @param text String to check.
     * @return True if not null of length == 0, else returns false.
     */
    public static boolean nullCheckString(String text) {
        return LogicHelper.nullCheckString(text);
    }

    /**
     * Shortened function call to LogicHelper class.
     *
     * @see LogicHelper#nullCheckString(String...)
     *
     * @param strings Strings to check.
     * @return True if not null, length of individual string == 0, or array length == 0, else returns false.
     */
    public static boolean nullCheckString(String... strings) {
        return LogicHelper.nullCheckString(strings);
    }

    /**
     * Searches through string for a given letter
     * and if found will return first index, else
     * returns -1.
     *
     * @param ref String to reference.
     * @param c Char to lookup.
     * @return first int index.
     */
    public static int indexOf(String ref, char c) {
        if (!nullCheckString(ref)) return -1;

        char[] arr = getCharArray(ref);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) return i;
        }

        return -1;
    }

    /**
     * Searches through string for a given letter
     * and if found will return last index, else
     * returns -1.
     *
     * @param ref String to reference.
     * @param c Char to lookup.
     * @return last int index.
     */
    public static int lastIndexOf(String ref, char c) {
        if (!nullCheckString(ref)) return -1;

        char[] arr = getCharArray(ref);

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == c) return i;
        }

        return -1;
    }

    /**
     * Checks if string contains a given char.
     *
     * @param ref String to reference.
     * @param c Char to lookup.
     * @return boolean result.
     */
    public static boolean contains(String ref, char c) {
        return indexOf(ref, c) > -1;
    }

    /**
     * Gets a single String from an array of Objects.
     *
     * @param objects Objects.
     * @return Single String from Objects.
     */
    public static String getString(Object... objects) {
        if (objects == null || objects.length == 0) return "<empty>";

        StringBuilder builder = new StringBuilder();

        for (Object obj : objects) {
            if (obj != null) builder.append(obj.toString());
        }

        return builder.toString();
    }

    /**
     * Uses String's substring method but also sets the passed reference
     * to this new String.
     *
     * @param ref   String to reference.
     * @param begin Begin index.
     * @return String substring.
     */
    public static String substring(String ref, int begin) {
        if (LogicHelper.nullCheckString(ref)) ref = ref.substring(begin);

        return ref;
    }

    /**
     * Uses String's substring method but also sets the passed reference
     * to this new String.
     *
     * @param ref   String to reference.
     * @param begin Begin index.
     * @param end   End index.
     * @return String substring.
     */
    public static String substring(String ref, int begin, int end) {
        if (LogicHelper.nullCheckString(ref)) ref = ref.substring(begin, end);

        return ref;
    }

    /**
     * Uses String's trim method but also sets the passed reference
     * to this new String.
     *
     * @param ref String to reference.
     * @return String trimmed.
     */
    public static String trim(String ref) {
        if (LogicHelper.nullCheckString(ref)) ref = ref.trim();

        return ref;
    }

    /**
     * Gets the char array from the passed String reference.
     *
     * @param ref String to reference.
     * @return Char array representing the String.
     */
    public static char[] getCharArray(String ref) {
        if (LogicHelper.nullCheckString(ref)) return ref.toCharArray();

        return ref == null ? null : new char[0];
    }

    /**
     * Replaces chars in a String with new chars and
     * sets this new replaced values to the passed
     * String reference.
     *
     * @param ref     String to reference.
     * @param oldChar Old char to find.
     * @param newChar New char to replace with.
     * @return Replaced String.
     */
    public static String replace(String ref, char oldChar, char newChar) {
        if (LogicHelper.nullCheckString(ref)) ref = ref.replace(oldChar, newChar);

        return ref;
    }

    /**
     * Replaces chars in a String with new chars and
     * sets this new replaced values to the passed
     * String reference.
     *
     * @param ref         String to reference.
     * @param pattern     Old String pattern to find.
     * @param replacement New String to replace with.
     * @return Replaced String.
     */
    public static String replace(String ref, String pattern, String replacement) {
        if (LogicHelper.nullCheckString(ref) && LogicHelper.nullCheckString(pattern) &&
                LogicHelper.nullCheckString(replacement))
            ref = ref.replace(pattern, replacement);

        return ref;
    }

    /**
     * Gets the indicies of the passed char in the String reference.
     *
     * @param c   Char to find.
     * @param ref String to reference.
     * @return Int array representing the found indicies.
     */
    public static int[] indicies(char c, String ref) {
        if (LogicHelper.nullCheckString(ref)) {
            char[] arr = ref.toCharArray();
            List<Integer> list = new ArrayList<Integer>(ref.length());

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == c) list.add(i);
            }

            if (list.isEmpty()) return new int[0];

            int[] ret = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ret[i] = list.get(i);
            }

            return ret;
        }

        return ref == null ? null : new int[0];
    }

    /**
     * Gets the indicies of the passed char in the String reference.
     *
     * @param c       Char to find.
     * @param strings Strings to reference as an array.
     * @return Mapping of the char indicies of each String referenced.
     */
    public static Map<String, Integer[]> indicies(char c, String... strings) {
        Map<String, Integer[]> map = null;

        if (strings != null && strings.length > 0) {

            for (String str : strings) {
                if (!LogicHelper.nullCheckString(str)) continue;

                char[] arr = str.toCharArray();
                List<Integer> list = new ArrayList<Integer>(str.length());

                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == c) list.add(i);
                }

                if (list.isEmpty()) continue;

                if (map == null) map = new HashMap<String, Integer[]>(strings.length);
                map.put(str, (Integer[]) list.toArray());
            }
        }

        return map;
    }

    /**
     * Gets if two Strings are equal.
     *
     * @param text0 String.
     * @param text1 String.
     * @return True if equal, else false.
     */
    public static boolean isEqual(String text0, String text1) {
        return text0 != null && text1 != null && text0.length() == text1.length() &&
                text0.length() > 0 && text0.equals(text1);
    }

    /**
     * Gets if two Strings are equal (case insensitive).
     *
     * @param text0 String.
     * @param text1 String.
     * @return True if equal, else false.
     */
    public static boolean isEqulsIgnoreCase(String text0, String text1) {
        return text0 != null && text1 != null && text0.length() == text1.length() &&
                text0.length() > 0 && text0.equalsIgnoreCase(text1);
    }

    /**
     * Compares two Strings.
     *
     * @param text  String.
     * @param other String.
     * @return Result of comparing two Strings.
     */
    public static int compareTo(String text, String other) {
        if (!nullCheckString(text) || !nullCheckString(other)) return 0;
        else if (text.length() < other.length()) return -1;
        else if (text.length() > other.length()) return 1;


        for (int i = 0; i < text.length(); i++) {
            char c0 = text.charAt(i);
            char c1 = other.charAt(i);

            if (c0 < c1) return -1;
            else if (c0 > c1) return 1;
        }

        return 0;
    }

    /**
     * Interprets compareTo method to see if text comes
     * lexicographically before the other.
     *
     * @param text String to reference.
     * @param other String to compare.
     * @return boolean result.
     */
    public static boolean isTextBeforeOther(String text, String other) {
        return compareTo(text, other) < 0;
    }

    /**
     * Interprets compareTo method to see if text comes
     * lexicographically after the other.
     *
     * @param text String to reference.
     * @param other String to compare.
     * @return boolean result.
     */
    public static boolean isTextAfterOther(String text, String other) {
        return compareTo(text, other) > 0;
    }

}
