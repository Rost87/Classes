package com.company;

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
    private int employeeId;
    private int taskId;
    private String description;
    // ожидаемое время выполнения (в часах)
    private int expectedTime;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(int employeeId, int taskId, String description, int expectedTime, Status status) {
        this.employeeId = employeeId;
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
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

}
