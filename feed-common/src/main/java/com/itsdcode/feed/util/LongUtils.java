package com.itsdcode.feed.util;

import com.google.common.primitives.Longs;

public class LongUtils {

    public static boolean contains (long a , long b){
        return Longs.contains(new long[]{a}, b);
    }
}
