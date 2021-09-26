package io.course.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseConfigServerApplication {

	public static void main(String[] args) {
		System.out.println("This is test");
		SpringApplication.run(CourseConfigServerApplication.class, args);
	}

}
