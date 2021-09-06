package io.coursex.springbootstarter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.coursex.springbootstarter.model.Topic;
import io.coursex.springbootstarter.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;

	/*
	List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("spring", "Spring Boot", "Spring Boot Description"),
			new Topic("java", "Core Java", "Core Java Description"),
			new Topic("javascript", "Javascript", "Javascript Description")));
	*/
	
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
		//return this.topics;
	}

	public Topic getTopic(String id) {
		return topicRepository.findById(id).get();
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	public void addTopic(Topic topic) {
		topicRepository.save(topic);
		//topics.add(topic);
	}

	public void updateTopic(Topic topic, String id) {
		topicRepository.save(topic);
		/*
		for (int i = 0; i < topics.size(); i++) {
			if (topics.get(i).getId().equals(id)) {
				topics.set(i, topic);
				return topics;
			}
		}*/
		//return topics;
	}

	public void deleteTopic(String id) {
		topicRepository.deleteById(id);
		//topics.removeIf(t -> t.getId().equals(id));
		//return topics;
	}

}
