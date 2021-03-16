package com.company;

import java.util.ArrayList;
import java.util.Calendar;

enum Role {
    EMPLOYEE, DIRECTOR
}

public class Employee {
    private final int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Calendar birthday;
    private ArrayList<Task> tasks = new ArrayList<>();
    private final Role role;
    public static int nextId;
    public static String path = "./PersonInfo/EmployeesTasks.txt";

    public Employee(String firstName, String lastName, String phone, String email, String password, Calendar birthday, Role role, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
        this.id = id;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }


    public Role getRole() {
        return role;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // добавление новой задачи, с предварительной проверкой существовования задачи с указанным id
    public void addTask(Task newTask){
        int taskId = newTask.getTaskId();
        Task task = this.getTaskById(taskId);
        if (task !=null) {
            rewriteTask(newTask);
        } else{
            this.getTasks().add(newTask);
        }
    }

    // поиск задачи по ее идентификатору
    public Task getTaskById(int taskId){
         for (Task task : tasks){
            if (taskId == task.getTaskId()){
                return task;
            }
        }
        return null;
    }

    // перезапись задачи
    public void rewriteTask(Task newTask){
        int taskId = newTask.getTaskId();
        Task task = this.getTaskById(taskId);
        task.setDescription(newTask.getDescription());
        task.setExpectedTime(newTask.getExpectedTime());
        task.setStatus(newTask.getStatus());
    }


    public int getId() {
        return id;
    }
}

//создать классы задача и сотрудник. Поля задачи - название, описание, кол-во часов, статус (enum - новая, в работе, завершенная),
// назначенный сотрудник. Поля сотрудника - имя, фамилия, емайл, телефон, пароль, список назначенных задач, роль (директор или сотрудник).
// Две роли, директор и сотрудник. При входе в систему нужно ввести емайл и пароль.
// если зашли под директором то можно создавать задачи, назначать их на сотрудников. создавать сотрудников. просмотр и редактирование всего
//Добавить в задачу Начало и Окончание задачи