package entity;

import dao.SuperDAO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course implements SuperEntity {
    @Id
    @Column(name = "program_id")
    private String programId;
    private String batch_number;
    private String program;
    private String sheets;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<StudentCourses> studentCourses=new HashSet<>();


    public Set<StudentCourses> getStudentCourses() {
        return studentCourses;
    }

    public void addStudentCourses(StudentCourses studentCourses) {
        getStudentCourses().add(studentCourses);
    }

    public Course() {
    }

    public Course(String programId, String batch_number, String program, String sheets, String duration, double fee) {
        this.programId = programId;
        this.batch_number = batch_number;
        this.program = program;
        this.sheets = sheets;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSheets() {
        return sheets;
    }

    public void setSheets(String sheets) {
        this.sheets = sheets;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setStudentCourses(Set<StudentCourses> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
