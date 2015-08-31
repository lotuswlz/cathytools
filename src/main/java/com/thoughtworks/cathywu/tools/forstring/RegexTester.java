package com.thoughtworks.cathywu.tools.forstring;

/**
 * @author lzwu
 * @since 8/26/15
 */
public class RegexTester {
    public static void main(String[] args) {
        verySpecialCase();
    }

    /**
     * maybe : equals \: ?
     * you need to write 4 "\" if you want to represent a "\" in regex.
     */
    private static void verySpecialCase() {
        String regex = "[^/\\\\:\"\\?<>\\|\\*#]+";
        String msg = "\\\\";
        System.out.println(msg.matches(regex));
    }
}
