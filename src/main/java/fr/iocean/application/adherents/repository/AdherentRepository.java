package fr.iocean.application.adherents.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.repository.AbstractJpaRepository;

@Repository
public class AdherentRepository extends AbstractJpaRepository<Adherent>{

	
	@Override
	protected Class<Adherent> getEntityClass() {
		return Adherent.class;
	}

	public List<Adherent> rechercheAdherents(String debutIdent, String partieNom, String colonneTri, Classement ordreTri) {
		TypedQuery<Adherent> requete = em.createQuery(
				"SELECT a FROM Adherent a\n" +
				"WHERE CAST(a.ID AS string)\n" +
					"LIKE CONCAT(:debutIdent, '%') AND\n" +
					"a.nom LIKE CONCAT('%', CONCAT(:partieNom, '%'))\n" +
				"ORDER BY :colonneTri " + (ordreTri != null ? ordreTri.toSQLString() : "ASC"),
				Adherent.class);
		requete.setParameter("debutIdent", debutIdent != null ? debutIdent : "");
		requete.setParameter("partieNom",  partieNom  != null ? partieNom  : "");
		requete.setParameter("colonneTri", colonneTri != null ? colonneTri : "nom, prenom");
		List<Adherent> resultat = requete.getResultList();
		em.close();
		return resultat;
	}
	
	public List<Emprunt> empruntsEnCours(Adherent adh) {
	
		TypedQuery<Emprunt> requete = em.createQuery(
				"SELECT e FROM Emprunt e\n" +
				"WHERE\n" +
					"e.adherent = :adherent AND\n" +
					"e.dateretour IS NULL",
				Emprunt.class);
		requete.setParameter("adherent", adh);
		List<Emprunt> resultat = requete.getResultList();
		em.close();
		return resultat;
	}
}
