package com.tutorial.hibernate.demo;

import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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
			
			
			
			//start a transaction
			session.beginTransaction();
			 
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found Instructror"+ tempInstructor);
			
			if(tempInstructor!=null) {
				System.out.println("deleting record of : "+tempInstructor);
				session.delete(tempInstructor);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}finally {
			factory.close();
		}
	}

}
