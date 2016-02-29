package fr.iocean.application.media.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.persistence.IOEntity;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"titre","auteur"})})
@Inheritance
public abstract class Media implements IOEntity {

	private static final long serialVersionUID = -5876776304798188521L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	

	@NotBlank
	@Length(max=50)
	private String titre;
	
	@NotBlank
	@Length(max=50)
	private String auteur;

	@OneToMany(mappedBy = "media")
	private List<Emprunt> emprunts;

	@NotBlank
	private boolean emprunte;

	public Media() {
		emprunts = new ArrayList<Emprunt>();
		emprunte = true;
	}
	
	protected Media(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.emprunts = new ArrayList<Emprunt>();
		this.emprunte = false;
	}

	
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public abstract String getType();
	public abstract int getNbJoursLoues();


	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	
	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void ajouterEmprunt(Emprunt e) {
		if (emprunts.contains(e))
			return;

		emprunts.add(e);
		emprunte = true;
	}
	
	public boolean estEmprunte() {
		return emprunte;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.toLowerCase().equals(other.auteur.toLowerCase()))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.toLowerCase().equals(other.titre.toLowerCase()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Media [ID=" + id + ", titre=" + titre + ", auteur=" + auteur + ", emprunte=" + emprunte + "]";
	}
}
