package com.shadow.TestHomeWork22;

import com.shadow.TestHomeWork22.Author;
import com.shadow.TestHomeWork22.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private final SessionFactory sessionFactory;

    public HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Book.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    /*static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Book.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }*/
}
