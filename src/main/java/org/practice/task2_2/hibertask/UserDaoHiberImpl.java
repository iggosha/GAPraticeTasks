package org.practice.task2_2.hibertask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.practice.task2_2.Dao;

import java.util.List;

public class UserDaoHiberImpl implements Dao<UserHiber> {

    Configuration configuration;
    SessionFactory sessionFactory;

    public UserDaoHiberImpl() {
        this.configuration = new Configuration();
        configuration.configure();
        this.sessionFactory = configuration
                .addAnnotatedClass(UserHiber.class)
                .buildSessionFactory();

    }

    @Override
    public UserHiber getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(UserHiber.class, id);
        }
    }

    @Override
    public List<UserHiber> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserHiber> query = session.createQuery("from UserHiber", UserHiber.class);
            return query.list();
        }
    }

    @Override
    public List<UserHiber> getAll(int selectedPage) {
        int pageSize = 10;
        try (Session session = sessionFactory.openSession()) {
            Query<UserHiber> query = session.createQuery("from UserHiber", UserHiber.class);
            query.setFirstResult((selectedPage - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    @Override
    public List<UserHiber> getAllWithFilter(String filter) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserHiber> query = session.createQuery("from UserHiber where age > :minAge", UserHiber.class);
            query.setParameter("minAge", Integer.parseInt(filter));
            return query.list();
        }
    }

    @Override
    public void create(UserHiber userHiber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(userHiber);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(UserHiber oldT, UserHiber newT) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            oldT = session.get(UserHiber.class, oldT.getId());
            oldT.setAge(newT.getAge());
            oldT.setName(newT.getName());
            oldT.setEmail(newT.getEmail());
            oldT.setRegistrationDate(newT.getRegistrationDate());
            session.merge(oldT);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(UserHiber userHiber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(userHiber);
            session.getTransaction().commit();
        }
    }
}
