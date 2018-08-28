package com.tutorial.hibernate.demo;

import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import com.tutorial.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class getInstructorDetailDemo {

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
			 
			int theId =2;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,  theId);
			
			System.out.println("InstructorDetail object details"+ tempInstructorDetail);
			
			System.out.println("Assocciated Instructor"+ tempInstructorDetail.getInstructor());
			System.out.println("Deleting session object"+tempInstructorDetail);
			session.delete(tempInstructorDetail);
			// commit transaction 
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
