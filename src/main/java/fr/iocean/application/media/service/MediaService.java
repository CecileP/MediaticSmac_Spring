package fr.iocean.application.media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.repository.MediaRepository;

@Service
@Transactional
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	
	public MediaRepository getMediaRepository() {
		return mediaRepository;
	}

	public void setMediaRepository(MediaRepository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}
	
	
	public void create(Media resource) {
		this.mediaRepository.save(resource);
	}

	public Media findById(Long id) throws NotFoundException {
		return this.mediaRepository.findOne(id);
	}

	public Media update(Long id, Media resource) throws NotFoundException {
		if( this.findById(id)==null){
			throw new NotFoundException();
		}
		resource.setId(id);
		return this.mediaRepository.save(resource);
	}
	
	public List<Media> findAll(){
		return this.mediaRepository.findAll();
	}
	
	public void delete(Long id) throws NotFoundException{
		Media resource = findById(id);
		this.mediaRepository.delete(resource);
	}
	
	
	public List<Media> rechercheMedia(String titre, String auteur, String type) throws NotFoundException{
		return this.mediaRepository.rechercheMedia(titre, auteur, type);
	}
	
}
