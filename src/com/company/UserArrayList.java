package com.company;

import java.util.Arrays;

public class UserArrayList {
    private int[] a;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public UserArrayList(){
        a = new int[DEFAULT_CAPACITY];
    }

    public UserArrayList(int capacity){
        a = new int[capacity];
    }

    public void add(int data){
        if (size == a.length)
            a = Arrays.copyOf(a, size * 2);

        a[size] = data;
        size++;
    }

    public int getSize() {
        return size;
    }

    public int get(int i){
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        return a[i];
    }

    public void removeAt(int i){
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();

        for (int j = i; j < size - 1; j++) {
            a[j] = a[j+1];
        }
        a[size-1] = 0;
        size--;
    }

    //вставка элемента по индексу
    public void insert(int elem, int i){
        if (i < 0 || i > size)
            throw new IndexOutOfBoundsException();

        if(size == a.length){
            a = Arrays.copyOf(a, size * 2);
        }

        if(i != size) {
            for (int j = size; j > i; j--) {
                a[j] = a[j - 1];
            }
        }
            a[i] = elem;
            size++;

    }

    //удаление по значению возвращает boolean

    // удаление элемента из списка. Если 2-ой аргумент true - удалются все вхождения, иначе - первое
    public boolean removeEntry(int elem, boolean deleteAllEntries){
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (a[i] == elem){
                removeAt(i);
                i--;
                counter++;
                if (!deleteAllEntries)
                    break;

            }
        }
        return counter != 0;
    }



    // поиск индекса первого вхождения элемента
    public int indexOf(int elem){
        for (int i = 0; i < size; i++) {
            if (a[i] == elem){
                return i;
            }
        }
        return  -1;
    }

    // поиск индекса последнего вхождения элемента
    public int lastIndexOf(int elem){
        for (int i = size-1; i >=0 ; i--) {
            if (a[i] == elem){
                return i;
            }
        }
        return  -1;
    }

    // проверка наличия элемента в массиве
    public boolean contain(int elem){
        return indexOf(elem) >=0;
    }



}

//insert - вставка по индексу
//remove - удаление по значению первое вхождение
//removeAll - удаление по значению все вхождения
//indexOf, lastIndexOf, contains