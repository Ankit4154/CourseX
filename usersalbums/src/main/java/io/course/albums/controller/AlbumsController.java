package io.course.albums.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.course.albums.model.Album;
import io.course.albums.service.AlbumsService;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {

	@Autowired
	AlbumsService albumsService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Album> userAlbums(@PathVariable String id) {
		List<Album> returnValue = new ArrayList<>();
		List<Album> albumsEntities = albumsService.getAlbums(id);

		if (albumsEntities == null || albumsEntities.isEmpty()) {
			return returnValue;
		}

		Type listType = new TypeToken<List<Album>>() {}.getType();

		returnValue = new ModelMapper().map(albumsEntities, listType);
		logger.info("Returning " + returnValue.size() + " albums");
		return returnValue;

	}
}
