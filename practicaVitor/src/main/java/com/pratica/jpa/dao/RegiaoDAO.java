package com.pratica.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.pratica.jpa.dao.entities.Regiao;
import com.pratica.jpa.dao.exceptions.DAOException;
import com.pratica.jpa.dao.interfaces.IDAO;

public class RegiaoDAO implements IDAO<Integer, Regiao> {

	EntityManagerFactory emf;
	EntityManager manager;

	private static final Logger log = Logger.getLogger(RegiaoDAO.class);

	private void init() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("jpaVitor");
		log.debug("[emf]:" + emf);
		if (manager == null)
			manager = emf.createEntityManager();
		log.debug("[manager]:" + manager);
	}

	@Override
	public List<Regiao> findAll() throws DAOException {
		try {
			init();
			List<Regiao> regions = manager.createNamedQuery("Regiao.findAll", Regiao.class).getResultList();
			return regions;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Regiao findOne(Integer key) throws DAOException {
		try {
			init();
			Regiao region = manager.find(Regiao.class, key);
			return region;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void create(Regiao item) throws DAOException {
		try {
			init();
			manager.persist(item);
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void update(Regiao item) throws DAOException {
		try {
			init();
			manager.merge(item);
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void delete(Integer key) throws DAOException {
		try {
			init();
			Regiao regiao = findOne(key);
			manager.remove(regiao);
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

}