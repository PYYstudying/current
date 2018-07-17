package com.pyy.current.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollection {

    private static List<Integer> list = ImmutableList.of(1,2,3,4);

    private static Set<Integer> set = ImmutableSet.of(5,6,7,8);

    private static Map<Integer,Integer> map = ImmutableMap.of(1,2,3,4);

    private static Map<Integer,Integer> map1 = ImmutableMap.<Integer,Integer>builder().put(5,6).put(7,8).build();

    public static void main(String[] args) {

        ThreadLocal
        map.put(5,6);
    }

}
