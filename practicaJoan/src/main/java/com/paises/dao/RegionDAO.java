package com.paises.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paises.entities.Region;
import com.paises.exceptions.DAOException;
import com.paises.interfaces.IDAO;

public class RegionDAO implements IDAO<Integer, Region> {
	
	EntityManagerFactory factory;
	EntityManager manager;
	private static final Logger log = LoggerFactory.getLogger(RegionDAO.class);

	@Override
	public List<Region> findAll() throws DAOException {
		init();
		List<Region> listaRegion = manager.createNamedQuery("Region.findAll", Region.class).getResultList();
		return listaRegion;
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
		log.debug("Parmetros:[Region element:" + item+"]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transacción");
//				manager.getTransaction().begin();
				manager.persist(item);
//				manager.getTransaction().commit();
				log.debug("[crear]Commit - Creamos departamento");
			} catch (Exception e) {
				log.error("Error", e);
//				manager.getTransaction().rollback();
				log.debug("[crear]Rollback");
				throw new DAOException(e);
			}

		} catch (DAOException daoe) {
			throw daoe;
		}catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Region item) throws DAOException {
		log.info("Method:[actualizar]");
		log.debug("Parmetros:[Region element:" + item+"]");

		try {
			init();

			try {
				log.debug("[actualizar]Iniciamos transacción");
//				manager.getTransaction().begin();
				Region region = findOne(item.getId());
				region.setNombre(item.getNombre());
//				manager.getTransaction().commit();
				log.debug("[actualizar]Commit - Creamos departamento");
			} catch (Exception e) {
				log.error("Error", e);
//				manager.getTransaction().rollback();
				log.debug("[actualizar]Rollback");
				throw new DAOException(e);
			}

		} catch (DAOException daoe) {
			throw daoe;
		}catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Integer key) throws DAOException {
		init();
		Region region = findOne(key);
		log.debug("[borrar]Iniciamos transacción");
		manager.remove(region);
		log.debug("[borrar]Borramos departamento");
	}

	private void init() {
		if(factory==null) factory = Persistence.createEntityManagerFactory("practicaJoan");
		if(manager== null) manager =  factory.createEntityManager();
	}

}
