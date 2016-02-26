package fr.iocean.application.emprunt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.emprunt.service.EmpruntService;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

	@Autowired
	private EmpruntService service;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Emprunt ajouter(@RequestBody @Valid Emprunt emprunt) {
		this.service.ajouter(emprunt);
		return emprunt;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Emprunt> findAll() {
		return this.service.findAll();
	}

	@RequestMapping(value = "/media/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Emprunt> rechercherParMedia(@PathVariable long id) {
		List<Emprunt> l = this.service.rechercherParMedia(id);
		return l;
	}

	@RequestMapping(value = "/adherent/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Emprunt> rechercherParAdherent(@PathVariable long id) {
		List<Emprunt> l = this.service.rechercherParAdherent(id);
		return l;
	}
}
