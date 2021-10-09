package io.coursex.springbootstarter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "albums")//, fallback = AlbumsFallback.class)
public interface AlbumsServiceClient {

	// Changed uri to ss for raising FeignException
	// @GetMapping("/users/{id}/albumss")
	@GetMapping("/users/{id}/albums")
	public List<AlbumResponse> getAlbums(@PathVariable String id);
}

/*
@Component
class AlbumsFallback implements AlbumsServiceClient {

	@Override
	public List<AlbumResponse> getAlbums(String id) {
		List<AlbumResponse> returnValue = new ArrayList<>();
        
		AlbumResponse albumEntity = new AlbumResponse();
        albumEntity.setUserId(id);
        albumEntity.setAlbumId("Fallback AnkitId");
        albumEntity.setDescription("Fallback Ankit description");
        albumEntity.setId(1L);
        albumEntity.setName("Fallback Ankit album 1 name");
        returnValue.add(albumEntity);
        
        return returnValue;
	}
}
*/
