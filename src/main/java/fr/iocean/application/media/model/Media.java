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

import fr.iocean.application.emprunt.model.Emprunt;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"titre","auteur"})})
@Inheritance
public abstract class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long ID;

	@Column
	private String titre;
	@Column
	private String auteur;

	@OneToMany(mappedBy = "media")
	private List<Emprunt> emprunts;

	@Column
	private boolean emprunte;
	
	@Column(insertable=false, updatable=false )
	protected String dtype;

	public Media() {
		emprunts = new ArrayList<Emprunt>();
		emprunte = true;
	}

	public abstract String getType();

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

	public Long getID() {
		return ID;
	}

	public abstract int getNbJoursLoues();

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void ajouterEmprunt(Emprunt e) {
		if (emprunts.contains(e))
			return;

		emprunts.add(e);
		emprunte = true;
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
		return "Media [ID=" + ID + ", titre=" + titre + ", auteur=" + auteur + ", emprunte=" + emprunte + "]";
	}

	public boolean estEmprunte() {
		return emprunte;
	}

	protected Media(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.emprunts = new ArrayList<Emprunt>();
		this.emprunte = false;
	}

}
