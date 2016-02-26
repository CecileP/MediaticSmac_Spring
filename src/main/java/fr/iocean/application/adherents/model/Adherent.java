package fr.iocean.application.adherents.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.media.model.Media;

@Entity
public class Adherent extends Personne {
	
	@Column
	protected LocalDate dateNaissance, dateCotisation;
	
	@OneToMany(mappedBy = "adherent")
	protected Collection<Emprunt> listeMediaEmpruntes;
	
	@Column
	protected int montantCotisation;

	public Adherent() {
		listeMediaEmpruntes = new ArrayList<Emprunt>();
	}

	public Adherent(String nom, String prenom, LocalDate date) {
		super(nom, prenom);
		this.dateNaissance = date;

		listeMediaEmpruntes = new ArrayList<Emprunt>();
	}

	public int getMontantCotisation() {
		return montantCotisation;
	}

	public void setMontantCotisation(int montantCotisation) {
		this.montantCotisation = montantCotisation;
	}

	public boolean ajoutMedia(Emprunt m) {
		return listeMediaEmpruntes.add(m);
	}

	public boolean supprMedia(Media m) {
		return listeMediaEmpruntes.remove(m);
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public LocalDate getDateCotisation() {
		return dateCotisation;
	}

	public void setDateCotisation(LocalDate dateCotisation) {
		this.dateCotisation = dateCotisation;
	}


	public String toString() {
		return "Adherent [ID=" + ID + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", dateCotisation=" + dateCotisation +  "]";

	}

	public int calculAge() {
		int d1, m1, a1;
		d1 = dateNaissance.getDayOfMonth();
		m1 = dateNaissance.getMonthValue();
		a1 = dateNaissance.getYear();

		LocalDate now = LocalDate.now();
		int d2, m2, a2;
		d2 = now.getDayOfMonth();
		m2 = now.getMonthValue();
		a2 = now.getYear();

		if (m1 > m2) {
			return a2 - a1 - 1;
		} else {
			if (d1 > d2)
				return a2 - a1 - 1;
			else
				return a2 - a1;
		}

	}

}
