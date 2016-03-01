package fr.iocean.application.security.credentials.repository;

import org.springframework.stereotype.Repository;

import fr.iocean.application.repository.AbstractJpaRepository;
import fr.iocean.application.security.credentials.model.Credential;

@Repository
public class CredentialRepository extends AbstractJpaRepository<Credential>{

	@Override
	protected Class<Credential> getEntityClass() {
		return Credential.class;
	}
}
