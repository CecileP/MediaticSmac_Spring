package fr.iocean.application.emprunt.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.service.AdherentService;
import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.emprunt.repository.EmpruntRepository;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.Media.TypeMedia;
import fr.iocean.application.media.service.MediaService;
import fr.iocean.application.persistence.ConvertDate;


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
		if(e.getDateRetour()== null){
			LocalDate retour = ConvertDate.dateToLocalDate(e.getDateEmprunt()).plusDays(getNbJoursLoues(e.getMedia()));
			e.setDateRetour(ConvertDate.localDateToDate(retour));
		}
		this.repository.save(e);
	}

	public void modifier(Long id, Emprunt emprunt) {
		Emprunt e = new Emprunt(emprunt);
		e.setId(id);
		Emprunt eOld=findById(id);
		if(!e.getDateEmprunt().equals(eOld.getDateEmprunt())){
			LocalDate retour = ConvertDate.dateToLocalDate(e.getDateEmprunt()).plusDays(getNbJoursLoues(e.getMedia()));
			e.setDateRetour(ConvertDate.localDateToDate(retour));
		}
		
		this.repository.update(e);
	}

	public Emprunt findById(Long id){
		Emprunt empt=new Emprunt();
		try {
			empt=this.repository.findOne(id);
		} catch (NotFoundException e) {
			System.out.println(e);
		}
		return empt;
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
			a = adherentService.findById(id);
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
	
	@Value("${media.nbjourretour.cd}")
	private int nbJours_CD;
	
	@Value("${media.nbjourretour.dvd}")
	private int nbJours_DVD;
	
	@Value("${media.nbjourretour.livre}")
	private int nbJours_Livre;
	
		public int getNbJoursLoues(Media media){
		if(media.getTypeMedia()!=null){
			if(media.getTypeMedia()==TypeMedia.CD){
				return nbJours_CD;
			}
			if(media.getTypeMedia()==TypeMedia.DVD){
				return nbJours_DVD;
			}
			if(media.getTypeMedia()==TypeMedia.Livre){
				return nbJours_Livre;
			}
		}
		
		return 0;
	}
}
