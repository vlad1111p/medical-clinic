package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenericDao<T> {
    SessionFactory sessionFactory;

    public GenericDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void add(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
    }

    public void update(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
    }

    public void delete(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }

    public List<T> getAll(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName());
        List<T> results = query.getResultList();
        transaction.commit();
        return results;
    }

    public T findById(T object, Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName() + " where id=" + id + "");
        T result = (T) query.getSingleResult();
        transaction.commit();
        return result;

        /*
         List<T>  results = query.getResultList();
         return results.get(0);
         */
    }

    public List<T> findByColumn(T object, String column, String value) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName() + " where " + column + "='" + value + "'");
        List<T> results = query.getResultList();
        transaction.commit();
        return results;

    }
}
