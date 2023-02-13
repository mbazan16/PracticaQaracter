package com.evaluacion.practicaNicolas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.evaluacion.practicaNicolas.dao.exceptions.DAOException;
import com.evaluacion.practicaNicolas.dao.interfaces.IDAO;
import com.evaluacion.practicaNicolas.entities.Region;

public class RegionDAO implements IDAO<Integer, Region> {
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public List<Region> findAll() throws DAOException {
		init();
		List<Region> regions = manager.createNamedQuery("Region.findAll", Region.class).getResultList();
		return regions;
	}

	@Override
	public Region findOne(Integer key) throws DAOException {
		init();
		Region region = manager.find(Region.class, key);
		return region;
	}

	@Override
	public void create(Region item) throws DAOException {
		try {
			init();
			try {
				manager.persist(item);
			} catch (Exception e) {
				throw new DAOException(e);
			}
		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void update(Region item) throws DAOException {
		try {
			init();
			try {
				Region region = findOne(item.getId());
				region.setNombre(item.getNombre());
			} catch (Exception e) {
				throw new DAOException(e);
			}
		} catch (DAOException daoe) {
			throw daoe;
		} catch (Exception e) {
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
			factory = Persistence.createEntityManagerFactory("practicaNicolas");
		if (manager == null)
			manager = factory.createEntityManager();
	}
}
