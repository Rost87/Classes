package com.company;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class BankDeposit {

    private String name;
    private Double rate;
    private Integer maxTime;
    private Double minDeposit;


    BankDeposit(String name, Double rate, Integer maxTime, Double minDeposit){
        this.name = name;
        this.rate = rate;
        this.maxTime = maxTime;
        this.minDeposit = minDeposit;
    }


   public void print(){
        System.out.println("Название вклада: " + name + "\n Cтавка: " + rate + "\n Максимальный срок вклада: " + maxTime +
                "\n Минимальная сумма вклада: " + minDeposit);
    }

   public String getName() {
        return name;
    }

    Double calculate(Double deposit, int daysCount){
        Double result = deposit*(1 + (rate/100d)*(daysCount/365d));
        return result;
    }

    static boolean checkBankDeposit(Double personDepositSumma, Integer personDepositTime, BankDeposit bD){
        return (personDepositSumma >= bD.minDeposit) && (personDepositTime <= bD.maxTime);
    }

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        ArrayList<BankDeposit> arrBankDeposits = new ArrayList<>();
        String personDepositName;
        Double personDepositSumma;
        Integer personDepositTime;
        BankDeposit personDeposit = new BankDeposit("Не выбран", 0d, 0, 0d);
        boolean rightDepositName = false;

        try (Scanner scanner = new Scanner(new File("Deposits.txt"))){
            while (scanner.hasNext()) {
                String[] depositInfo = scanner.nextLine().split(" ");
                BankDeposit bD = new BankDeposit(depositInfo[0], Double.parseDouble(depositInfo[1]), Integer.parseInt(depositInfo[2]), Double.parseDouble(depositInfo[3]));
                arrBankDeposits.add(bD);
            }

        }catch (Exception exc){

        }

        System.out.println("Доступные вклады: ");
        for (BankDeposit bD: arrBankDeposits) {
            System.out.println(bD.getName());
        }

        System.out.println("Выберите вклад");
        personDepositName = myScanner.nextLine();

        for (BankDeposit bD: arrBankDeposits) {
            if (bD.getName().equals(personDepositName)) {
                System.out.println("Описание вклада: ");
                bD.print();
                personDeposit = bD;
                rightDepositName = true;
                break;
            }

        }

        if (rightDepositName){

            System.out.println("Введите сумму вклада: ");
            personDepositSumma = Double.parseDouble(myScanner.nextLine());
            System.out.println("Введите кол-во дней вклада: ");
            personDepositTime = Integer.parseInt(myScanner.nextLine());

            if(checkBankDeposit(personDepositSumma, personDepositTime, personDeposit)){
                System.out.println("Сумма вклада, по истечении " + personDepositTime + " дней составит: " +personDeposit.calculate(personDepositSumma, personDepositTime));
            } else{
                System.out.println("Выбранный вклад " + personDeposit.getName() + " не соответствует введенным сумме или сроку вклада");
            }

        } else{
            System.out.println("Вклада, с указанным названием, не существует");
        }





    }
}
