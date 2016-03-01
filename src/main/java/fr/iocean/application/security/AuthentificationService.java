package fr.iocean.application.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fr.iocean.application.security.credentials.model.Credential;
import fr.iocean.application.utilisateur.model.Utilisateur;
import fr.iocean.application.utilisateur.repository.UtilisateurRepository;

@Service
public class AuthentificationService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		Optional<Utilisateur> option = utilisateurRepository.findOneByLogin(username);
		if (option.isPresent()) {
			Utilisateur utilisateur = option.get();
			List<GrantedAuthority> rules = this.getUserCredentials(utilisateur);
			return new org.springframework.security.core.userdetails.User(utilisateur.getLogin(), utilisateur.getMotDePasse(), rules);
		}
		throw new UsernameNotFoundException("username.not.found");
	}

	private List<GrantedAuthority> getUserCredentials(Utilisateur utilisateur) {
		List<GrantedAuthority> rules = new ArrayList<>();

		List<Credential> credentials = utilisateur.getRules();
		if(!CollectionUtils.isEmpty(credentials)){
			for(Credential c:credentials){
				rules.add(new SimpleGrantedAuthority(c.getCode()));				
			}
		}
		return rules;
	}

}
