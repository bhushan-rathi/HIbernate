package com.tutorial.hibernate.demo;

import com.tutorial.hibernate.demo.entity.Course;
import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import com.tutorial.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class BreakingLazyDemoOnPurpose {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			//get the instructor from db
			int theId=1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "+
			"JOIN FETCH i.courses "+
			"where i.id=:theInstructorId"
			,  Instructor.class);
			
			//set parameter on query
			
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Hibernate classes________Instructor: "+tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			session.close();
			
			System.out.println("session is closed");
			
			System.out.println("Hibernate classes________Courses: "+tempInstructor.getCourses());
			
			System.out.println("Done....!!!!");
		}finally {
			session.close();
			factory.close();
		}
	}

}
