package com.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.dao.interfaces.IDAO;
import com.practica.data.Pais;
import com.practica.exceptions.DAOException;

public class PaisDAO implements IDAO<String, Pais> {
	private static final Logger log = LoggerFactory.getLogger(PaisDAO.class);
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Override
	public List<Pais> findAll() throws DAOException {
		init();
		return manager.createNamedQuery("Pais.findAll", Pais.class).getResultList();
	}


	@Override
	public Pais findOne(String key) throws DAOException {
		init();
		return manager.find(Pais.class, key);
	}

	@Override
	public void create(Pais element) throws DAOException {
		log.info("Method:[crear]");
		log.debug("Parmetros:[Pais element:" + element + "]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
//				manager.getTransaction().begin();
				manager.persist(element);
//				manager.getTransaction().commit();
				log.debug("[crear]Commit - Creamos pais");
			} catch (Exception e) {
				log.error("Error", e);
				manager.getTransaction().rollback();
				log.debug("[crear]Rollback");
				throw new DAOException(e);
			}

		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Pais element) throws DAOException {
		log.info("Method:[update]");
		log.debug("Parmetros:[Pais element:" + element + "]");

		try {
			init();

			try {
				log.debug("[update]Iniciamos transaccion");
//				manager.getTransaction().begin();
//				manager.persist(element);

				Pais pais = findOne(element.getId());
				pais.setNombre(element.getNombre());
//				pais.setRegion(pais.getRegion());

//				manager.getTransaction().commit();
				log.debug("[update]Commit - Actualizamos pais");
			} catch (Exception e) {
				log.error("Error", e);
//				manager.getTransaction().rollback();
				log.debug("[update]Rollback");
				throw new DAOException(e);
			}

		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(String key) throws DAOException {
		log.info("Method:[delete]");
		log.debug("Parmetros:[Pais element:" + key + "]");

		try {
			init();

			try {
				log.debug("[delete]Iniciamos transaccion");
//				manager.getTransaction().begin();
				manager.remove(findOne(key));

//				manager.getTransaction().commit();
				log.debug("[delete]Commit - Borrar pais");
			} catch (Exception e) {
				log.error("Error", e);
//				manager.getTransaction().rollback();
				log.debug("[delete]Rollback");
				throw new DAOException(e);
			}

		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}

	private void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("practicaRodrigo");
		if (manager == null)
			manager = factory.createEntityManager();
	}
}
