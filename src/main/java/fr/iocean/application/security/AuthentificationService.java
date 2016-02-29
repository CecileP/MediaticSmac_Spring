//package fr.iocean.application.security;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import fr.iocean.credentials.model.Credential;
//import fr.iocean.users.model.User;
//import fr.iocean.users.repository.UserRepository;
//
//@Service
//public class AuthentificationService implements UserDetailsService {
//
//	@Autowired
//	private Repository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(final String username) {
//		Optional<User> option = userRepository.findOneByLogin(username);
//		if (option.isPresent()) {
//			User user = option.get();
//			List<GrantedAuthority> rules = this.getUserCredentials(user);
//			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
//		}
//		throw new UsernameNotFoundException("username.not.found");
//	}
//
//	private List<GrantedAuthority> getUserCredentials(User user) {
//		List<GrantedAuthority> rules = new ArrayList<>();
//
//		List<Credential> credentials = user.getRules();
//		if(!CollectionUtils.isEmpty(credentials)){
//			for(Credential c:credentials){
//				rules.add(new SimpleGrantedAuthority(c.getCode()));				
//			}
//		}
//		return rules;
//	}
//
//}
