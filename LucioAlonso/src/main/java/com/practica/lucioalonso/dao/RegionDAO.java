package com.practica.lucioalonso.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.lucioalonso.dao.exceptions.DAOException;
import com.practica.lucioalonso.dao.interfaces.IDAO;
import com.practica.lucioalonso.entities.Region;

public class RegionDAO implements IDAO<Integer, Region> {
	private static final Logger log = LoggerFactory.getLogger(RegionDAO.class);
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public List<Region> findAll() throws DAOException {
		init();
		List<Region> region = manager.createNamedQuery("Region.findAll", Region.class)
				.getResultList();
		return region;
	}
	
	
	@Override
	public Region findOne(Integer key) throws DAOException {
		init();
		Region region = manager.find(Region.class, key);
		return region;
	}

	@Override
	public void create(Region item) throws DAOException {
		log.info("Method:[crear]");
		log.debug("Parmetros:[Region item:" + item + "]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
				manager.persist(item);
				log.debug("[crear]Commit - Creamos region");
			} catch (Exception e) {
				log.error("Error", e);
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
	public void update(Region item) throws DAOException {
		log.info("Method:[update]");
		log.debug("Parmetros:[Region item:" + item + "]");

		try {
			init();

			try {
				log.debug("[update]Iniciamos transaccion");
				Region region = findOne(item.getRegionId());
				region.setRegionNombre(item.getRegionNombre());
				log.debug("[crear]Commit - Creamos region");
			} catch (Exception e) {
				log.error("Error", e);
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
	public void delete(Integer key) throws DAOException {
		init();
		Region region = findOne(key);
		manager.remove(region);
	}

	private void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("jpa1");
		if (manager == null)
			manager = factory.createEntityManager();
	}
}

