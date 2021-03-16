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

    // ф-ия поиска узла с начала или с конца списка (зависит от переменной begin)
    public Node findNodeFrom(int index, boolean begin) {
        Node p;
        if (begin) {
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

        return p;
    }

    public int get(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        if (count == 1) {
            Node p = head;
            return p.data;
        }
        if (index < count / 2) {
            Node p = findNodeFrom(index, true);
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

    public void insert(int elem, int index) {
        if (index < 0 || index > count)
            throw new IndexOutOfBoundsException();

        Node pNew = new Node(elem);

        if (index == count) {
            addLast(elem);
        } else if (index == 0) {
            addFirst(elem);
        } else {
            Node p, pAfter, pBefore;
            if (index < count / 2) {
                p = findNodeFrom(index, true);
                pAfter = p;
                pBefore = pAfter.pred;

            } else {
                p = findNodeFrom(index, false);
                pBefore = p;
                pAfter = pBefore.next;
            }

            pNew.next = pAfter;
            pNew.pred = pBefore;
            pAfter.pred = pNew;
            pBefore.next = pNew;
            count++;
        }
    }

    //использовать removeLast, First
    //убрать boolean
    //получение нужного узла с начала и с конца в отдельные функциц
    public void removeAt(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        Node p, pBefore, pAfter;

        if (index == 0) {
            removeFirst();
        } else if (index == count - 1) {
            removeLast();
        } else {
            if (index < count / 2) {
                p = findNodeFrom(index, true);
            } else {
                p = findNodeFrom(index, false);
            }
            pAfter = p.next;
            pBefore = p.pred;
            pAfter.pred = pBefore;
            pBefore.next = pAfter;
            p.next = p.pred = null;
            count--;
        }
    }

    //убрать removeAt
    public boolean removeFirstEntry(int elem) {

        if (count == 0)
            return false;

        Node p = head;
        int i = 0;
        do {
            if (p.data == elem) {
                removeAt(i);
                return true;
            }
            i++;
        } while ((p = p.next) != null);

        return false;
    }


    public void removeAll(int elem) {
        if (count != 0) {
            Node p = head;
            Node pSave = p;
            // используется для удаления элемента по его индексу
            int i = 0;

            do {
                pSave = pSave.next;
                if (p.data == elem) {
                    removeAt(i);
                    i--;
                }
                p = pSave;
                i++;
            } while (pSave != null);

        }
    }


    public void removeFirst() {
        if (count != 0) {
            if (count == 1) {
                head = tail = null;
            } else {
                head = head.next;
                head.pred = null;
            }
            count--;
        }
    }

    public void removeLast() {
        if (count != 0) {
            if (count == 1) {
                head = tail = null;
            } else {
                tail = tail.pred;
                tail.next = null;
            }
            count--;
        }
    }

}

//addFirst
//insert
//removeAt
//removeAll, removeFirstEntry
//removeFirst, removeLast