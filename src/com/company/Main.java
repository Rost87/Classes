package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String answer;





        try (Scanner scan = new Scanner(System.in)) {

            for (int i = 0; i <3; i++) {
                answer = scan.nextLine();//scan.nextInt();
                System.out.println("Ответ: " + answer);
            }
        } catch (Exception e) {
            System.out.println("Нужно выбрать цифру, соответствующую варианту ответа");
        }
    }


}
