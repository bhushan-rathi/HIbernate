package com.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Course;
import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import com.tutorial.hibernate.demo.entity.Review;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			
			//get the course
			int theId=10;
			Course tempCourse=session.get(Course.class, theId);

			
			System.out.println("Deleting the course......");
			//print the course
			System.out.println("Course"+ tempCourse);
			
			
			//print the course review
			System.out.println("reviews"+tempCourse.getReviews());
			//deleting the course
			session.delete(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}finally {
			session.close();
			factory.close();
		}
	}

}
