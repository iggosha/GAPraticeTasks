package org.practice.task2_2.hibertask;

import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.practice.task2_2.Dao;

import java.util.List;

public class UserDaoCritImpl implements Dao<UserHiber> {

    Configuration configuration;
    SessionFactory sessionFactory;

    public UserDaoCritImpl() {
        this.configuration = new Configuration();
        configuration.configure();
        this.sessionFactory = configuration
                .addAnnotatedClass(UserHiber.class)
                .buildSessionFactory();

    }

    @Override
    public UserHiber getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserHiber> cq = cb.createQuery(UserHiber.class);
            cq.where(cb.equal(cq.from(UserHiber.class).get("id"), id));
            return session.createQuery(cq).getSingleResult();
        }
    }

    @Override
    public List<UserHiber> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<UserHiber> criteriaQuery = session.getCriteriaBuilder().createQuery(UserHiber.class);
            criteriaQuery.select(criteriaQuery.from(UserHiber.class));
            Query<UserHiber> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    @Override
    public List<UserHiber> getAll(int selectedPage) {
        int pageSize = 10;
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<UserHiber> criteriaQuery = session.getCriteriaBuilder().createQuery(UserHiber.class);
            criteriaQuery.select(criteriaQuery.from(UserHiber.class));
            Query<UserHiber> query = session.createQuery(criteriaQuery);
            query.setFirstResult((selectedPage - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        }
    }

    @Override
    public List<UserHiber> getAllWithFilter(String filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserHiber> criteriaQuery = builder.createQuery(UserHiber.class);
            Root<UserHiber> root = criteriaQuery.from(UserHiber.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.gt(root.get("age"), Integer.parseInt(filter)));
            Query<UserHiber> query = session.createQuery(criteriaQuery);

            return query.getResultList();
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

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<UserHiber> criteriaUpdate = builder.createCriteriaUpdate(UserHiber.class);
            Root<UserHiber> root = criteriaUpdate.from(UserHiber.class);

            criteriaUpdate.set(root.get("age"), newT.getAge());
            criteriaUpdate.set(root.get("name"), newT.getName());
            criteriaUpdate.set(root.get("email"), newT.getEmail());
            criteriaUpdate.set(root.get("registrationDate"), newT.getRegistrationDate());

            criteriaUpdate.where(builder.equal(root.get("id"), oldT.getId()));

            session.createMutationQuery(criteriaUpdate).executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(UserHiber userHiber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<UserHiber> criteriaDelete = builder.createCriteriaDelete(UserHiber.class);

            criteriaDelete.where(builder.equal(criteriaDelete.from(UserHiber.class), userHiber));

            session.createMutationQuery(criteriaDelete).executeUpdate();

            session.getTransaction().commit();
        }
    }
}
