package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Instructor;
import com.pioneerx.hibernate.demo.entity.InstructorDetail;
import com.pioneerx.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			System.out.println("Beginning transaction ....");
			session.beginTransaction();

			// get instructor detail object
			int tempId = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, tempId);
			
			// print instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print associated instructor
			System.out.println("associated instructor: " + tempInstructorDetail.getInstructor());

			// commit transaction
			System.out.println("Commiting transaction....");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
