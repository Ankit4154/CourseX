package io.coursex.springbootstarter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.coursex.springbootstarter.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

	public List<Course> findByTopicId(String topicId);
}
