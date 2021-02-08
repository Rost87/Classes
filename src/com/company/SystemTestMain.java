package com.company;

import java.io.*;
import java.util.*;

public class SystemTestMain {
    static ArrayList<Question> readQuestions(){
        ArrayList<Question> questions = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("java.txt")))
        {
           String name;
           while ((name = br.readLine()) != null){
               ArrayList<String> answers = new ArrayList<>();
               for (int i = 0; i < 4; i++) {
                   String answer = br.readLine();
                   answers.add(answer);
               }
               int rightAnswer = Integer.parseInt(br.readLine());
               Question question = new Question(name, answers, rightAnswer);
               questions.add(question);
           }
        }
        catch (IOException e){

        }
        return questions;
    }

    //ф-ия для считывания из файла логинов и паролей всех пользователей
    static HashMap<String, String> loginPasswordInfo(){
        HashMap<String, String> loginPassword = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("./PersonInfo/LoginPassword.txt") )){
            String line;
            String[] personInfo;
            String login;
            String password;
            while ((line = br.readLine()) != null){
                personInfo = line.split(" ");
                login = personInfo[0];
                password = personInfo[1];
                loginPassword.put(login, password);
            }
        }catch(IOException e){
            System.out.println("Ошибка");
        }
        return loginPassword;
    }

    //ф-ия для проверки введенного логина и пароля
    static boolean checkLoginPassword(HashMap<String, String> loginPassword, String login, String password){
        String result;
        if(loginPassword.containsKey(login)){
            result = loginPassword.getOrDefault(login, "Неверный пароль");
        }else{
           result = "Неверный логин";
        }
        return (!result.equals("Неверный пароль"))&&(!result.equals("Неверный логин"));
    }

    //ф-ия для вывода всех курсов с вопросами
    static String[] courseList(){
        File path = new File("./Courses");
        String[] list;
        list = path.list();
        removeExtension(list);
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    static void removeExtension(String[] list){
        for (int i = 0; i < list.length; i++) {
            int pointIndex = list[i].lastIndexOf('.');
            list[i] = list[i].substring(0, pointIndex);
        }

    }



    public static void main(String[] args) {


    //    ArrayList<Question> questions = readQuestions();
    //    System.out.println(questions);
       // Collections.shuffle(questions);
        //запрос логина и пароля при входе в систему
        Scanner scan = new Scanner(System.in);
        System.out.println("Для входа в систему введите логин: ");
        String login = scan.nextLine();
        System.out.println("Введите пароль: ");
        String password = scan.nextLine();
        HashMap<String, String> loginPassword = loginPasswordInfo();
        boolean check = checkLoginPassword(loginPassword, login, password);

        if(check){
          String[] courseList = courseList();
            for (String courseName : courseList) {
                System.out.println(courseName);
            }



        } else{
            System.out.println("Указаны неверный логин или пароль");
        }







      //  System.out.println(loginPassword);



        // вывод названий всех доступных тестов
        //dirList();

    }
}

//провести тестирование и выдать оценку
//создать файл с пользователями вида Логин Пароль
//при входе в систему запрашиваеи логин и пароль и проверяем
//создать папку с тестами, считываем все файл с папки и выдаем пользователю названия тестов
//после тестирования результат записать в файл в виде логин дата результат предмет
//при выборе теста сделать проверку прошло ли больше 24 часов со сдачи теста