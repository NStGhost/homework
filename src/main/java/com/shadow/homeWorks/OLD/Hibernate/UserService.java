package com.shadow.homeWorks.OLD.Hibernate;

import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    private final UserDao usersDao;
    private final SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        usersDao = new UserDao(this.sessionFactory);
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Car findAutoById(int id) {
        return usersDao.findAutoById(id);
    }


}