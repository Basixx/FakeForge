package com.romecka.fakeforge.utils;

import java.util.Map;

import static java.util.Map.entry;

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
            result.append(replacement != null ? replacement : c);
        }
        return result.toString();
    }

}
