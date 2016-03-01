package fr.iocean.application.utilisateur.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import fr.iocean.application.adherents.model.Personne;
import fr.iocean.application.persistence.IOEntity;
import fr.iocean.application.security.credentials.model.Credential;

@Entity
public class Utilisateur implements IOEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Embedded
	protected Personne personne;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Credential> rules;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String login;
	@Column
	private String motDePasse;

	public Utilisateur() {
		this.personne=new Personne();
	}

	public List<Credential> getRules() {
		return rules;
	}

	public void setRules(List<Credential> rules) {
		this.rules = rules;
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
