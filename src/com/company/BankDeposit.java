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


   public void print(){
        System.out.println("Название вклада: " + name + "\n Cтавка: " + rate + "\n Максимальный срок вклада: " + maxTime +
                "\n Минимальная сумма вклада: " + minDeposit);
    }

    @Override
    public String toString() {
        return "Название вклада: " + name + "\n Cтавка: " + rate + "\n Максимальный срок вклада: " + maxTime +
                "\n Минимальная сумма вклада: " + minDeposit;
    }

    public String getName() {
        return name;
    }

    Double calculate(Double deposit, int daysCount){
        Double result = deposit*(1 + (rate/100d)*(daysCount/365d));
        return result;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public Double getMinDeposit() {
        return minDeposit;
    }

    public void setName(String name) {
        this.name = name;
    }
}
