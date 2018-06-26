package com.pioneerx.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// begin transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();
			
			// query students
			List<Student> students = session.createQuery("from Student").getResultList();
			
			// display ALL students
			displayStudents(students);
			
			// query specific students
			students = session.createQuery("from Student s where s.lastName='Higgins'").getResultList();
			
			// display students with last name of Higgins
			displayStudents(students);
			
			// query students with last name of Higgins or first name of Daffy
			students = session.createQuery("from Student s where s.lastName='Higgins'" 
					+ " OR s.firstName='Daffy'").getResultList();
			
			// display results
			displayStudents(students);
			
			// query students with email LIKE '.com'
			students = session.createQuery("from Student s where s.email LIKE '%.com'").getResultList();
			displayStudents(students);
			
			
			// commit transaction
			System.out.println("Commiting transaction....");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		System.out.println("Displaying students....");
		System.out.println("--------");
		for(Student tempStudent : students) {
			System.out.println(tempStudent);
		}
		System.out.println("---------");
	}

}
