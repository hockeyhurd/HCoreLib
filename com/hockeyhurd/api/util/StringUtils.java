package com.hockeyhurd.api.util;

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
     * Gets a single String from an array of Objects.
     *
     * @param objects Objects.
     * @return Single String from Objects.
     */
    public String getString(Object... objects) {
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
    public String substring(String ref, int begin) {
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
    public String substring(String ref, int begin, int end) {
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
    public String trim(String ref) {
        if (LogicHelper.nullCheckString(ref)) ref = ref.trim();

        return ref;
    }

    /**
     * Gets the char array from the passed String reference.
     *
     * @param ref String to reference.
     * @return Char array representing the String.
     */
    public char[] getCharArray(String ref) {
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
    public String replace(String ref, char oldChar, char newChar) {
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
    public String replace(String ref, String pattern, String replacement) {
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
    public int[] indicies(char c, String ref) {
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
    public Map<String, Integer[]> indicies(char c, String... strings) {
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

}
