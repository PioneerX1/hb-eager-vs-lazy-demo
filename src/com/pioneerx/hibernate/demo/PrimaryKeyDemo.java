package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
					
					// 1. create 3 Student objects
					System.out.println("Creating new Student object....");
					Student student1 = new Student("Mary", "Higgins", "mhiggs@bigdiggs.com");
					Student student2 = new Student("Bonita", "Applebaum", "bonita@applebum.com");
					Student student3 = new Student("Svark", "Glorp", "skfla@blgokee.com");
					
					// 2. begin transaction
					System.out.println("Beginning transaction ....");
					session.beginTransaction();
					
					// 3. save the student object
					System.out.println("Saving student....");
					session.save(student1);
					session.save(student2);
					session.save(student3);
					
					// 4. commit transaction
					System.out.println("Commiting transaction....");
					session.getTransaction().commit();
					
					System.out.println("Done!");
					
				} finally {
					factory.close();
				}
		
		
	}

}
