package dao.custom.impl;

import dao.custom.CourseDAO;
import dao.custom.QueryDAO;

import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentDTO;
import entity.Student;
import entity.StudentCourses;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import view.tm.CourseTM;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO{

    @Override
    public List<StudentDTO> getStudentFromCourseId(String id) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="select s.id,s.nic,s.name,s.dateOfBirth,s.gender,s.parentName,s.mobile,s.address,s.ol_result,s.al_result,s.email from Student s inner join StudentCourses sc on s.id=sc.student.id WHERE sc.course.id=:courseId";
        Query query = session.createQuery(hql);
        query.setParameter("courseId",id);

        List<Object[]> list = query.list();
        List<StudentDTO> studentDTOList=new ArrayList<>();
        for (Object[] objects : list) {
            studentDTOList.add(new StudentDTO(String.valueOf(objects[0]),String.valueOf(objects[1]),String.valueOf(objects[2]),String.valueOf(objects[3]),String.valueOf(objects[4]),String.valueOf(objects[5]),String.valueOf(objects[6]),String.valueOf(objects[7]),String.valueOf(objects[8]),String.valueOf(objects[9]),String.valueOf(objects[10])));
        }

        transaction.commit();
        session.close();

        return studentDTOList;
    }

    @Override
    public List<CustomDTO> getCourseFromStudentId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="select c.id,c.batch_number,c.program,sc.registerDate from Course c inner join StudentCourses sc on c.id=sc.course.id WHERE sc.student.id=:studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId",id);

        List<Object[]> list = query.list();
        List<CustomDTO> customDTOList=new ArrayList<>();
        for (Object[] objects : list) {
            customDTOList.add(new CustomDTO(String.valueOf(objects[0]),String.valueOf(objects[1]),String.valueOf(objects[2]),String.valueOf(objects[3])));
        }

        transaction.commit();
        session.close();

        return customDTOList;
    }
}
