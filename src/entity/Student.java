package entity;

import dao.SuperDAO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student implements SuperEntity {
    @Id
    @Column(name = "student_id")
    private String id;
    private String nic;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String parentName;
    private String mobile;
    private String address;
    private String ol_result;
    private String al_result;
    private String email;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<StudentCourses> studentCourses=new HashSet<>();


    public Set<StudentCourses> getStudentCourses() {
        return studentCourses;
    }

    public void addStudentCourses(StudentCourses studentCourses) {
        getStudentCourses().add(studentCourses);
    }

    public Student() {
    }


    public Student(String id, String nic, String name, String dateOfBirth, String gender, String parentName, String mobile, String address, String ol_result, String al_result, String email) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.parentName = parentName;
        this.mobile = mobile;
        this.address = address;
        this.ol_result = ol_result;
        this.al_result = al_result;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOl_result() {
        return ol_result;
    }

    public void setOl_result(String ol_result) {
        this.ol_result = ol_result;
    }

    public String getAl_result() {
        return al_result;
    }

    public void setAl_result(String al_result) {
        this.al_result = al_result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", parentName='" + parentName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", ol_result='" + ol_result + '\'' +
                ", al_result='" + al_result + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}
