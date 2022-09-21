package entity;

import dao.SuperDAO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_course")
public class StudentCourses implements SuperEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private
    Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private
    Course course;

    @CreationTimestamp
    private
    Date registerDate;

    public StudentCourses() {
    }

    public StudentCourses( Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
