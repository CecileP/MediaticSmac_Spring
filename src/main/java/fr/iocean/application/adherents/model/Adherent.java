package fr.iocean.application.adherents.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.persistence.ConvertDate;
import fr.iocean.application.persistence.IOEntity;

@Entity
public class Adherent extends Personne implements IOEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	protected Date dateNaissance;
	
	protected Date dateCotisation;

	@OneToMany(mappedBy = "adherent",fetch= FetchType.EAGER)
	protected Collection<Emprunt> listeMediaEmpruntes;

	@NotNull
	@Embedded
	protected Personne personne;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotBlank
	private String adresseMail;

	@Column
	private int montantCotisation;
	private String adresse;
	private Integer codePostal;
	private String ville;

	

	public Adherent() {
		listeMediaEmpruntes = new ArrayList<Emprunt>();
	}

	public Adherent(String nom, String prenom, Date date) {
		this.personne = new Personne(nom, prenom);
		this.dateNaissance = date;

		listeMediaEmpruntes = new ArrayList<Emprunt>();
	}

	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	} 
	
	
	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
	
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateCotisation() {
		return dateCotisation;
	}

	public void setDateCotisation(Date dateCotisation) {
		this.dateCotisation = dateCotisation;
	}

	public int calculAge() {
		int d1, m1, a1;
		LocalDate dateNaissance = ConvertDate.dateToLocalDate(this.dateNaissance);

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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
