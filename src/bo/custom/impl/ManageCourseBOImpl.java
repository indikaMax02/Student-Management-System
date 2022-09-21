package bo.custom.impl;

import bo.custom.ManageCourseBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dto.CourseDTO;
import entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCourseBOImpl implements ManageCourseBO {
    CourseDAO courseDAO =(CourseDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    @Override
    public void addCourse(CourseDTO courseDTO) {
        try {
            courseDAO.add(new Course(courseDTO.getProgramId(),courseDTO.getBatch_number(),courseDTO.getProgram(),courseDTO.getSheets(),courseDTO.getDuration(),courseDTO.getFee()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean ifExitsCourse(String id) {
        return courseDAO.ifExistCourse(id);
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        try {
            List<Course> all = courseDAO.getAll();
            List<CourseDTO> allCourse=new ArrayList<>();

            for (Course course : all) {
               allCourse.add(new CourseDTO(course.getProgramId(),course.getBatch_number(),course.getProgram(),course.getSheets(),course.getDuration(),course.getFee()));
            }

        return allCourse;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {
        try {
            courseDAO.update(new Course(courseDTO.getProgramId(),courseDTO.getBatch_number(),courseDTO.getProgram(),courseDTO.getSheets(),courseDTO.getDuration(),courseDTO.getFee()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
