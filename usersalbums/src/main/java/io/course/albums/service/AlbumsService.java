package io.course.albums.service;

import java.util.List;

import io.course.albums.model.Album;

public interface AlbumsService {

	List<Album> getAlbums(String userId);

}
