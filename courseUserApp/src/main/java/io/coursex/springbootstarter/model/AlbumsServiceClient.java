package io.coursex.springbootstarter.model;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="albums")
public interface AlbumsServiceClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumResponse> getAlbums(@PathVariable String id);
}
