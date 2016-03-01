package fr.iocean.application.media.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void create(@RequestBody @Valid Media resource) {
		mediaService.create(resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Media findById(@PathVariable Long id) throws NotFoundException {
		return mediaService.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Media> findAll() {
		return mediaService.findAll();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Media update(@PathVariable Long id, @RequestBody @Valid Media resource) throws NotFoundException {
		return mediaService.update(id, resource);
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws NotFoundException {
			mediaService.delete(id);
	}
	
	@RequestMapping(value = "search",method = RequestMethod.GET)
	@ResponseBody
	public  List<Media> rechercheMedia(@RequestParam("titre") String titre, @RequestParam("auteur") String auteur, @RequestParam("type") String type) throws NotFoundException {
		return mediaService.rechercheMedia(titre, auteur, type);
	}
	
}
