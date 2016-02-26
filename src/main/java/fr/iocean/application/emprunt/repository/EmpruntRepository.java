package fr.iocean.application.emprunt.repository;

import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.repository.AbstractJpaRepository;

public class EmpruntRepository extends AbstractJpaRepository<Emprunt> {

	@Override
	protected Class<Emprunt> getEntityClass() {
		return Emprunt.class;
	}

}
