package bo.custom.impl;

import bo.custom.RegisterStudentBO;
import dao.DAOFactory;

import dao.custom.CourseDAO;
import dao.custom.StudentCoursesDAO;
import dao.custom.StudentDAO;
import dto.CourseDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import entity.StudentCourses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterStudentBOImpl implements RegisterStudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentCoursesDAO studentCoursesDAO = (StudentCoursesDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT_COURSES);

    @Override
    public void registerStudent(StudentDTO studentDTO) {
        try {
            Student student = new Student(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail());

            CourseDTO courseDTO = studentDTO.getCourseDTO();
            Course course = new Course(courseDTO.getProgramId(),courseDTO.getBatch_number(),courseDTO.getProgram(),courseDTO.getSheets(),courseDTO.getDuration(),courseDTO.getFee());

            StudentCourses studentCourses = new StudentCourses();
            studentCourses.setStudent(student);
            studentCourses.setCourse(course);

            studentDAO.add(student);
            studentCoursesDAO.add(studentCourses);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CourseDTO> getAllCourses() {

        try {

            List<Course> all = courseDAO.getAll();
            List<CourseDTO> allCourse = new ArrayList<>();

            for (Course course : all) {
                allCourse.add(new CourseDTO(course.getProgramId(),course.getBatch_number(), course.getProgram(),course.getSheets(), course.getDuration(), course.getFee()));
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
    public List<CourseDTO> getLastCourses(String courseName) {
        String lastBatchNumber = courseDAO.getLastBatchNumberWhereCourse(courseName);

        List<CourseDTO> courseDTOList=new ArrayList<>();

        return courseDTOList;
    }











    /*StudentDAO studentDAO =(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public void registerStudent(StudentDTO studentDTO) {
        try {

            studentDAO.add(new Student(studentDTO.getId(),studentDTO.getNic(),studentDTO.getName(),studentDTO.getDateOfBirth(),studentDTO.getGender(),studentDTO.getParentName(),studentDTO.getMobile(),studentDTO.getAddress(),studentDTO.getOl_result(),studentDTO.getAl_result(),studentDTO.getEmail()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }*/

    }
