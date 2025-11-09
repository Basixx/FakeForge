package com.romecka.fakeforge.utils;

import java.util.Map;

import static java.util.Map.entry;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class StringUtils {

    private static final Map<Character, Character> POLISH_CHARS = Map.ofEntries(
            entry('ą', 'a'),
            entry('ć', 'c'),
            entry('ę', 'e'),
            entry('ł', 'l'),
            entry('ń', 'n'),
            entry('ó', 'o'),
            entry('ś', 's'),
            entry('ź', 'z'),
            entry('ż', 'z'),
            entry('Ą', 'A'),
            entry('Ć', 'C'),
            entry('Ę', 'E'),
            entry('Ł', 'L'),
            entry('Ń', 'N'),
            entry('Ó', 'O'),
            entry('Ś', 'S'),
            entry('Ź', 'Z'),
            entry('Ż', 'Z')
    );

    public static String polishToLatin(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            Character replacement = POLISH_CHARS.get(c);
            result.append(defaultIfNull(replacement, c));
        }
        return result.toString();
    }

    public static String mask(String value, int keepStart, int keepEnd) {
        if (value == null) return null;
        if (keepStart < 0 || keepEnd < 0) throw new IllegalArgumentException("keepStart/keepEnd must be >= 0");

        int len = value.length();
        if (len == 0) return value;
        if (keepStart + keepEnd >= len) {
            return "***";
        }

        int starsCount = len - keepStart - keepEnd;
        StringBuilder sb = new StringBuilder(len);
        if (keepStart > 0) sb.append(value, 0, Math.min(keepStart, len));
        sb.append("*".repeat(Math.max(0, starsCount)));
        if (keepEnd > 0) sb.append(value, len - keepEnd, len);
        return sb.toString();
    }

    public static String mask(String value) {
        return mask(value, 3, 1);
    }

}
