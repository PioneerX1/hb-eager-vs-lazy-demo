package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 3;
			
			// begin transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();
			
			// delete via object
			Student myStudent = session.get(Student.class, studentId);
			session.delete(myStudent);
			
			// commit transaction  
			session.getTransaction().commit();
			
			// ALTERNATE DELETION METHOD
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// delete via executeUpdate query, still need to COMMIT transaction!
			session.createQuery("DELETE from Student WHERE id = 5").executeUpdate();
			
			session.getTransaction().commit();
			
			
			System.out.println("--------");
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
