package view.tm;

import java.util.Date;

public class CourseDetailsTM {
private String batchNumber;
private String courseName;
private String registerDate;

    public CourseDetailsTM() {
    }

    public CourseDetailsTM(String batchNumber, String courseName, String registerDate) {
        this.batchNumber = batchNumber;
        this.courseName = courseName;
        this.registerDate = registerDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
