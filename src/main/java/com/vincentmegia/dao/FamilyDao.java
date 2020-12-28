package com.vincentmegia.dao;

import com.vincentmegia.models.Family;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FamilyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public void addFamily() {
        var family = new Family("01", "megia");
        var session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(family);
            transaction.commit(); }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Family addFamily2() {
        var family = new Family(null, "megia");
        System.out.println("entitymanager.contains: " + entityManager.contains(family));
        entityManager.persist(family);
        System.out.println("entitymanager.contains: " + entityManager.contains(family));
        family.setSurname("ASLKFJSLDKJF");
        entityManager.persist(family);
        return family;
    }

    public void updateFamily(Family family) {
        if (this.entityManager.contains(family))
            this.entityManager.persist(family);
        else {
            this.entityManager.merge(family);
        }
    }

    public void getAll() {
        var session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Family> families = session.createQuery("FROM Family").list();
            for (var family : families){
                System.out.print("Id: " + family.getId());
                System.out.print("Surname: " + family.getSurname());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Family getById2(String id) {
        var family = this.entityManager.find(Family.class, id);
        return family;
    }

    public Family getByI(String id) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            var family = session.get(Family.class, id);
            System.out.print("Id: " + family.getId());
            System.out.print("Surname: " + family.getSurname());
            transaction.commit();
            return family;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
