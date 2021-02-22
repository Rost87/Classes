package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserArrayListTest {

    @Test
    public void addOneTest() {
        UserArrayList list = new UserArrayList();
        int expectedValue = 10;
        int expectedSize = 1;

        list.add(expectedValue);

        assertEquals(expectedSize, list.getSize());
        assertEquals(expectedValue, list.get(0));
    }

    @Test
    public void addFewTest() {
        UserArrayList list = new UserArrayList();
        int[] expected = {2,7,1};
        int expectedSize = 3;

        for (int i = 0; i < expected.length; i++) {
            list.add(expected[i]);
        }

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOutOfRangeTest(){
        UserArrayList list = new UserArrayList();
        list.add(10);

        int value = list.get(1);
    }

    void equalsArrayAndList(int[] expected, UserArrayList list){
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }

    @Test
    public void removeAtTest() {
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,1};
        int[] expected = {2,1};
        int expectedSize = 2;

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.removeAt(1);

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test
    public void insertTest (){
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,1};
        int[] expected = {2,5,7,1};
        int expectedSize = 4;
        int elem = 5;
        int j = 1;

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.insert(elem, j);

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test
    public void insertEndTest (){
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,1};
        int[] expected = {2,7,1,5};
        int expectedSize = 4;
        int elem = 5;
        int j = 3;

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.insert(elem, j);

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test
    public void insertBeginTest (){
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,1};
        int[] expected = {5,2,7,1};
        int expectedSize = 4;
        int elem = 5;
        int j = 0;

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.insert(elem, j);

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test
    public void insertFullArrayTest (){
        UserArrayList list = new UserArrayList(3);
        int[] a = {2,7,1};
        int[] expected = {2,5,7,1};
        int expectedSize = 4;
        int elem = 5;
        int j = 1;

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.insert(elem, j);

        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);
    }

    @Test
    public void removeFirstEntry(){
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,3,1};
        int elem = 3;
        int[] expected = {2,7,1};
        int expectedSize = 3;
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.removeEntry(elem, false);
        assertEquals(expectedSize, list.getSize());
        equalsArrayAndList(expected, list);

    }

    @Test
    public void removeAllEntry(){
        UserArrayList list = new UserArrayList();
        int[] a = {2,7,7,1,7};
        int elem = 7;
        int[] expected = {2,1};
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

        list.removeEntry(elem, true);
        assertEquals(expected.length, list.getSize());
        equalsArrayAndList(expected, list);

    }


}