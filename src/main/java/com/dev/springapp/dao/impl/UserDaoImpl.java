package com.dev.springapp.dao.impl;

import com.dev.springapp.dao.UserDao;
import com.dev.springapp.exception.DataProcessingException;
import com.dev.springapp.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding user. Stacktrace: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        List<User> list;
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session
                    .createQuery("from User", User.class);
            list = query.getResultList();
        }
        return list;
    }
}
