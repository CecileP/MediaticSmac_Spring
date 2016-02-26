package fr.iocean.application.media.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.repository.AbstractJpaRepository;

@Repository
public class MediaRepository extends AbstractJpaRepository<Media>{

	@Override
	protected Class<Media> getEntityClass() {
		return Media.class;
	}
	
	
	public List<Media> rechercheMedia(String titre, String auteur, String type) {
		TypedQuery<Media> tq = em.createQuery(
				"select m from Media m where m.titre LIKE :titre or m.auteur Like :auteur and m.dtype=:type", Media.class);

		tq.setParameter("titre", "%"+ titre+"%");
		tq.setParameter("auteur", "%"+auteur+"%");
		tq.setParameter("type", type);
		return tq.getResultList();
	}

	public List<Adherent> listeAdherents(Media m) {
		TypedQuery<Adherent> tq = em.createQuery(
				"select a from Emprunt e join e.adherent a "
				+ "join e.media m where e.media.ID=:id ", Adherent.class);
		tq.setParameter("id", m.getId());
		return tq.getResultList();
	}

}
