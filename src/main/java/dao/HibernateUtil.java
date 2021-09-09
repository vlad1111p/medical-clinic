package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class HibernateUtil<T> {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/medical_clinic?serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "qazwsx");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Patient.class);
                configuration.addAnnotatedClass(MedicalClinic.class);
                configuration.addAnnotatedClass(Appointment.class);
                configuration.addAnnotatedClass(Doctor.class);
                configuration.addAnnotatedClass(Recipe.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public void add(T object)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
    }

    public void update(T object)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
    }

    public void delete(T object)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }

    public List<T> getAll(T object)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName());
        List<T>  results = query.getResultList();
        transaction.commit();
        return results;
    }

    public T findById(T object, int id)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName() + " where id=" + id +"");
        T result = (T) query.getSingleResult();
        transaction.commit();
        return result;

        /*
         List<T>  results = query.getResultList();
         return results.get(0);
         */
    }

    public List<T> findByColumn(T object, String column, String value)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName() + " where " + column + "='" + value + "'" );
        List<T>  results = query.getResultList();
        transaction.commit();
        return results;

    }
}