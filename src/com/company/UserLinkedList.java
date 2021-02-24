package com.company;

import java.util.LinkedList;

public class UserLinkedList {
    public static class Node {
        private int data;
        private Node next;
        private Node pred;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public void addLast(int item) {
        Node p = new Node(item);

        if (head == null) {
            head = tail = p;
        } else {
            tail.next = p;
            p.pred = tail;
            tail = p;
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    public int maxElement() {
        Node p = head;
        int max = p.data;
        while (p != null) {
            if (p.data > max)
                max = p.data;
            p = p.next;
        }
        return max;
    }

    public int get(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        if (count == 1) {
            Node p = head;
            return p.data;
        }
        if (index < count / 2) {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        } else {
            Node p = tail;

            for (int i = 0; i < count - index - 1; i++) {
                p = p.pred;
            }
            return p.data;
        }
    }

    public void addFirst(int elem) {
        Node p = new Node(elem);
        if (head == null) {
            head = tail = p;

        } else {
            head.pred = p;
            p.next = head;
            head = p;
        }
        count++;
    }

    public boolean insert(int elem, int index) {
        Node pNew = new Node(elem);

        if (index < 0 || index > count)
            throw new IndexOutOfBoundsException();

        if (index == count) {
            addLast(elem);
            return true;
        }

        if (index == 0) {
            addFirst(elem);
            return true;
        }

        Node pAfter, pBefore;
        if (index < count / 2) {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            pAfter = p;
            pBefore = pAfter.pred;

        } else {
            Node p = tail;
            for (int i = 0; i < count - index; i++) {
                p = p.pred;
            }
            pBefore = p;
            pAfter = pBefore.next;

        }

        pNew.next = pAfter;
        pNew.pred = pBefore;
        pAfter.pred = pNew;
        pBefore.next = pNew;
        count++;

        return true;

    }

    public boolean removeAt(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        Node p, pBefore, pAfter;

        if (index == 0){
            head = head.next;
            head.pred = null;
            count--;
            return true;

        }

        if (index == count - 1){
            tail = tail.pred;
            tail.next = null;
            count--;
            return true;
        }

        if (index < count / 2) {
            p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = 0; i < count - index; i++) {
                p = p.pred;
            }
        }
        pAfter = p.next;
        pBefore = p.pred;
        pAfter.pred = pBefore;
        pBefore.next = pAfter;
        p.next = p.pred = null;
        count--;

        return true;

    }

    public boolean removeFirstEntry(int elem){

        if (count == 0)
            return false;

        Node p = head;
        int i = 0;
        do{
            if(p.data == elem){
                removeAt(i);
                return true;
            }
            i++;
        }while((p = p.next) != null);

        return false;
    }


    // не работает
    public boolean removeAll(int elem){
        if (count == 0)
            return false;

        Node p = head;
        Node pSave = p;
        // используется для удаления элемента по его индексу
        int i = 0;
        // counter = 0, если не было ни одного удаления
        int counter = 0;
        do{
            pSave = pSave.next;
            if(p.data == elem){
                removeAt(i);
                counter++;
                i--;
            }
            p = pSave;
            i++;
        }while(pSave != null);

       return counter != 0;
    }


    public boolean removeFirst(){
        if (count == 0)
            return false;

        if(count == 1){
            head = tail = null;
        }else{
            head = head.next;
            head.pred = null;
        }
        count--;
        return true;
    }

    public boolean removeLast(){
        if (count == 0)
            return false;

        if(count == 1){
            head = tail = null;
        }else{
            tail = tail.pred;
            tail.next = null;
        }
        count--;
        return true;
    }

}

//addFirst
//insert
//removeAt
//removeAll, removeFirstEntry
//removeFirst, removeLast