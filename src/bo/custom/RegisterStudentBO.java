package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.StudentDTO;

import java.util.List;

public interface RegisterStudentBO extends SuperBO {
    void registerStudent(StudentDTO studentDTO);
    List<CourseDTO> getAllCourses();
    List<CourseDTO>getLastCourses(String courseName);

}
