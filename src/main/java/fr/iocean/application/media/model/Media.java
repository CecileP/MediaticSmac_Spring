package fr.iocean.application.media.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.persistence.IOEntity;

 

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"titre","auteur"})})
@Inheritance
public class Media implements IOEntity {

	public enum TypeMedia{
		DVD,
		CD,
		Livre};

	
	private static final long serialVersionUID = -5876776304798188521L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeMedia typeMedia;


	@NotBlank
	@Length(max=50)
	private String titre;
	
	@NotBlank
	@Length(max=50)
	private String auteur;

	@OneToMany(mappedBy = "media", fetch=FetchType.EAGER)
	private List<Emprunt> emprunts;

	private boolean emprunte;

	public Media() {
		emprunts = new ArrayList<Emprunt>();
		emprunte = false;
	}
	
	public Media(String titre, String auteur,TypeMedia type) {
		this.titre = titre;
		this.auteur = auteur;
		this.typeMedia=type;
		this.emprunts = new ArrayList<Emprunt>();
		this.emprunte = false;
	}
	
	public Media(String titre, String auteur,String type) throws NotFoundException {
		this.titre = titre;
		this.auteur = auteur;
		this.emprunts = new ArrayList<Emprunt>();
		this.emprunte = false;
		
		this.typeMedia= convertStringToType(type);
				
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}




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


	public TypeMedia getTypeMedia() {
		return typeMedia;
	}

	public void setTypeMedia(TypeMedia type) {
		this.typeMedia = type;
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


	public static TypeMedia convertStringToType(String type) throws NotFoundException{
		TypeMedia typeMedia=null;
		try {
			typeMedia = TypeMedia.valueOf(type);
		} catch (Exception e) {
				throw new NotFoundException("type media non trouve : "+type);
		}
		return typeMedia;
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
		return "Media [ID=" + id + ", titre=" + titre + ", auteur=" + auteur + ", emprunte=" + emprunte + ", type="+this.getTypeMedia()+"]";
	}
}
