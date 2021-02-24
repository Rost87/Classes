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
        for (int i = 3; i < 8; i++) {
            uLL.addLast(i);
        }

        int expectedValue = 7;

        assertEquals(expectedValue, uLL.get(4));
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

    @Test
    public void insertInMiddleTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.insert(11, 2);

        int expectedValue = 11;
        int expectedCount = 7;

        assertEquals(expectedValue, uLL.get(2));
        assertEquals(expectedCount, uLL.getCount());

    }


    @Test
    public void insertInMiddleTest2(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.insert(11, 5);

        int expectedValue = 11;
        int expectedCount = 7;

        assertEquals(expectedValue, uLL.get(5));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void insertInBeginTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.insert(11, 0);

        int expectedValue = 11;
        int expectedCount = 7;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void insertInEndTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.insert(11, 6);

        int expectedValue = 11;
        int expectedCount = 7;

        assertEquals(expectedValue, uLL.get(6));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeAtMiddleTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeAt(1);

        int expectedValue = 5;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(1));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeAtFirstElemTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeAt(0);

        int expectedValue = 4;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeAtLastElemTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeAt(5);

        int expectedValue = 7;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(4));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test // в списке есть повторяющиеся значения
    public void removeFirstEntryTest2(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.addLast(4);
        uLL.removeFirstEntry(4);

        int expectedValue = 5;
        int expectedCount = 6;

        assertEquals(expectedValue, uLL.get(1));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeFirstEntryInMiddleTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeFirstEntry(4);

        int expectedValue = 5;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(1));
        assertEquals(expectedCount, uLL.getCount());

    }


    @Test
    public void removeFirstEntryInBeginTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeFirstEntry(3);

        int expectedValue = 4;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeFirstEntryInEndTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.removeFirstEntry(8);

        int expectedValue = 7;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(4));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeAllTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }

        uLL.removeAll(4);

        int expectedValue = 5;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(1));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeAllSeveralElemTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }
        uLL.addLast(3);
        uLL.addLast(11);
        uLL.addLast(4);

        uLL.removeAll(4);

        int expectedValue1 = 5;
        int expectedValue2 = 11;
        int expectedCount = 7;

        assertEquals(expectedValue1, uLL.get(1));
        assertEquals(expectedValue2, uLL.get(6));
        assertEquals(expectedCount, uLL.getCount());

    }


    @Test
    public void removeFirstTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }

        uLL.removeFirst();

        int expectedValue = 4;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(0));
        assertEquals(expectedCount, uLL.getCount());

    }

    @Test
    public void removeFirstTest2(){
        UserLinkedList uLL = new UserLinkedList();

        uLL.addLast(5);

        uLL.removeFirst();


        int expectedCount = 0;

        assertEquals(expectedCount, uLL.getCount());

    }


    @Test
    public void removeLastTest(){
        UserLinkedList uLL = new UserLinkedList();

        for (int i = 3; i < 9; i++) {
            uLL.addLast(i);
        }

        uLL.removeLast();

        int expectedValue = 7;
        int expectedCount = 5;

        assertEquals(expectedValue, uLL.get(4));
        assertEquals(expectedCount, uLL.getCount());

    }

}