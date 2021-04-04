package task_manager;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class MainEmployee {

    //вывод списка задач сотрудника
    public static void showEmployeeTasks(Repository repository, Employee employee) {
        List<Task> tasks = repository.getTasksForEmployee(employee);
        System.out.println("\nId: Описание задачи(статус)");
        for (Task task : tasks) {
            System.out.println(task.getTaskId() + ": " + task.getDescription() + "(" + task.getStatus() + ")");
        }
    }

    //вывод списка работающих сотрудников
    public static void showWorkingEmployees(Repository repository) {
       // HashMap<Integer, Employee> idAndEmployee = repository.getIdAndEmployee();
        List<Employee> employees = repository.getEmployees();
        System.out.println("Вывод списка всех работающих сотрудников: ");

        /*for (Employee employee : idAndEmployee.values()) {
            if (!employee.getDeleted()) {
                System.out.println("id: " + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName());
            }
        }*/

        for (Employee employee : employees) {
            if (!employee.getDeleted()) {
                System.out.println(employee.getLastName() + " " + employee.getFirstName());
            }
        }
    }

    //вывод списка уволенных сотрудников
    public static void showRemovedEmployees(Repository repository) {
        List<Employee> employees = repository.getEmployees();
        System.out.println("Вывод списка всех уволенных сотрудников: ");
//TODO: https://stackoverflow.com/questions/2419353/make-arraylist-read-only
        for (Employee employee : employees) {
            if (employee.getDeleted()) {
                System.out.println(employee.getLastName() + " " + employee.getFirstName());
            }
        }
    }


    // редактирование задачи директором
    public static void editTask(Repository repository, Employee employee){

        showEmployeeTasks(repository, employee);
        System.out.println("\nВведите id задачи: ");
        Scanner scanner = new Scanner(System.in);
        int taskId = scanner.nextInt();
        Task task = employee.getTaskById(taskId);

        System.out.println("\nВведите данные для редактирования задачи:");
        System.out.println("Описание задачи:");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Время, отведенное для выполнения задачи (в часах):");
        int expectedTime = scanner.nextInt();

        System.out.println("Для присвоения задачи статуса:");
        System.out.println("NEW - введите 1");
        System.out.println("WORK - введите 2");
        System.out.println("COMPLETED - введите 3");
        int intStatus = scanner.nextInt();
        String strStatus = "NEW";
        if(intStatus == 2){
            strStatus = "WORK";
        } else if (intStatus == 3){
            strStatus = "COMPLETED";
        }
        Status status = Status.valueOf(strStatus);

        task.setDescription(description);
        task.setExpectedTime(expectedTime);
        task.setStatus(status);

        repository.saveTasks();

    }

    // создание новой задачи
    public static void createNewTask(Repository repository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nОписание задачи:");
        String description = scanner.nextLine();
        System.out.println("Время, отведенное для выполнения задачи (в часах):");
        int expectedTime = scanner.nextInt();
        int tasksCount = repository.getTasksCount();
        int taskId = ++tasksCount;
        Task task = new Task(taskId, description, expectedTime, Status.NEW);

        System.out.println("Чтобы назначить созданную задачу сотруднику, нажмите: 1");
        System.out.println("Чтобы не назначать созданную задачу сотруднику, нажмите: 2");
        int choice = scanner.nextInt();

        if (choice == 1){
            System.out.println("\nВведите id сотрудника: ");
            int employeeId = scanner.nextInt();
            Employee employee = repository.getEmployee(employeeId);
            employee.addTask(task);
            task.setEmployee(employee);
        }

       // repository.getTasks().add(task);
        repository.addTask(task);
        repository.saveTasks();
    }




    // time в виде dd.MM.yyyy
    public static Calendar stringToCalendar(String time) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        try {
            Date date = df.parse(time);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    // создание нового сотрудника
    public static void createNewEmployee(Repository repository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Введите данные нового сотрудника: ");
        System.out.println("Имя: ");
        String firstName = scanner.nextLine();
        System.out.println("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.println("Телефон: ");
        String phone = scanner.nextLine();
        System.out.println("E-mail: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Введите дату рождения в формате dd.mm.yyyy: ");
        String strBirthday = scanner.nextLine();
        Calendar birthday = stringToCalendar(strBirthday);

        int nextId = repository.getEmployeeMaxId() + 1;
        Employee employee = new Employee(firstName, lastName, phone, email, password, birthday, Role.EMPLOYEE, nextId);
        //TODO: repository addEmployee
        repository.addEmployee(employee);
        repository.saveEmployees();
    }

    // удаление сотрудника
    public static void removeEmployee(Repository repository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Введите id удаляемого сотрудника: ");
        int id = scanner.nextInt();
        Employee employee = repository.getEmployee(id);
        //TODO: так же вынести в репозиторий функцию удаления
        repository.deleteEmployee(employee);
        repository.saveEmployees();
    }

    public static void printTask(Task task) {
        String description = task.getDescription();
        int expectedTime = task.getExpectedTime();
        Status status = task.getStatus();

        System.out.println("Описание задачи: " + description);
        System.out.println("Время, требуемое для выполнения (в часах): " + expectedTime);
        System.out.println("Текущий статус задачи: " + status);
    }

    public static int showEmployeeMenu(Repository repository, Employee employee) {
        System.out.println("Доступные действия: ");
        System.out.println("0: Выход");
        System.out.println("1: Просмотр списка своих задач");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            showEmployeeTasks(repository, employee);
            System.out.println("\nДля редактирования статуса задачи выберите ее id");
            System.out.println("Для выхода нажмите 0");
            int taskId = scanner.nextInt();

            if (taskId != 0) {
                Task task = employee.getTaskById(taskId);
                printTask(task);
                System.out.println("\nДля принятия задачи в работу, нажмите: 1");
                System.out.println("Для завершения задачи, нажмите: 2");
                System.out.println("Для выхода, нажмите: 0");
                choice = scanner.nextInt();

                if (choice == 1) {
                    task.setStatus(Status.WORK);
                }
                if (choice == 2) {
                    task.setStatus(Status.COMPLETED);
                }

                repository.saveTasks();
                System.out.println("Изменения сохранены");
            } else {
                choice = 0;
            }
        }
        return choice;
    }

    public static void showFinalQuestions() {
        System.out.println("\nДля возврата в главное меню нажмите 1");
        System.out.println("Для выхода нажмите 0");
    }

    public static int showDirectorMenu(Employee employee, Repository repository) {
        System.out.println("Доступные действия: ");
        System.out.println("0: Выход");
        System.out.println("1: Просмотр списка сотрудников");
        System.out.println("2: Создание нового сотрудника");
        System.out.println("3: Удаление сотрудника");
        System.out.println("4: Просмотр удаленных сотрудников");
        System.out.println("5: Просмотр задач выбранного сотрудника");
        System.out.println("6: Создание задачи");
        System.out.println("7: Редактирование задачи выбранного сотрудника");
        System.out.println("8: Назначение задачи сотруднику");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice){
            case 1: {
                showWorkingEmployees(repository);
                break;
            }
            case 2: {
                createNewEmployee(repository);
                break;
            }
            case 3: {
                removeEmployee(repository);
                break;
            }
            case 4: {
                showRemovedEmployees(repository);
                break;
            }
            case 5: {
                System.out.println("\nВведите id сотрудника:");
                int id = scanner.nextInt();
                Employee viewedEmployee = repository.getEmployee(id);
                showEmployeeTasks(repository, viewedEmployee);
                break;
            }
            case 6: {
                createNewTask(repository);
                break;
            }
            case 7: {
                System.out.println("\nВведите id сотрудника:");
                int employeeId = scanner.nextInt();
                Employee employeeForTask = repository.getEmployee(employeeId);
                editTask(repository, employeeForTask);
                break;
            }
            case 8: {
                //TODO: где правильнее разместить эту ф-ию: в Repository или в Employee?
                HashMap<Integer, Task> unassignedTask =  repository.getUnassignedTasks();
                for (Map.Entry<Integer, Task> pair : unassignedTask.entrySet()) {
                    System.out.println(pair.getKey() + ": " + pair.getValue().getDescription());
                }

                System.out.println("Введите номер задачи из списка неназначенных задач: ");
                int taskNum = scanner.nextInt();
                Task task = unassignedTask.get(taskNum);
                if (task != null) {

                    System.out.println("\nВведите id сотрудника:");
                    int employeeId = scanner.nextInt();

                    //TODO: где правильнее разместить эту ф-ию: в Repository или в Employee (и тогда разместить в ней предыдущие sout)?
                    boolean result = repository.assignTask(employeeId, task);
                    if (result){
                        System.out.println("Задача успешно назначена сотруднику");
                    } else{
                        System.out.println("Указан неверный id сотруднику");
                    }
                } else{
                    System.out.println("Указан неверный номер задачи");
                }
                break;
            }


        }
        showFinalQuestions();
        choice = scanner.nextInt();
        return choice;
    }

    public static void main(String[] args) {
        Repository repository = new Repository();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите e-mail:");
        String mail = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        Employee employee = repository.findEmployee(mail, password);

        if (employee != null) {
            repository.readTasks();

            int choice;

            if (employee.getRole().equals(Role.EMPLOYEE)) {
                do {
                    choice = showEmployeeMenu(repository, employee);
                } while (choice != 0);
            }

            if (employee.getRole().equals(Role.DIRECTOR)) {
                do {
                    choice = showDirectorMenu(employee, repository);
                } while (choice != 0);
            }
       } else{
            System.out.println("Указан неверный логин или пароль");
        }
    }
}
