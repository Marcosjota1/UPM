package es.upm.pproject.miniproject;

public class Course {
    private int code;
    private String name;
    private String coordinator;

    public Course(int code, String name, String coordinator) {
        this.code = code;
        this.name = name;
        this.coordinator = coordinator;
    }

    // Getters
    public int getCode() { return code; }
    public String getName() { return name; }
    public String getCoordinator() { return coordinator; }
}