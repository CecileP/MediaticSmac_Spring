package fr.iocean.application.media.model;

import javax.persistence.Entity;

@Entity
public class CD extends Media {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CD(){
		super();
	}

	public CD (String titre, String auteur) {
		super(titre, auteur);

	}

	public String getType() {
		return "CD";
	}

	@Override
	public int getNbJoursLoues() {
		
		return 15;
	}
}
