package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer cust1 = new Customer();
        cust1.setName("John Doe");
        cust1.setEmail("john@example.com");
        cust1.setAge(25);
        cust1.setLocation("New York");
        Customer cust2 = new Customer();
        cust2.setName("Jane Smith");
        cust2.setEmail("jane@example.com");
        cust2.setAge(30);
        cust2.setLocation("Los Angeles");
        session.save(cust1);
        session.save(cust2);
        transaction.commit();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("location", "New York"));
        List<Customer> result = criteria.list();
        System.out.println("Customers from New York:");
        for (Customer c : result) {
            System.out.println(c.getName());
        }
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 28));
        result = criteria.list();
        System.out.println("Customers older than 28:");
        for (Customer c : result) {
            System.out.println(c.getName());
        }
        session.close();
        sessionFactory.close();
    }
}
