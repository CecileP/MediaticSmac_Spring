package fr.iocean.application.emprunt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.service.AdherentService;
import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.emprunt.repository.EmpruntRepository;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;

@Repository
public class EmpruntService {

	@Autowired
	private EmpruntRepository repository;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private AdherentService adherentService;

	public void ajouter(Emprunt emprunt) {
		Emprunt e = new Emprunt(emprunt);
		this.repository.save(e);
	}

	public void modifier(Long id, Emprunt emprunt) {
		Emprunt e = new Emprunt(emprunt);
		e.setId(id);
		this.repository.update(e);
	}

	public List<Emprunt> findAll() {
		return this.repository.findAll();
	}

	public List<Emprunt> rechercherParMedia(long id) {
		Media m = null;

		try {
			m = mediaService.findById(id);
		} catch (NotFoundException e) {
			return new ArrayList<>();
		}
		return this.repository.rechercherParMedia(m);
	}

	public List<Emprunt> rechercherParAdherent(long id) {
		Adherent a;

		try {
			a = adherentService.findOne(id);
		} catch (NotFoundException e) {
			return new ArrayList<>();
		}
		return this.repository.rechercherParAdherent(a);

	}
	// public void supprimer(Long id) throws EmpruntNonTrouve {
	// Emprunt e = this.repository.findOne(id);
	// if (e == null)
	// throw new EmpruntNonTrouve("id", id);
	//
	// this.repository.remove(e);
	// }
}
