package com.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Course;
import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;
import com.tutorial.hibernate.demo.entity.Review;
import com.tutorial.hibernate.demo.entity.Student;

public class GetCoursesForBarryDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			//create Course
			Course tempCourse = new Course("PackMan");
			
			//save the course .... levarage the cascade al
			System.out.println("saving the course");
			session.save(tempCourse);
			
			//create student
			Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
			Student tempStudent2 = new Student("Barry", "Fisher", "barry@gmail.com");
			
			//add student to course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			//save the student
			System.out.println("saving student");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("saved student...");
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done....!!!!");
		}finally {
			session.close();
			factory.close();
		}
	}

}
