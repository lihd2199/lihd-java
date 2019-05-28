package com.lihd.java.tree;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;

public class RedAndBlackTree<K,V> {


    private static RedAndBlackTree.Entry root = null;

    public static void main(String[] args) {

        RedAndBlackTree redAndBlackTree = new RedAndBlackTree();
        redAndBlackTree.put(5,3);
        redAndBlackTree.put(1,2);
        redAndBlackTree.put(7,3);
        redAndBlackTree.put(3,3);
        redAndBlackTree.put(12,3);
        redAndBlackTree.put(8,3);
        redAndBlackTree.put(0,3);
        redAndBlackTree.put(4,3);
//        redAndBlackTree.put(12,3);
//        redAndBlackTree.put(8,3);
        System.out.println(redAndBlackTree.toString());

    }


    private void put(K key, V value){

        if(key == null || value == null){
            throw new NullPointerException();
        }
        if(root == null){
            root = new RedAndBlackTree.Entry<>(key, value, null);
            return ;
        }
        Entry comp = root;
        Entry parent ;
        int comareInt ;
        do{
            parent = comp;
            comareInt = compare((K) comp.key,key);
            if(comareInt > 0){
                comp = comp.left ;
            }else if (comareInt < 0) {
                comp = comp.right ;
            }else{
                comp.value = value;
                return ;
            }

        }while (comp != null);

        Entry entry = new Entry(key,value,parent);

        if(comareInt > 0){
            parent.left = entry;
        }else{
            parent.right = entry;
        }

        dealTree(root,entry);

    }

    private void dealTree(Entry root, Entry x) {

        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Entry<K,V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        //rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    //rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        //rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    //rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;

    }

    private void setColor(Entry entry, boolean color) {
        entry.color = color;
    }

    private boolean colorOf(Entry<K,V> y) {
        return y.color;
    }

    private Entry rightOf(Entry entry) {
        return entry.right;
    }

    private Entry leftOf(Entry entry) {
        return entry.left;
    }

    private Entry parentOf(Entry x) {
        return x.parent;
    }


    public String toString() {

        if(root == null){
            return "{}";
        }

        theInOrderTraversal(root);

        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        iterator(root,sb);
        sb.append("}");
        return sb.toString();

    }

    private void iterator(Entry root, StringBuffer sb) {

            if (root.left != null) {
                iterator(root.left,sb);
            }

            sb.append(root);
            sb.append(";");

            if (root.right != null) {
                iterator(root.right,sb);
            }

    }

    private void theInOrderTraversal(Entry root) {  //中序遍历
        Stack<Entry> stack = new Stack<>();
        Entry node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);   //直接压栈
                node = node.left;
            } else {
                node = stack.pop(); //出栈并访问
                System.out.println(node);
                node = node.right;
            }
        }
    }






    private int compare(K key1,K key2){
        return ((Comparable<? super K>)key1).compareTo((K)key2);
    }


    private static final boolean RED   = false;
    private static final boolean BLACK = true;


    static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        RedAndBlackTree.Entry<K,V> left;
        RedAndBlackTree.Entry<K,V> right;
        RedAndBlackTree.Entry<K,V> parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        Entry(K key, V value, RedAndBlackTree.Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Returns the key.
         *
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value associated with the key.
         *
         * @return the value associated with the key
         */
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value currently associated with the key with the given
         * value.
         *
         * @return the value associated with the key before this method was
         *         called
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;

            return valEquals(key,e.getKey()) && valEquals(value,e.getValue());
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }


    static final boolean valEquals(Object var0, Object var1) {
        return Objects.equals(var0, var1);
    }


}
