package fr.iocean.application.media.model;

import javax.persistence.Entity;

@Entity
public class Livre extends Media {

	public Livre(String titre, String auteur) {
		super(titre, auteur);
	}

	public Livre() {
		super();
	}

	public String getType() {
		return "Livre";
	}

	@Override
	public int getNbJoursLoues() {
		return 30;
	}
}
