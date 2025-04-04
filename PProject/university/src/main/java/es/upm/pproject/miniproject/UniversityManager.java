package es.upm.pproject.miniproject;

import java.util.List;

public interface UniversityManager {
    void registerCourse(int code, String name, String coordinator) throws IllegalArgumentException;
    void registerStudent(int id, String name, String email) throws IllegalArgumentException;
    void enrollStudent(int studentId, int courseCode) throws IllegalStateException;
    List<Student> getStudentsInCourse(int courseCode);
    void cancelEnrollment(int studentId, int courseCode) throws IllegalStateException;
    void restartCourse(int courseCode);
    List<Student> getAllStudents();
    List<Course> getAllCourses();
}