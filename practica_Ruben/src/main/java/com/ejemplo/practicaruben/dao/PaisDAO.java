package com.ejemplo.practicaruben.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejemplo.practicaruben.dao.excepcion.DAOException;
import com.ejemplo.practicaruben.dao.interfaz.IDAO;
import com.ejemplo.practicaruben.entidades.Pais;

public class PaisDAO implements IDAO<String, Pais>{

	EntityManagerFactory factory;
	EntityManager manager;
	Logger log = LoggerFactory.getLogger(PaisDAO.class);
	
	@Override
	public List<Pais> findAll() throws DAOException {
		
		init();
		List<Pais> paises = manager.createNamedQuery("Region.findAll",Pais.class).
				getResultList();
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
		
		log.info("Method:[crear]");
		log.debug("Parmetros:[Pais element:" + item+"]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
				manager.persist(item);
				log.debug("[crear]Commit - Creamos pais");
			} catch (Exception e) {
				log.error("Error", e);
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

		log.info("Method:[Actualizar]");
		log.debug("Parmetros:[Pais element:" + item+"]");

		try {
			init();

			try {
				log.debug("[Actualizar]Iniciamos transaccion");
				Pais pais = findOne(item.getIdPais());
				pais.setNombre(item.getNombre());
				pais.setRegion(item.getRegion());
				log.debug("[Actualizar]Commit - Actualizamos pais");
			} catch (Exception e) {
				log.error("Error", e);
				throw new DAOException(e);
			}
//
		} catch (DAOException daoe) {
			throw daoe;
		}catch (Exception e) {
			log.error("Error", e);
			throw new DAOException(e);
		}
	}
	
	@Override
	public void delete(String key) throws DAOException {
		
		init();
		manager.remove(findOne(key));
		
	}
	
	private void init() {
		if(factory==null) factory = Persistence.createEntityManagerFactory("practica_Ruben");
		if(manager== null) manager =  factory.createEntityManager();
	}
}
