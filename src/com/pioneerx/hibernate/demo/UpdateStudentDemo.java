package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// begin transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("");
		
			// update student **INITIALLY ONLY UPDATED IN MEMORY
			System.out.println("Updating student");
			myStudent.setFirstName("Scooby");
			
			// commit transaction  **NOW UPDATED IN DATABASE
			session.getTransaction().commit();
			
			System.out.println("--------");
			System.out.println("Done!");
			
			// NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student s set email='skfla@hotmail.com' where s.lastName='Glorp'")
						.executeUpdate();
			
			session.getTransaction().commit();
			
			
		} finally {
			factory.close();
		}

	}

}
