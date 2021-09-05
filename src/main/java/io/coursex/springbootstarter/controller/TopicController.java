package io.coursex.springbootstarter.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.coursex.springbootstarter.model.Topic;

@RestController
public class TopicController {
	
	@RequestMapping("/topics")
	public List<Topic> getTopics(){
		return Arrays.asList(new Topic("spring", "Spring Boot", "Spring Boot Description"),
				new Topic("java", "Core Java", "Core Java Description"),
				new Topic("javascript", "Javascript", "Javascript Description"));
	}

}
