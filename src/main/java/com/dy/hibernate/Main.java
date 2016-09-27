package com.dy.hibernate;

import com.dy.hibernate.entity.Jobs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT e FROM Jobs e").getResultList().forEach(System.out::println);
        System.out.println("-----");
        Jobs j = em.find(Jobs.class, "AC_MGR");
        Integer originalMaxSalary = j.getMaxSalary();
        System.out.println("Original salary: " + originalMaxSalary);
        j.setMaxSalary(999_999);
        em.persist(j);
        em.getTransaction().commit();

        em.getTransaction().begin();
        j = em.find(Jobs.class, "AC_MGR");
        System.out.println("New salary: " + j.getMaxSalary());
        j.setMaxSalary(originalMaxSalary);
        em.persist(j);
        em.getTransaction().commit();

        emf.close();
    }
}
