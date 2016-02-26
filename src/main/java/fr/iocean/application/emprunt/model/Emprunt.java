package fr.iocean.application.emprunt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.adherents.model.Adherent;


@Entity
public class Emprunt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;

	@ManyToOne
	protected Adherent adherent;
	
	@ManyToOne
	protected Media media;
	
	@Column
	protected LocalDate dateEmprunt, dateRetour;

	// lien avec adherent

	///

	// lien avec media

	///

	public Emprunt(Adherent a, Media m, LocalDate dateEmprunt) {
		this.adherent = a;
		this.media = m;
		this.dateEmprunt = dateEmprunt;
		dateRetour = dateEmprunt.plusDays(media.getNbJoursLoues());
		a.ajoutMedia(this);
		m.ajouterEmprunt(this);
	}
	
	public Emprunt(){}
	
	public Long getID() {
		return ID;
	}
	

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	public String toString() {
		return "Emprunt [adherent=" + adherent + ", media=" + media + ", dateRetour=" + dateRetour + "]";
	}

}
