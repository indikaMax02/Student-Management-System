package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;

import java.util.List;

public interface ManageCourseBO extends SuperBO {
    void addCourse(CourseDTO courseDTO);

    boolean ifExitsCourse(String id);

    List<CourseDTO> getAllCourses();

    void updateCourse(CourseDTO courseDTO);


}
