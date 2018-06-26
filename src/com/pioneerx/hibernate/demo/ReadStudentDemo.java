package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy", "Duck", "dduck@gmail.com");
			
			// 2. begin transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();
			
			// 3. save the student object
			System.out.println("Saving student, " + tempStudent + "....");
			session.save(tempStudent);
			
			// 4. commit transaction
			System.out.println("Commiting transaction....");
			session.getTransaction().commit();
			
			// NEW CODE to retrieve student from db, need its primary key first
			System.out.println("Saved student's ID: " + tempStudent.getId());
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id, primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			Student savedStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + savedStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("--------");
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
