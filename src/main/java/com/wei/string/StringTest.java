package com.wei.string;

import org.junit.Test;

/**
 * @author weiyinglei
 */
public class StringTest {

    /**
     * Double Quotes vs. Constructor
     */
    @Test
    public void testString1() {
        /*
         * if String a = "abcd",then "abcd" will be put into constant poll of method area.
         */
//        String a = "abcd";
//        String b = "abcd";
//        System.out.println(a == b);
//        System.out.println(a.equals(b));

        /*
         * if String c = new String("abcd"),then "abcd" will be put into heap.
         */
        String c = new String("abcd");
        String d = new String("abcd");
        System.out.println(c == d);
        System.out.println(c.equals(d));

        String e = new String("abcd").intern();
        String f = new String("abcd").intern();
        System.out.println(e == f);  // Now true
        System.out.println(e.equals(f)); // True
    }
}
