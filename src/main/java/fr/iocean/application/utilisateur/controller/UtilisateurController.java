package fr.iocean.application.utilisateur.controller;

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

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.utilisateur.model.Utilisateur;
import fr.iocean.application.utilisateur.service.UtilisateurService;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Utilisateur> findAll() {
		return utilisateurService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Utilisateur findById(@PathVariable Long id) throws NotFoundException {
		return utilisateurService.findOne(id);
	}

	// @PreAuthorize("hasAuthority('MANAGE_USER')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Utilisateur resource) {
		utilisateurService.save(resource);
	}

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) throws NotFoundException {
		utilisateurService.delete(id);
	}

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody @Valid Utilisateur resource) throws NotFoundException {
		utilisateurService.updateUtilisateur(id, resource);
	}
}
