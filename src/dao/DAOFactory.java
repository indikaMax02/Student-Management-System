package dao;

import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.StudentCoursesDAOImpl;
import dao.custom.impl.StudentDAOImpl;



public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case STUDENT_COURSES:
                return new StudentCoursesDAOImpl();
            case JOIN_QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT,COURSE,STUDENT_COURSES,JOIN_QUERY
    }

}
