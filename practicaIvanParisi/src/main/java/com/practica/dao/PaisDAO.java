package com.practica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.practica.dao.exceptions.DAOException;
import com.practica.dao.interfaces.IDAO;
import com.practica.entities.Pais;
import com.practica.entities.Pais;

public class PaisDAO implements IDAO<Integer, Pais>
{
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public List<Pais> findAll() throws DAOException {
		init();
		List<Pais> regiones = manager.createNamedQuery("Pais.findAll", Pais.class)
				.getResultList();
		return regiones;
	}

	@Override
	public Pais findOne(Integer key) throws DAOException {
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
				pais.setPaisNombre(item.getPaisNombre());
			
			} catch (Exception e) 
			{
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
		Pais pais = findOne(key);
		manager.remove(pais);
	}

	private void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("practicaIvanParisi");
		if (manager == null)
			manager = factory.createEntityManager();
	}

}
