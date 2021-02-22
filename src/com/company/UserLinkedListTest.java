package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserLinkedListTest {

    @Test
    public void getBeginElementTest(){
        UserLinkedList uLL = new UserLinkedList();
        for (int i = 3; i < 15; i++) {
            uLL.addLast(i);
        }

        int expectedValue = 5;

        assertEquals(expectedValue, uLL.get(2));
    }

    @Test
    public void getEndElementTest(){
        UserLinkedList uLL = new UserLinkedList();
        for (int i = 3; i < 15; i++) {
            uLL.addLast(i);
        }

        int expectedValue = 12;

        assertEquals(expectedValue, uLL.get(10));
    }

    @Test
    public void getElementTest(){
        UserLinkedList uLL = new UserLinkedList();
        uLL.addLast(123);

        int expectedValue = 123;

        assertEquals(expectedValue, uLL.get(0));
    }



    @Test
    public  void maxElementTest(){
        UserLinkedList uLL = new UserLinkedList();
        int expectedMaxElement = 4;
        for (int i = 0; i < 5; i++) {
            uLL.addLast(i);
        }
        uLL.addLast(2);

        assertEquals(expectedMaxElement, uLL.maxElement());
    }

    @Test
    public void addFirstTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 7; i++) {
            uLL.addLast(i);
        }
        uLL.addFirst(11);

        int expectedValue = 11;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }


    @Test
    public void addFirstToEmptyListTest(){
        UserLinkedList uLL = new UserLinkedList();

        uLL.addFirst(11);

        int expectedValue = 11;
        int expectedCount = 1;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }


}