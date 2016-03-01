package fr.iocean.application.utilisateur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.utilisateur.model.Utilisateur;
import fr.iocean.application.utilisateur.repository.UtilisateurRepository;



@Service
public class UtilisateurService {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void save(Utilisateur Utilisateur) {
		Utilisateur usr = Utilisateur;
		usr.setMotDePasse(passwordEncoder.encode(usr.getMotDePasse()));
		utilisateurRepository.save(usr);

	}

	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}

	public Optional<Utilisateur> findOnebyLogin(String login) {
		return utilisateurRepository.findOneByLogin(login);
	}

	public void updateUtilisateur(Long id, Utilisateur Utilisateur) throws NotFoundException {
		Utilisateur u = utilisateurRepository.findOne(id);

		if (u.getId() == Utilisateur.getId()) {
			Utilisateur usr = Utilisateur;
			usr.setMotDePasse(passwordEncoder.encode(usr.getMotDePasse()));
			utilisateurRepository.save(usr);
		}
	}

	public Utilisateur findOne(Long id) throws NotFoundException {
		Utilisateur u = utilisateurRepository.findOne(id);

		if (u == null)
			throw new NotFoundException();
		return u;
	}

	public void delete(Long id) throws NotFoundException {
		Utilisateur u = utilisateurRepository.findOne(id);
		utilisateurRepository.delete(u);
	}

}