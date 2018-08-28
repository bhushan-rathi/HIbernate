package com.tutorial.hibernate.demo;

import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			Instructor tempInstructor = new Instructor("Bhalla", "Mhatre", "Bhall@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com", "Cartoon");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
				//start a transaction
			session.beginTransaction();
			System.out.println("saving tempInstructor"+ tempInstructor);
			session.save(tempInstructor);
				// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}finally {
			factory.close();
		}
	}

}
