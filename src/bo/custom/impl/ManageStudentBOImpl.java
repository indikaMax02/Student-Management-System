package bo.custom.impl;

import bo.custom.ManageStudentBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dao.custom.QueryDAO;
import dao.custom.StudentCoursesDAO;
import dao.custom.StudentDAO;

import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import entity.StudentCourses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentBOImpl implements ManageStudentBO {
    StudentDAO studentDAO =(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = (QueryDAO)DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.JOIN_QUERY);
    CourseDAO courseDAO =(CourseDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentCoursesDAO studentCoursesDAO =(StudentCoursesDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT_COURSES);
    @Override
    public List<StudentDTO> getAllStudent() {


        try {

            List<Student> all = studentDAO.getAll();

            List<StudentDTO> studentDTOList = new ArrayList<>();

            for (Student student : all) {
                studentDTOList.add(new StudentDTO(student.getId(),student.getNic(),student.getName(),student.getDateOfBirth(),student.getGender(),student.getParentName(),student.getMobile(),student.getAddress(),student.getOl_result(),student.getAl_result(),student.getEmail()));
            }

            return studentDTOList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         return null;
    }

    @Override
    public List<StudentDTO> getStudentFromCourse(String courseId) {
        return queryDAO.getStudentFromCourseId(courseId);
    }

    @Override
    public List<CustomDTO> getCourseForStudent(String studentId) {
        List<CustomDTO> courseFromStudentId = queryDAO.getCourseFromStudentId(studentId);
        for (CustomDTO customDTO : courseFromStudentId) {
            System.out.println(customDTO.getId()+" "+customDTO.getProgramName());
        }
        return courseFromStudentId;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        try {
            List<Course> allCourse = courseDAO.getAll();

            List<CourseDTO> courseDTOS=new ArrayList<>();
            for (Course course : allCourse) {
                courseDTOS.add(new CourseDTO(course.getProgramId(),course.getBatch_number(),course.getProgram(),course.getSheets(),course.getDuration(),course.getFee()));
            }
            return courseDTOS;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void deleteStudentCourse(StudentDTO studentDTO,CourseDTO courseDTO) {

        try {
            Student student = new Student(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail());
            Course course = new Course(courseDTO.getProgramId(),courseDTO.getBatch_number(),courseDTO.getProgram(),courseDTO.getSheets(),courseDTO.getDuration(),courseDTO.getFee());
            StudentCourses studentCourses = new StudentCourses(student,course);
            studentCoursesDAO.delete(studentCourses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {

        try {

            boolean b = studentCoursesDAO.ifCourseExitsInStudent(studentDTO.getId());
            if (b) {
              return false;


            }else {
                Student student = new Student(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail());
                studentDAO.delete(student);
                return true;
            }

            } catch(SQLException throwables){
                throwables.printStackTrace();
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            }

       return false;
    }

    @Override
    public void addCourseToStudent(StudentCoursesDTO dto) {
        StudentDTO studentDTO = dto.getStudent();
        CourseDTO courseDTO = dto.getCourse();

        Student student = new Student(studentDTO.getId(),studentDTO.getNic(),studentDTO.getName(),studentDTO.getDateOfBirth(),studentDTO.getGender(),studentDTO.getParentName(),studentDTO.getMobile(),studentDTO.getAddress(),studentDTO.getOl_result(),studentDTO.getAl_result(),studentDTO.getEmail());
        Course course = new Course(courseDTO.getProgramId(),courseDTO.getBatch_number(),courseDTO.getProgram(),courseDTO.getSheets(),courseDTO.getDuration(),courseDTO.getFee());
        StudentCourses studentCourses = new StudentCourses(student,course);

        try {

            studentCoursesDAO.add(studentCourses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        try {
            studentDAO.update(new Student(studentDTO.getId(),studentDTO.getNic(),studentDTO.getName(),studentDTO.getDateOfBirth(),studentDTO.getGender(),studentDTO.getParentName(),studentDTO.getMobile(),studentDTO.getAddress(),studentDTO.getOl_result(),studentDTO.getAl_result(),studentDTO.getEmail()));

        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
