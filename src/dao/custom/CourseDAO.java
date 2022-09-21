package dao.custom;

import dao.CrudDAO;
import entity.Course;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course,String>{
    boolean ifExistCourse(String id);
    String getLastBatchNumberWhereCourse(String courseName);
    List<Course> getLastCourses(String batchNumber);
}
