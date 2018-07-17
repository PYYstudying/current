package com.pyy.current.immutable;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import java.util.Collections;
import java.util.Map;

public class UnmotidyCollection {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        System.out.println(map.get(3));
    }
}
