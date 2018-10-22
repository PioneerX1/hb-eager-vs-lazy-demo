package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.pioneerx.hibernate.demo.entity.Course;
import com.pioneerx.hibernate.demo.entity.Instructor;
import com.pioneerx.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			// option2: Hibernate query with HQL
			
			// get instructor from db
			int id = 1;
	
			Query<Instructor> query = session.createQuery("select i from Instructor i " 
					+ "JOIN FETCH i.courses " 
					+ "where i.id=:theInstructorId",
					Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", id);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("test99 Instructor: " + tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			// retrieve courses for the instructor
						System.out.println("test99 Courses: " + tempInstructor.getCourses());
			
			System.out.println("test99 Done!");
			
		} finally {
			session.close();
			factory.close();
		}

	}

}
