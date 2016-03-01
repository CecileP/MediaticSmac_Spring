package fr.iocean.application.utilisateur.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.iocean.application.adherents.model.Personne;
import fr.iocean.application.persistence.IOEntity;

@Entity
public class Utilisateur extends Personne implements IOEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Embedded
	protected Personne personne;
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Utilisateur() {
		super();
	}


	public Utilisateur(String nom, String prenom) {
		super(nom, prenom);
	}

	public Utilisateur(String nom, String prenom, String login, String motDePasse) {
		super(nom, prenom);
		this.login = login;
		this.motDePasse = motDePasse;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Column(unique=true)
	private String login;
	@Column
	private String motDePasse;
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;		
	}
}
