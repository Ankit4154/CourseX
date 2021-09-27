package io.course.albums.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.course.albums.model.Album;

@Service
public class AlbumsServiceImpl implements AlbumsService {

	@Override
	public List<Album> getAlbums(String userId) {
		List<Album> returnValue = new ArrayList<>();

		Album album = new Album();
		album.setUserId(userId);
		album.setAlbumId("album1Id");
		album.setDescription("album 1 description");
		album.setId(1L);
		album.setName("album 1 name");

		Album album2 = new Album();
		album2.setUserId(userId);
		album2.setAlbumId("album2Id");
		album2.setDescription("album 2 description");
		album2.setId(2L);
		album2.setName("album 2 name");

		returnValue.add(album);
		returnValue.add(album2);

		return returnValue;
	}
}
