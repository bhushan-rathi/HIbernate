package com.tutorial.hibernate.demo;

import com.tutorial.hibernate.demo.entity.Course;
import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import com.tutorial.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor" +tempInstructor);
			//get courses of the Instructor
			System.out.println("courses "+tempInstructor.getCourses());
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}finally {
			session.close();
			factory.close();
		}
	}

}
