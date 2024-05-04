package Main;

import java.util.HashMap;
import java.util.Map;

public class Doctor extends User {
    private String name;
    private Map<Integer, Course> courses;

    public Doctor(int doctorId, String name) {
        super(doctorId, null, null, "doctor");
        this.name = name;
        this.courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseId(), course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, Course> courses) {
        this.courses = courses;
    }
}