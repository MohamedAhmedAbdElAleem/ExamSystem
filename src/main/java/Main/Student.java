package Main;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private String name;
    private List<Course> enrolledCourses;

    public Student(int studentId, String name) {
        super(studentId, null, null, "student");
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public void addEnrolledCourse(Course course) {
        enrolledCourses.add(course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
