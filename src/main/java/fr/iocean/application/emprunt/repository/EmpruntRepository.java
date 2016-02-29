package fr.iocean.application.emprunt.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.repository.AbstractJpaRepository;

@Repository
public class EmpruntRepository extends AbstractJpaRepository<Emprunt> {

	@Override
	protected Class<Emprunt> getEntityClass() {
		return Emprunt.class;
	}

	public List<Emprunt> rechercherParMedia(long id) {
		String requete = "FROM Emprunt e WHERE e.media = :mediaId";
		TypedQuery<Emprunt> q = em.createQuery(requete, Emprunt.class);
		q.setParameter("mediaId", new Long(id));
		return q.getResultList();
	}

	public List<Emprunt> rechercherParAdherent(long id) {
		String requete = "FROM Emprunt e WHERE e.adherent = :adherentId";
		TypedQuery<Emprunt> q = em.createQuery(requete, Emprunt.class);
		q.setParameter("adherentId", new Long(id));
		return q.getResultList();
	}
}
