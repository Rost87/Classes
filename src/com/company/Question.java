package com.company;

import java.util.ArrayList;

public class Question {
    private String name;
    private ArrayList<String> answers;
    private int rightAnswer;

    public Question(String name, ArrayList<String> answers, int rightAnswer) {
        this.name = name;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }
}
