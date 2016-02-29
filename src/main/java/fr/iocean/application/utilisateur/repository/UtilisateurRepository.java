package fr.iocean.application.utilisateur.repository;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.repository.AbstractJpaRepository;
import fr.iocean.application.utilisateur.model.Utilisateur;

@Repository
public class UtilisateurRepository extends AbstractJpaRepository<Utilisateur>{

	@Override
	protected Class<Utilisateur> getEntityClass() {
		return Utilisateur.class;
	}
	
	@Transactional
	public Optional<Utilisateur> findOneByLogin(String login){
		Criteria criteria = getSession().createCriteria(Utilisateur.class);
		return Optional.of((Utilisateur)criteria.add(Restrictions.eq("login", login)).uniqueResult());
	}

}
