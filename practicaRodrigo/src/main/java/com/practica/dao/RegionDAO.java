package com.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.dao.interfaces.IDAO;
import com.practica.data.Region;
import com.practica.exceptions.DAOException;

public class RegionDAO implements IDAO<Integer, Region> {
	private static final Logger log = LoggerFactory.getLogger(RegionDAO.class);
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Override
	public List<Region> findAll() throws DAOException {
		init();
		return manager.createNamedQuery("Region.findAll", Region.class).getResultList();
	}


	@Override
	public Region findOne(Integer key) throws DAOException {
		init();
		return manager.find(Region.class, key);
	}

	@Override
	public void create(Region element) throws DAOException {
		log.info("Method:[crear]");
		log.debug("Parmetros:[Region element:" + element + "]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
//				manager.getTransaction().begin();
				manager.persist(element);
//				manager.getTransaction().commit();
				log.debug("[crear]Commit - Creamos departamento");
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
	public void update(Region element) throws DAOException {
		log.info("Method:[update]");
		log.debug("Parmetros:[Region element:" + element + "]");

		try {
			init();

			try {
				log.debug("[update]Iniciamos transaccion");
//				manager.getTransaction().begin();
//				manager.persist(element);

				Region dep = findOne(element.getId());
				dep.setNombre(element.getNombre());

//				manager.getTransaction().commit();
				log.debug("[update]Commit - Actualizamos departamento");
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
	public void delete(Integer key) throws DAOException {
		log.info("Method:[delete]");
		log.debug("Parmetros:[Region element:" + key + "]");

		try {
			init();

			try {
				log.debug("[delete]Iniciamos transaccion");
//				manager.getTransaction().begin();
				manager.remove(findOne(key));

//				manager.getTransaction().commit();
				log.debug("[delete]Commit - Borrar departamento");
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
