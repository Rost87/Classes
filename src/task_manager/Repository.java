package task_manager;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Repository {
    private HashMap<Integer, Employee> idAndEmployee = new HashMap<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskMaxId;
    static String employeesPath = "./src/task_manager/Data/Employees.txt";
    static String tasksPath = "./src/task_manager/Data/EmployeesTasks.txt";


    public HashMap<Integer, Employee> getIdAndEmployee() {
        return idAndEmployee;
    }

    public void setIdAndEmployee(HashMap<Integer, Employee> idAndEmployee) {
        this.idAndEmployee = idAndEmployee;
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public Repository() {
        readEmployees();
    }

    public Employee getEmployee(int id) {
        Employee employee = idAndEmployee.get(id);
        return employee;
    }

    public void addEmployee(Employee employee){
        int employeeId = employee.getId();
        idAndEmployee.put(employeeId, employee);
    }

    public void deleteEmployee(Employee employee){
        employee.setDeleted(true);
        int newEmployeeId = -employee.getId();
        employee.setId(newEmployeeId);
    }

    public List<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        for (Map.Entry<Integer, Employee> pair : idAndEmployee.entrySet()) {
            employees.add(pair.getValue());
        }
        return Collections.unmodifiableList(employees);
    }

    // поиск сотрудника по указанному login и password
    public Employee findEmployee(String login, String password) {
        for (Employee employee : idAndEmployee.values()) {
            if (employee.getEmail().equals(login)) {
                if (employee.getPassword().equals(password)) {
                    return employee;
                }
                break;
            }
        }
        return null;
    }

    public int getEmployeeMaxId() {
        int maxId = 1;
        for (Employee employee : idAndEmployee.values()) {
            int id = employee.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId;
    }

    // считывание инф-ии о всех сотрудниках из txt файла
    public void readEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(employeesPath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employeeInfo = line.split(" ");
                String firstName = employeeInfo[0];
                String lastName = employeeInfo[1];
                String phone = employeeInfo[2];
                String email = employeeInfo[3];
                String password = employeeInfo[4];

                DateFormat df = new SimpleDateFormat("dd.yyyy.MM");
                Date date = df.parse(employeeInfo[5]);
                Calendar birthday = new GregorianCalendar();
                birthday.setTime(date);

                Role role = Role.valueOf(employeeInfo[6]);
                int employeeId = Integer.parseInt(employeeInfo[7]);

                Employee employee = new Employee(firstName, lastName, phone, email, password, birthday, role, employeeId);
                idAndEmployee.put(employeeId, employee);

                if (employeeId < 0) {
                    employee.setDeleted(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // считывание всех задач из txt файла
    public void readTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(tasksPath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tasksInfo = line.split("\\|");

                int employeeId = Integer.parseInt(tasksInfo[0]);
                int taskId = Integer.parseInt(tasksInfo[1]);
                String description = tasksInfo[2];
                int expectedTime = Integer.parseInt(tasksInfo[3]);
                Status status = Status.valueOf(tasksInfo[4]);

                Task task = new Task(taskId, description, expectedTime, status);
                tasks.add(task);

                if (employeeId != 0) {
                    Employee employee = idAndEmployee.get(employeeId);
                    employee.addTask(task);
                    task.setEmployee(employee);
                    taskMaxId++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // сохранение инф-ии о сотрудниках в txt файл
    public void saveEmployees() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(employeesPath, false))) {
            bw.append("FName").append(" ").append("LName").append(" ").append("E-mail").append(" ").append("Password").append(" ")
                    .append("Birthday").append(" ").append("Role").append(" ").append("id");
            for (Employee employee : idAndEmployee.values()) {
                String firstName = employee.getFirstName();
                String lastName = employee.getLastName();
                String phone = employee.getPhone();
                String email = employee.getEmail();
                String password = employee.getPassword();
                Calendar birthday = employee.getBirthday();
                String strBirthday = dateToString(birthday.getTime());
                Role role = employee.getRole();
                int id = employee.getId();
                bw.append("\n");
                bw.append(firstName).append(" ").append(lastName).append(" ").append(phone).append(" ").append(email)
                        .append(" ").append(password).append(" ").append(strBirthday).append(" ").append(String.valueOf(role)).append(" ")
                        .append(String.valueOf(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // сохранение инф-ии о задачах в txt файл
    public void saveTasks() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tasksPath))) {
            bw.append("userId|taskId|description|expected time for task(hour)|task status");
            for (Task task : tasks) {
                int id = task.getEmployeeId();
                int taskId = task.getTaskId();
                String description = task.getDescription();
                int expectedTime = task.getExpectedTime();
                Status status = task.getStatus();
                bw.append("\n");
                bw.append(String.valueOf(id)).append("|").append(String.valueOf(taskId)).append("|").append(description).append("|")
                        .append(String.valueOf(expectedTime)).append("|").append(String.valueOf(status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        return df.format(date);
    }

    // возвращает все задачи (всех сотрудников)
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public List<Task> getTasksForEmployee(Employee employee){
        return employee.getTasks();
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public  Task getTaskById(int needTaskId){
        for (Task task : tasks) {
            int taskId = task.getTaskId();
            if (taskId == needTaskId){
                return task;
            }
        }
        return null;
    }

    //показать список неназначенных задач
    public HashMap<Integer, Task> getUnassignedTasks(){
        HashMap<Integer, Task> unassignedTasks = new HashMap<>();
        int i = 1;
        for (Task task : tasks) {
            if (task.getEmployeeId() == 0){
                unassignedTasks.put(i++, task);
            }

        }
        return unassignedTasks;
    }


    //назначение задачи сотруднику
    public boolean assignTask( int employeeId, Task task) {

        Employee employee = this.getEmployee(employeeId);
        if (employee != null) {
            employee.addTask(task);
            task.setEmployee(employee);
            this.saveTasks();
            return true;
        }
        return false;
    }

    public int getTaskMaxId() {
        return taskMaxId;
    }

    public void setTaskMaxId(int taskMaxId) {
        this.taskMaxId = taskMaxId;
    }
}
