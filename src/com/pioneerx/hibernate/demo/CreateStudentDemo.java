package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use session object to save Java object
			
			// 1. create Student object
			System.out.println("Creating new Student object....");
			Student tempStudent = new Student("Paul", "Wall", "paul@gmail.com");
			
			// 2. begin transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();
			
			// 3. save the student object
			System.out.println("Saving student....");
			session.save(tempStudent);
			
			// 4. commit transaction
			System.out.println("Commiting transaction....");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
