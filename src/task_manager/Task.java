package task_manager;

enum Status {
    NEW, WORK, COMPLETED;

    public static boolean getStatus(String strStatus){

        for (Status status : Status.values()) {
            if (status.name().equals(strStatus)){
                return true;
            }
        }
        return false;
    }
};

public class Task {
   // private int employeeId;
    private int taskId;
    private String description;
    // ожидаемое время выполнения (в часах)
    private int expectedTime;
    private Status status;

    private Employee employee;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(int taskId, String description, int expectedTime, Status status) {
        this.taskId = taskId;
        this.description = description;
        this.expectedTime = expectedTime;
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDescription() {
        return description;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public int getEmployeeId() {
        if(this.employee == null)
            return 0;
        else return this.employee.getId();
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
