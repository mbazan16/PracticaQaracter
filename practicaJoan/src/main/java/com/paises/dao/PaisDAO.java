package com.paises.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paises.entities.Pais;
import com.paises.entities.Region;
import com.paises.exceptions.DAOException;
import com.paises.interfaces.IDAO;

public class PaisDAO implements IDAO<Integer, Pais> {
	
	EntityManagerFactory factory;
	EntityManager manager;
	private static final Logger log = LoggerFactory.getLogger(PaisDAO.class);
	
	@Override
	public List<Pais> findAll() throws DAOException {
		init();
		List<Pais> listaPais = manager.createNamedQuery("Pais.findAll", Pais.class).getResultList();
		return listaPais;
	}
	@Override
	public Pais findOne(Integer key) throws DAOException {
		init();
		Pais pais = manager.find(Pais.class, key);
		return pais;
	}
	@Override
	public void create(Pais item) throws DAOException {
		log.info("Method:[crear]");
		log.debug("Parmetros:[Pais element:" + item+"]");

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
	public void update(Pais item) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer key) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		if(factory==null) factory = Persistence.createEntityManagerFactory("practicaJoan");
		if(manager== null) manager =  factory.createEntityManager();
	}

}
