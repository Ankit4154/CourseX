package io.coursex.springbootstarter.repository;

import org.springframework.data.repository.CrudRepository;

import io.coursex.springbootstarter.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
