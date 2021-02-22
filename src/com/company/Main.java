package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    static boolean checkBankDeposit(Double personDepositSumma, Integer personDepositTime, BankDeposit bD){
        return (personDepositSumma >= bD.getMinDeposit()) && (personDepositTime <= bD.getMaxTime());
    }

    static BankDeposit findDeposit(ArrayList<BankDeposit> arrBankDeposits, String personDepositName)
    {
        for (BankDeposit bD: arrBankDeposits) {
            if (bD.getName().equals(personDepositName)) {
                return bD;
            }
        }
        return null;
    }


    public static void main(String[] args) {



        Scanner myScanner = new Scanner(System.in);
        ArrayList<BankDeposit> arrBankDeposits = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Deposits.txt"))){
            while (scanner.hasNext()) {
                String[] depositInfo = scanner.nextLine().split(" ");
                BankDeposit bD = new BankDeposit(depositInfo[0], Double.parseDouble(depositInfo[1]),
                        Integer.parseInt(depositInfo[2]), Double.parseDouble(depositInfo[3]));
                arrBankDeposits.add(bD);
            }

        }catch (Exception exc){

        }

        System.out.println("Доступные вклады: ");
        for (BankDeposit bD: arrBankDeposits) {
            System.out.println(bD.getName());
        }

        System.out.println("Выберите вклад");
        String personDepositName = myScanner.nextLine();
        BankDeposit deposit = findDeposit(arrBankDeposits, personDepositName);

        if (deposit != null){
            System.out.println(deposit);
            System.out.println("Введите сумму вклада: ");
            Double personDepositSumma = myScanner.nextDouble();
            System.out.println("Введите кол-во дней вклада: ");
            Integer personDepositTime = myScanner.nextInt();

            if(checkBankDeposit(personDepositSumma, personDepositTime, deposit)){
                System.out.println("Сумма вклада, по истечении " + personDepositTime + " дней составит: " +deposit.calculate(personDepositSumma, personDepositTime));
            } else{
                System.out.println("Выбранный вклад " + deposit.getName() + " не соответствует введенным сумме или сроку вклада");
            }

        } else{
            System.out.println("Вклада, с указанным названием, не существует");
        }
    }
}
