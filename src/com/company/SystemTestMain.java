package com.company;

import java.io.*;
import java.util.*;


public class SystemTestMain {


    static ArrayList<Question> readQuestions(String courseName, String pathName) {
        ArrayList<Question> questions = new ArrayList<>();
        String fullName = pathName + courseName;
        try (BufferedReader br = new BufferedReader(new FileReader(fullName))) {
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
                ArrayList<String> answers = questionsList.get(i).getAnswers();
                String question = questionsList.get(i).getName();
                System.out.println(question);
                for (int j = 0; j < answers.size(); j++) {
                    System.out.println(answers.get(j));
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

    //возвращаем процент правильных ответов
    static double percentageRightAnswer(ArrayList<Question> questionsList, HashMap<Integer, Integer> questionAnswer) {
        int rightAnswerNumber = 0;

        for (int i = 0; i < questionsList.size(); i++) {
            int rightAnswer = questionsList.get(i).getRightAnswer();
            if (rightAnswer == questionAnswer.get(i + 1)) {
                rightAnswerNumber++;
            }
        }
        double averagePercent = (double) rightAnswerNumber / questionsList.size();

        return Math.round(averagePercent*100);
    }

    static int estimate(double percentRightAnswer){
        int estimate = 2;
        if (percentRightAnswer > 80){
            estimate = 5;
        } else if (percentRightAnswer > 60 && percentRightAnswer < 80){
            estimate = 4;
        } else if (percentRightAnswer > 40 && percentRightAnswer < 60){
            estimate = 3;
        }
        return estimate;
    }


    //ф-ия для считывания из файла логинов и паролей всех пользователей
    static HashMap<String, String> loginPasswordInfo(String pathLoginPassword) {
        HashMap<String, String> loginPassword = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathLoginPassword))) {
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
            System.out.println(e);
        }
        return loginPassword;
    }

    //ф-ия для проверки введенного логина и пароля
    static boolean checkLoginPassword(HashMap<String, String> loginPassword, String login, String password) {

        return loginPassword.containsKey(login) && loginPassword.get(login).equals(password);
    }

    //ф-ия для вывода всех курсов с вопросами
    static HashMap<String, String> courseList(String pathName) {
        File path = new File(pathName);
        String[] list;
        list = path.list();
        HashMap<String, String> extendedNameList;
        extendedNameList = removeExtension(list);
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        return extendedNameList;
    }

    static HashMap<String, String>  removeExtension(String[] list) {
        HashMap<String, String> extendedNameList = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            int pointIndex = list[i].lastIndexOf('.');
            String fullCourseName = list[i];
            list[i] = list[i].substring(0, pointIndex);
            extendedNameList.put(list[i], fullCourseName);
        }
        return extendedNameList;
    }

    // ф-ия, возвращающая true, если последний раз курс был пройден пользователем более 24 ч назад
    static boolean checkCourseDate(String pathLoginCourseDate, String personLogin, String personCourseName, long personMillis){
        File file = new File(pathLoginCourseDate);

        try{
            // если файл создан
            if(!file.createNewFile()){
                BufferedReader br = new BufferedReader(new FileReader(pathLoginCourseDate));
                String line, login, courseName;
                long millisec = 0;
                String[] info;
                while((line = br.readLine()) != null){
                    info = line.split(" ");
                    login = info[0];
                    courseName = info[1];
                    if (personLogin.equals(login) && personCourseName.equals(courseName))
                        millisec = Long.parseLong(info[2]);
                }
                if((personMillis - millisec) > 24*60*60*1000)
                    return true;
            } else{
                  return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }

       return false;
    }

    // ф-ия для записи информации о дате прохождения курса
    static void writeCourseDate(String pathLoginCourseDate, String personLogin, String personCourseName, long personMillis){
        File file = new File(pathLoginCourseDate);
        // использую конструктор, для записи в конец файла
        try(FileWriter writer = new FileWriter(file, true);){
            writer.append(personLogin + " " + personCourseName + " " + personMillis + "\n");
        }catch(Exception e){
            System.out.println(e);
        }

    }


    public static void main(String[] args) {

        // путь к папке, где хранятся курсы
        String pathCourse = "./Courses/";

        // путь к файлу, где хранятся данные о времени прохождения курсов каждого из пользователей
        String pathLoginCourseDate = "./PersonInfo/LoginCourseDate.txt";

        // путь к файлу, где хранятся данные о логинах и паролях пользователей
        String pathLoginPassword = "./PersonInfo/LoginPassword.txt";

        //запрос логина и пароля при входе в систему
        Scanner scan = new Scanner(System.in);
        System.out.println("Для входа в систему введите логин: ");
        String login = scan.nextLine();
        System.out.println("Введите пароль: ");
        String password = scan.nextLine();
        HashMap<String, String> loginPassword = loginPasswordInfo(pathLoginPassword);
        boolean check = checkLoginPassword(loginPassword, login, password);

        if (check) {
            HashMap<String, String> courseList = courseList(pathCourse);
            for (Map.Entry<String, String> pair : courseList.entrySet()) {
                System.out.println(pair.getKey());
            }

            System.out.println("Выберите курс, по которому вы хотите пройти тест: ");
            String courseName = scan.nextLine();
            HashMap<Integer, Integer> questionAnswer;
            if (courseList.containsKey(courseName)) {
                Calendar date = new GregorianCalendar();
                long timeNow = date.getTimeInMillis();
                //проверка даты прохождения курсов
                if (checkCourseDate(pathLoginCourseDate, login, courseName, timeNow)){
                    ArrayList<Question> questions = readQuestions(courseList.get(courseName), pathCourse);
                    questionAnswer = outputQuestion(questions);
                    // процент правильных ответов
                    double percentRightAnswer = percentageRightAnswer(questions, questionAnswer);
                    // оценка по 5-ти бальной шкале
                    int estimate = estimate(percentRightAnswer);
                    System.out.println("Процент правильных ответов составил: " + percentRightAnswer);
                    System.out.println("Ваша оценка (по 5-ти бальной шкале): " + estimate);
                    writeCourseDate(pathLoginCourseDate, login, courseName, date.getTimeInMillis());

                } else{
                    System.out.println("С момента последнего прохождения курса прощло менее 24 ч");

                }
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