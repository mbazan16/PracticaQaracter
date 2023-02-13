package com.evaluacion.practicaNicolas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.evaluacion.practicaNicolas.dao.exceptions.DAOException;
import com.evaluacion.practicaNicolas.dao.interfaces.IDAO;
import com.evaluacion.practicaNicolas.entities.Pais;
import com.evaluacion.practicaNicolas.entities.Region;

public class PaisDAO implements IDAO<String, Pais> {
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public List<Pais> findAll() throws DAOException {
		init();
		List<Pais> paises = manager.createNamedQuery("Pais.findAll", Pais.class).getResultList();
		return paises;
	}
	
	public List<Pais> findAllByRegion(Region region) throws DAOException {
		init();
		String query = "SELECT p FROM Pais p WHERE p.region = ':region'";
		List<Pais> paises = manager.createQuery(query, Pais.class)
				.setParameter("region", region).getResultList();
		paises.stream().map(Pais::toString);
		return paises;
	}

	@Override
	public Pais findOne(String key) throws DAOException {
		init();
		Pais pais = manager.find(Pais.class, key);
		return pais;
	}

	@Override
	public void create(Pais item) throws DAOException {
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
	public void update(Pais item) throws DAOException {
		try {
			init();
			try {
				Pais pais = findOne(item.getId());
				pais.setNombre(item.getNombre());
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
	public void delete(String key) throws DAOException {
		init();
		Pais pais = findOne(key);
		manager.remove(pais);
	}

	private void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("practicaNicolas");
		if (manager == null)
			manager = factory.createEntityManager();
	}
}
