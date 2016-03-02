package fr.iocean.application.media.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.repository.AbstractJpaRepository;

@Repository
public class MediaRepository extends AbstractJpaRepository<Media> {

	@Override
	protected Class<Media> getEntityClass() {
		return Media.class;
	}

	@SuppressWarnings("unchecked")
	public List<Media> rechercheMedia(String titre, String auteur, String type) throws NotFoundException {
		Criteria crt = getSession().createCriteria(getEntityClass());
		if (!StringUtils.isEmpty(titre)) {
			crt.add(Restrictions.like("titre", "%" + titre + "%"));
		}
		if (!StringUtils.isEmpty(auteur)) {
			crt.add(Restrictions.like("auteur", "%" + auteur + "%"));
		}
		if (!StringUtils.isEmpty(type)) {
			crt.add(Restrictions.eq("type", type));
		}
		
		return (List<Media>) crt.list();
	}

	public List<Adherent> listeAdherents(Media m) {
		TypedQuery<Adherent> tq = em.createQuery(
				"select a from Emprunt e join e.adherent a " + "join e.media m where e.media.ID=:id ", Adherent.class);
		tq.setParameter("id", m.getId());
		return tq.getResultList();
	}

}
