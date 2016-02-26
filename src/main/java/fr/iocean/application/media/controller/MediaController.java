package fr.iocean.application.media.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;


@RestController
@RequestMapping("/api/medias")
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasAuthority('MANAGER_Media')")
	public void create(@RequestBody @Valid Media resource) {
		mediaService.create(resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('MANAGER_Media','Media')")
	public Media findById(@PathVariable Long id) throws NotFoundException {
		return mediaService.findById(id);
	}

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	@PreAuthorize("hasAnyAuthority('MANAGER_Media','Media')")
//	public List<Media> findAll() {
//		return mediaService.findAll();
//	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
//	@PreAuthorize("hasAuthority('MANAGER_Media')")
	public Media update(@PathVariable Long id, @RequestBody @Valid Media resource) {
		return mediaService.update(id, resource);
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
//	@PreAuthorize("hasAuthority('MANAGER_Media')")
	public void delete(@PathVariable Long id) throws NotFoundException {
		Media resource = mediaService.findById(id);
		if(resource !=null)
			mediaService.delete(resource);
	}
	
}
