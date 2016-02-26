package fr.iocean.application.adherents.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.service.AdherentService;
import fr.iocean.application.exception.NotFoundException;

@RestController
@RequestMapping("/api/adherent")
public class AdhrentController {

	@Autowired
	private AdherentService adherentService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Adherent> findAll() {
		return adherentService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Adherent findById(@PathVariable Long id) throws NotFoundException {
		return adherentService.findOne(id);
	}

	// @PreAuthorize("hasAuthority('MANAGE_Adherent')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Adherent resource) {
		adherentService.save(resource);
	}

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) throws NotFoundException {
		adherentService.delete(id);
	}

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody @Valid Adherent resource) throws NotFoundException {
		adherentService.update(id, resource);
	}
}
