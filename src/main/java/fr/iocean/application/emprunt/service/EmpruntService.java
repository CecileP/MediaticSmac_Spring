package fr.iocean.application.emprunt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.emprunt.repository.EmpruntRepository;

@Repository
public class EmpruntService {

	@Autowired
	private EmpruntRepository repository;

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
		return this.repository.rechercherParMedia(id);
	}

	public List<Emprunt> rechercherParAdherent(long id) {
		return this.repository.rechercherParAdherent(id);
	}
	// public void supprimer(Long id) throws EmpruntNonTrouve {
	// Emprunt e = this.repository.findOne(id);
	// if (e == null)
	// throw new EmpruntNonTrouve("id", id);
	//
	// this.repository.remove(e);
	// }
}
