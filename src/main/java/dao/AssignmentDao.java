package dao;


import objects.Assignment;
import org.hibernate.Session;

public class AssignmentDao {


    public void persistAssignment(Assignment assignment) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(assignment);

            session.getTransaction().commit();
            session.close();
        }
    }
}
