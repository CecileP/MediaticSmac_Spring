package fr.iocean.application.media.model;

import javax.persistence.Entity;

@Entity
public class DVD extends Media {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DVD (String titre, String auteur) {
		super(titre, auteur);
	}
	
	public DVD(){
		super();
	}

	public String getType() {
		return "DVD";
	}

	@Override
	public int getNbJoursLoues() {
		return 15;
	}
}
