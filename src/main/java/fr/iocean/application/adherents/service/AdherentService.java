package fr.iocean.application.adherents.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.repository.AdherentRepository;
import fr.iocean.application.exception.NotFoundException;

@Service
public class AdherentService {

	@Autowired
	AdherentRepository adherentRepository;

	public void save(Adherent adh){
		adherentRepository.save(adh);
	}

	public List<Adherent> findAll() {
		return adherentRepository.findAll();
	}
	
	public Adherent update(Long id, Adherent adherent) throws NotFoundException {
		if(adherentRepository.findOne(id)==null){
			throw new NotFoundException();
		}
		adherent.setId(id);
		return adherentRepository.save(adherent);
	}

	public Adherent findById(Long id) throws NotFoundException {
		Adherent a = adherentRepository.findOne(id);
		return a;
	}

	public void delete(Long id) throws NotFoundException {
		Adherent a = adherentRepository.findOne(id);
		adherentRepository.delete(a);
	}
}
