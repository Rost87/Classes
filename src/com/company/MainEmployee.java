package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

enum MENU{SHOWMAINMENU, SHOWALLEMPLOYEES, SHOWEMPLOYEETASK, CREATETASK,
    CHANGETASK, CREATEEMPLOYEE, REMOVEEMPLOYEE, SHOWREMOVEDEMPLOYEES,
    SHOWTASKSREMOVEDEMPLOYEE, SHOWMYTASKS, CHANGESTATUSFORMYTASK}

public class MainEmployee {
    static int employeeId;
    static int taskId;
    static int choice;

    //проверка правильности введенных данных (email и пароль)
    public static Employee checkData(String email, String password, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                if (employee.getPassword().equals(password)) {
                    return employee;
                }
                break;
            }
        }
        return null;
    }

    //выбор доступных действий для сотрудника/директора
    public static void showOption(Employee employee, MENU menu) {
        HashMap<Integer, String> employeeTaskList = new HashMap<>();
        employeeTaskList.put(1, "Просмотр списка своих задач");
        employeeTaskList.put(2, "Редактирование статуса задачи");
        employeeTaskList.put(3, "Вывод списка всех сотрудников: фамилия имя идентификатор");
        employeeTaskList.put(4, "Просмотр задач выбранного сотрудника");
        employeeTaskList.put(5, "Создание задачи для выбранного сотрудника");
        employeeTaskList.put(6, "Создание сотрудника");
        employeeTaskList.put(7, "Удаление сотрудника");
        employeeTaskList.put(8, "Просмотр удаленных сотрудников");
        employeeTaskList.put(9, "Редактирование задачи для выбранного сотрудника");
        employeeTaskList.put(0, "Выход");

        if (employee.getRole().equals(Role.EMPLOYEE)) {
            switch (menu) {
                case SHOWMAINMENU: {
                    System.out.println("0: " + employeeTaskList.get(0));
                    System.out.println("1: " + employeeTaskList.get(1));
                    break;
                }
                case CHANGESTATUSFORMYTASK: {
                    System.out.println("0: " + employeeTaskList.get(0));
                    System.out.println("2: " + employeeTaskList.get(2));
                    break;
                }
            }
        }

        if(employee.getRole().equals(Role.DIRECTOR)){
            switch (menu){
                case SHOWMAINMENU: {
                    System.out.println("0: " + employeeTaskList.get(0));
                    System.out.println("3: " + employeeTaskList.get(3));
                    System.out.println("6: " + employeeTaskList.get(6));
                    System.out.println("7: " + employeeTaskList.get(7));
                    System.out.println("8: " + employeeTaskList.get(8));
                    break;
                }
                case SHOWALLEMPLOYEES:{
                    System.out.println("0: " + employeeTaskList.get(0));
                    System.out.println("4: " + employeeTaskList.get(4));
                    System.out.println("5: " + employeeTaskList.get(5));
                    System.out.println("6: " + employeeTaskList.get(6));
                    System.out.println("7: " + employeeTaskList.get(7));
                    System.out.println("8: " + employeeTaskList.get(8));
                    break;
                }
                case SHOWEMPLOYEETASK:{
                    System.out.println("9: " + employeeTaskList.get(9));
                    break;
                }
            }
        }
        readChoice();
    }

    public static void readChoice(){
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
    }

    public static void showOffer(MENU menu){
        switch(menu){
            case CHANGESTATUSFORMYTASK:{
                System.out.println("Для редактирования статуса задачи, выберите id задачи.\nДля выхода наберите 0");
                readChoice();
            }
        }
    }



    //запись информации в базу даных
    public static boolean saveInfo(Employee employee, Task task) {
        int id = employee.getId();
        int taskId = task.getTaskId();
        String description = task.getDescription();
        int expectedTime = task.getExpectedTime();
        Status status = task.getStatus();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Employee.path, true))) {
            bw.append("\n");
            bw.append(id + "|" + taskId + "|" + description + "|" + expectedTime + "|" + status);

        } catch (Exception exc) {
            System.out.println(exc);
        }
        return true;
    }

    //изменение статуса задачи
    public static void changeTaskStatus(Employee employee, int taskId) {
        System.out.println("Id задачи: " + taskId);
        ArrayList<Task> tasks = employee.getTasks();
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                System.out.println("Описание задачи: " + task.getDescription());
                System.out.println("Текущий статус задачи: " + task.getStatus());
                System.out.println("Выберите новый стаус задачи.");
                System.out.println("Возможные варианты: ");
                for (Status status : Status.values()) {
                    System.out.println(status);
                }

                Scanner scanner = new Scanner(System.in);
                String newStatus = scanner.nextLine();

                if (Status.getStatus(newStatus)) {
                    task.setStatus(Status.valueOf(newStatus));
                    saveInfo(employee, task);
                    System.out.println("Статус задачи изменен");
                } else {
                    System.out.println("Выбран несуществующий статус");
                }

                break;
            }
        }
    }

    public static void showEmployeeTasks(Employee employee) {
        ArrayList<Task> tasks = employee.getTasks();
        System.out.println("Id: Описание задачи(cтатус)");
        for (Task task : tasks) {
            System.out.println(task.getTaskId() + ": " + task.getDescription() + "(" + task.getStatus() + ")");
        }
    }

    public static void showAllEmployees(ArrayList<Employee> employees){
        for (Employee employee : employees) {
            System.out.println("id: " + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName());
        }
    }


    public static void showInfoForChoice(ArrayList<Employee> employees, Employee employee) {

        if (employee.getRole().equals(Role.EMPLOYEE)) {
            switch (choice) {
                // показать все задачи сотрудника
                case 1: {
                    showEmployeeTasks(employee);
                    showOption(employee, MENU.CHANGESTATUSFORMYTASK);
                    break;
                }
                // редактирование статуса выбранной задачи
                case 2: {
                    showOffer(MENU.CHANGESTATUSFORMYTASK);
                    if (choice != 0) {
                       changeTaskStatus(employee, choice);
                       showOption(employee, MENU.SHOWMAINMENU);
                    }
                    break;
                }
                case 0: {
                    System.out.println("Выход выполнен");
                    break;
                }
            }
        }

        if(employee.getRole().equals(Role.DIRECTOR)){
            switch(choice){
                case 0: {
                    System.out.println("Выход выполнен");
                    break;
                }
                case 1: {
                    showAllEmployees(employees);
                }
            }
        }

    }

    public static void welcome(Employee employee) {
        System.out.println("Здравствуйте, " + employee.getLastName() + " " + employee.getFirstName());
        System.out.println("Выбор доступных действий: ");
    }


    // чтение из файла инф-ии о сотрудниках и создание списка сотрудников.
    public static ArrayList<Employee> readEmployee(String employeesPath, HashMap<Integer, Employee> idAndEmployee) {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(employeesPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] employeeInfo = line.split(" ");
                String firstName = employeeInfo[0];
                String lastName = employeeInfo[1];
                String phone = employeeInfo[2];
                String email = employeeInfo[3];
                String password = employeeInfo[4];

                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                Date date = df.parse(employeeInfo[5]);
                Calendar birthday = new GregorianCalendar();
                birthday.setTime(date);

                Role role = Role.valueOf(employeeInfo[6]);
                int employeeId = Integer.parseInt(employeeInfo[7]);
                Employee employee = new Employee(firstName, lastName, phone, email, password, birthday, role, employeeId);
                employees.add(employee);
                idAndEmployee.put(employeeId, employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    //чтение из файла задач и добавление их сотруднику
    public static void readTasks(String tasksPath, HashMap<Integer, Employee> idAndEmployee) {
        try (BufferedReader br = new BufferedReader(new FileReader(tasksPath))) {
            String line;
            br.readLine();
            //создаю hashmap, чтобы избежать появление задач с одним и тем же id (например, после редактирования статуса)
            HashMap<Integer, Task> taskIdAndTask = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] tasksInfo = line.split("\\|");
                int employeeId = Integer.parseInt(tasksInfo[0]);
                int taskId = Integer.parseInt(tasksInfo[1]);
                String description = tasksInfo[2];
                int expectedTime = Integer.parseInt(tasksInfo[3]);
                String status = tasksInfo[4];

                Task task = new Task(employeeId, taskId, description, expectedTime, Status.valueOf(status));
                Employee employee = idAndEmployee.get(employeeId);
                employee.addTask(task);

                /*taskIdAndTask.put(taskId, task);

                ArrayList<Task> tasks = employee.getTasks();
                tasks.add(taskIdAndTask.get(taskId));
                employee.setTasks(tasks);*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // считывание списка сотрудников из файла
    public static ArrayList<Employee> readEmployeeInfo(String employeesPath, String tasksPath) {

        HashMap<Integer, Employee> idAndEmployee = new HashMap<>();
        ArrayList<Employee> employees = readEmployee(employeesPath, idAndEmployee);
        readTasks(tasksPath, idAndEmployee);


        return employees;
    }


    public static void main(String[] args) {
        String employeesPath = "./PersonInfo/Employees.txt";
        String tasksPath = "./PersonInfo/EmployeesTasks.txt";

        ArrayList<Employee> employees = readEmployeeInfo(employeesPath, tasksPath);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите e-mail:");
        String mail = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        Employee employee = checkData(mail, password, employees);


        if (employee != null) {
            welcome(employee);
            showOption(employee, MENU.SHOWMAINMENU);

            while(choice != 0){
                showInfoForChoice(employees, employee);
              //  choice = scanner.nextInt();

            }

            /*if (employee.getRole().equals(Role.EMPLOYEE)) {
                showInfoForChoice(employees, employee, choice);
                taskId = scanner.nextInt();

                *//*if (taskId != 0) {
                    showInfoForChoice(employees, employee, taskId);
                } else {
                    // выход из системы
                    showInfoForChoice(employee, 0, 0,0);
                }*//*
            }*/

            /*if (employee.getRole().equals(Role.DIRECTOR)){
                while(choice != 0){
                    showInfoForChoice(employees, employee, choice);
                }


                showInfoForChoice(employee, choice, );
            }*/

        } else {
            System.out.println("Нет такого пользователя");
        }
    }
}
