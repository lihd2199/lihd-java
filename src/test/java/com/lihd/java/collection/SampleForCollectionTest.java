package com.lihd.java.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: li_hd
 * @date: 2020-06-22 15:46
 **/
public class SampleForCollectionTest {

    /**
     * hash map key和value 都可以是null
     * key 会覆盖
     */
    @Test
    public void hashmap_test() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.get(null));
        map.put(null, "lihd");
        System.out.println(map.get(null));
        map.put("lihd", null);
        System.out.println(map.get("lihd"));
        map.put("lihd", "lihd");
        System.out.println(map.get("lihd"));

    }

    /**
     * hash table key 和 value 都不能为null
     */
    @Test
    public void hashtable_test() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null, "lihd");
        System.out.println(hashtable.get(null));
        hashtable.put("lihd", null);
        System.out.println(hashtable.get("lihd"));

    }


    /**
     * tree map和Comparator有关 默认key不允许null
     */
    @Test
    public void treeMap_test() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("lihd", null);
        System.out.println(treeMap.get("lihd"));
        treeMap.put(null, "lihd");
        System.out.println(treeMap.get(null));

    }


    /**
     * hash set  value 可以为null
     * 放多个会覆盖
     */
    @Test
    public void hashset_test() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(null);
        hashSet.add(null);
        System.out.println(hashSet.size());

    }


    /**
     * array list 可以放 null
     * 放几次有几个
     */
    @Test
    public void arraylist_test() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(null);
        System.out.println(arrayList.size());

    }


    /**
     * linked list 可以放 null
     * 放几个都可以
     */
    @Test
    public void linkedList_test() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(null);
        System.out.println(linkedList.size());

    }


    /**
     * ConcurrentHashMap key 和 value 都不可以是null
     */
    @Test
    public void currentHashMap_test(){
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null,null);
        System.out.println(concurrentHashMap.size());

    }


    /**
     * CopyOnWriteArrayList 可以放null
     */
    @Test
    public void copyOnWriteArrayList_test(){
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(null);
        copyOnWriteArrayList.add(null);
        System.out.println(copyOnWriteArrayList.size());

    }


}
