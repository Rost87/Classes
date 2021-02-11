package com.company;

import java.io.*;
import java.util.*;


public class SystemTestMain {
    static ArrayList<Question> readQuestions(String courseName) {
        ArrayList<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("java.txt"))) {
            String name;
            while ((name = br.readLine()) != null) {
                ArrayList<String> answers = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    String answer = br.readLine();
                    answers.add(answer);
                }
                int rightAnswer = Integer.parseInt(br.readLine());
                Question question = new Question(name, answers, rightAnswer);
                questions.add(question);
            }
        } catch (IOException e) {

        }
        return questions;
    }


    //ф-ия для вывода вопросов и записи ответов пользователя
    static HashMap<Integer, Integer> outputQuestion(ArrayList<Question> questionsList) {
        HashMap<Integer, Integer> questionAnswer = new HashMap<>();
        try (Scanner scan = new Scanner(System.in)) {
            for (int i = 0; i < questionsList.size(); i++) {
                ArrayList<String> questions = questionsList.get(i).getAnswers();
                System.out.println(questionsList.get(i).getName());
                for (int j = 0; j < questions.size(); j++) {
                    System.out.println((j+1) + ". "+ questions.get(j));
                }
                System.out.println("Выберите вариант ответа: ");
                int answer;
                answer = scan.nextInt();
                questionAnswer.put(i + 1, answer);
            }
        } catch (Exception e) {
            System.out.println("Нужно выбрать цифру, соответствующую варианту ответа");
        }
        return questionAnswer;
    }

    static String estimate(ArrayList<Question> questionsList, HashMap<Integer, Integer> questionAnswer) {
        int rightAnswerNumber = 0;

        for (int i = 0; i < questionsList.size(); i++) {
            int rightAnswer = questionsList.get(i).getRightAnswer();
            if (rightAnswer == questionAnswer.get(i + 1)) {
                rightAnswerNumber++;
            }
        }
        double average = rightAnswerNumber / questionsList.size();

        if (average >= 0.8) {
            return "Курс успешно пройден";
        } else {
            return "Курс не пройден";
        }


    }


    //ф-ия для считывания из файла логинов и паролей всех пользователей
    static HashMap<String, String> loginPasswordInfo() {
        HashMap<String, String> loginPassword = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./PersonInfo/LoginPassword.txt"))) {
            String line;
            String[] personInfo;
            String login;
            String password;
            while ((line = br.readLine()) != null) {
                personInfo = line.split(" ");
                login = personInfo[0];
                password = personInfo[1];
                loginPassword.put(login, password);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return loginPassword;
    }

    //ф-ия для проверки введенного логина и пароля
    static boolean checkLoginPassword(HashMap<String, String> loginPassword, String login, String password) {
        String result;
        if (loginPassword.containsKey(login)) {
            result = loginPassword.getOrDefault(login, "Неверный пароль");
        } else {
            result = "Неверный логин";
        }
        return (!result.equals("Неверный пароль")) && (!result.equals("Неверный логин"));
    }

    //ф-ия для вывода всех курсов с вопросами
    static HashMap<String, String> courseList() {
        File path = new File("./Courses");
        String[] list;
        list = path.list();
        HashMap<String, String> extendedNameList = new HashMap<>();
        removeExtension(list, extendedNameList);
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);


        return extendedNameList;
    }

    static void removeExtension(String[] list, HashMap<String, String> extendedNameList) {
        for (int i = 0; i < list.length; i++) {
            int pointIndex = list[i].lastIndexOf('.');
            String fullCourseName = list[i];
            list[i] = list[i].substring(0, pointIndex);
            extendedNameList.put(list[i], fullCourseName);
        }

    }


    public static void main(String[] args) {

        //запрос логина и пароля при входе в систему
        Scanner scan = new Scanner(System.in);
        System.out.println("Для входа в систему введите логин: ");
        String login = scan.nextLine();
        System.out.println("Введите пароль: ");
        String password = scan.nextLine();
        HashMap<String, String> loginPassword = loginPasswordInfo();
        boolean check = checkLoginPassword(loginPassword, login, password);

        if (check) {
            HashMap<String, String> courseList = courseList();
            for (Map.Entry<String, String> pair : courseList.entrySet()) {
                System.out.println(pair.getKey());
            }

            System.out.println("Выберите курс, по которому вы хотите пройти тест: ");
            String courseName = scan.nextLine();
            HashMap<Integer, Integer> questionAnswer;// = new HashMap<>();
            if (courseList.containsKey(courseName)) {
                ArrayList<Question> questions = readQuestions(courseName);
                questionAnswer = outputQuestion(questions);
                String result = estimate(questions, questionAnswer);
                System.out.println(result);

            } else {
                System.out.println("Вы выбрали несуществующий курс");
            }

        } else {
            System.out.println("Указаны неверный логин или пароль");
        }




    }
}

//провести тестирование и выдать оценку
//создать файл с пользователями вида Логин Пароль
//при входе в систему запрашиваеи логин и пароль и проверяем
//создать папку с тестами, считываем все файл с папки и выдаем пользователю названия тестов
//после тестирования результат записать в файл в виде логин дата результат предмет
//при выборе теста сделать проверку прошло ли больше 24 часов со сдачи теста