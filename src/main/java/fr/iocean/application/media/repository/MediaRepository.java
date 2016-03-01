package fr.iocean.application.media.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.Media.TypeMedia;
import fr.iocean.application.repository.AbstractJpaRepository;

@Repository
public class MediaRepository extends AbstractJpaRepository<Media> {

	@Override
	protected Class<Media> getEntityClass() {
		return Media.class;
	}

	public List<Media> rechercheMedia(String titre, String auteur, String type) throws NotFoundException {
		StringBuilder str = new StringBuilder();
		str.append("SELECT m FROM Media m WHERE ");

		int count = 0;
		if (titre.length() != 0)
			count++;
		if (auteur.length() != 0)
			count++;
		if (type.length() != 0)
			count++;

		if (titre != null && titre != "") {
			str.append(" m.titre LIKE :titre");
			count--;
			if (count != 0) {
				str.append(" AND ");
			}
		}
		if (auteur != null && auteur != "") {
			str.append(" m.auteur LIKE :auteur");
			count--;
			if (count != 0) {
				str.append(" AND ");
			}
		}
		if (type != null && type != "") {
			str.append(" m.typeMedia=:type");
		}

		System.out.println("ttrtrt" + str.toString());

		
		
		
		TypedQuery<Media> tq = em.createQuery(str.toString(), Media.class);

		if (titre.length() != 0) {
			tq.setParameter("titre", "%" + titre + "%");
		}
		if (auteur.length() != 0) {
			tq.setParameter("auteur", "%" + auteur + "%");
		}
		if (type.length() != 0) {
			tq.setParameter("type", Media.convertStringToType(type));
		}
		return tq.getResultList();
	}

	public List<Adherent> listeAdherents(Media m) {
		TypedQuery<Adherent> tq = em.createQuery(
				"select a from Emprunt e join e.adherent a " + "join e.media m where e.media.ID=:id ", Adherent.class);
		tq.setParameter("id", m.getId());
		return tq.getResultList();
	}

}
