package dao.custom.impl;

import dao.custom.StudentCoursesDAO;
import dao.custom.StudentDAO;
import entity.Student;
import entity.StudentCourses;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class StudentCoursesDAOImpl implements StudentCoursesDAO {

    @Override
    public void add(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(studentCourses);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(studentCourses);

        transaction.commit();
        session.close();




    }


    @Override
    public void update(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {

    }

    @Override
    public StudentCourses search(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public List<StudentCourses> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean ifCourseExitsInStudent(String student_id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql="FROM StudentCourses sc WHERE sc.student.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",student_id);
        List list = query.list();
        for (Object o : list) {
            return true;
        }

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public List<StudentCourses> getStudentCoursesForStudent(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql="FROM StudentCourses sc WHERE sc.student.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",studentId);
        List<StudentCourses>list = query.list();

        transaction.commit();
        session.close();
       return list;
    }
}
