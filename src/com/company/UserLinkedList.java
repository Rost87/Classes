package com.company;

import java.util.LinkedList;

public class UserLinkedList {
    public static class Node
    {
        private int data;
        private Node next;
        private Node pred;

        public Node(int data){
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public void addLast(int item) {
        Node p = new Node(item);

        if (head == null){
            head = tail = p;
        }
        else {
            tail.next = p;
            p.pred = tail;
            tail = p;
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    public int maxElement(){
        Node p = head;
        int max = p.data;
        while (p != null){
            if (p.data > max)
                max = p.data;
            p = p.next;
        }
        return max;
    }

    public int get(int index){
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        if (count == 1) {
            Node p = head;
            return p.data;
        }
        if (index < count / 2){
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        }else {
            Node p = tail;

            for (int i = 0; i < count - index; i++) {
                p = p.pred;
            }
            return p.data;
        }
    }

    public void addFirst(int elem){
        Node p = new Node(elem);
        if (head == null){
            head = tail = p;

        } else{
            head.pred = p;
            p.next = head;
            head = p;
        }
        count++;
    }


}

//addFirst
//insert
//removeAt
//removeAll, removeFirstEntry
//removeFirst, removeLast