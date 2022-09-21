package dto;

import entity.Course;
import entity.Student;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;



public class StudentCoursesDTO {

    private StudentDTO student;
    private CourseDTO course;

    public StudentCoursesDTO() {
    }

    public StudentCoursesDTO(StudentDTO student, CourseDTO course) {
        this.student = student;
        this.course = course;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
