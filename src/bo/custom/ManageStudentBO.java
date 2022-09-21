package bo.custom;

import bo.SuperBO;

import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
import dto.StudentDTO;
import entity.Student;
import entity.StudentCourses;

import java.util.List;

public interface ManageStudentBO extends SuperBO {
    List<StudentDTO> getAllStudent();
    List<StudentDTO> getStudentFromCourse(String courseId);
    List<CustomDTO>  getCourseForStudent(String studentId);
    List<CourseDTO> getAllCourses();
    void deleteStudentCourse(StudentDTO studentDTO,CourseDTO courseDTO);
    boolean deleteStudent(StudentDTO studentDTO);
    void addCourseToStudent(StudentCoursesDTO studentCourse);
    void updateStudent(StudentDTO studentDTO);

}
