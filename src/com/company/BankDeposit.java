package com.company;

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

    @Override
   public String toString(){
        return "Название вклада: " + name + "\n Cтавка: " + rate + "\n Максимальный срок вклада: " + maxTime +
                "\n Минимальная сумма вклада: " + minDeposit;
    }

    Double calculate(Double deposit, int daysCount){
        return deposit + rate*(daysCount/365);
    }


    public static void main(String[] args) {

    }
}
