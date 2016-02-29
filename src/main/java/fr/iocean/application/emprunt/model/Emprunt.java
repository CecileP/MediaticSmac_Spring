package fr.iocean.application.emprunt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.persistence.IOEntity;


@Entity
public class Emprunt implements IOEntity {
	
	private static final long serialVersionUID = -1487635178830374220L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	protected Adherent adherent;
	
	@ManyToOne
	@JsonIgnoreProperties("emprunts")
	protected Media media;
	
	@Column
	protected LocalDate dateEmprunt, dateRetour;

	public Emprunt(Adherent a, Media m, LocalDate dateEmprunt) {
		this.adherent = a;
		this.media = m;
		this.dateEmprunt = dateEmprunt;
		dateRetour = null;
		a.ajoutMedia(this);
		m.ajouterEmprunt(this);
	}
	
	public Emprunt(){}
	public Emprunt(Emprunt e) {
		this.adherent = e.adherent;
		this.media = e.media;
		this.dateEmprunt = e.dateEmprunt;
		this.dateRetour = e.dateRetour;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
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
