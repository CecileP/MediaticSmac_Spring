package fr.iocean.application.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.persistence.IOEntity;

@Repository
public abstract class AbstractJpaRepository<T extends IOEntity> {

	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager em;

	@PostConstruct
	public void init() {
		entityClass = getEntityClass();
	}

	protected abstract Class<T> getEntityClass();

	protected Session getSession() {
		return em.unwrap(Session.class);
	}


	@Transactional
	public T save(T entity) {
		if (isNew(entity)) {
			em.persist(entity);
		} else if (!em.contains(entity)) {
			return em.merge(entity);
		}

		return entity;
	}
	
	public boolean isNew(T entity){
		return entity.getId() == null;
	}

	@Transactional
	public T findOne(Long id) {
		return em.find(entityClass, id);
	}
	
	public void update(Object entity) {
		em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll() {
		return getSession().createCriteria(entityClass).list();
	}

	

	@Transactional
	public void delete(T entity) {
		if (!getSession().contains(entity))
			em.remove(getSession().merge(entity));
		else
			em.remove(entity);
	}
	
	

}
