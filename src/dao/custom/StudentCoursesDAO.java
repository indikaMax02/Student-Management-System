package dao.custom;

import dao.CrudDAO;
import entity.StudentCourses;

import java.sql.SQLException;
import java.util.List;

public interface StudentCoursesDAO extends CrudDAO<StudentCourses,StudentCourses> {

    boolean ifCourseExitsInStudent(String student_id);
    List<StudentCourses> getStudentCoursesForStudent(String studentId);

}
