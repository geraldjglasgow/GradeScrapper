package dao;

import objects.Course;
import org.hibernate.Session;

public class CourseDao {

    public void persistCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(course);

            session.getTransaction().commit();
            session.close();
        }
    }

    public Course retrieveCourse(String courseName) {
        Course course;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            course = session.get(Course.class, courseName);

            session.getTransaction().commit();
            session.close();
        }
        return course;
    }
}
