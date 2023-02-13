package com.ejemplo.practicaruben.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejemplo.practicaruben.dao.excepcion.DAOException;
import com.ejemplo.practicaruben.dao.interfaz.IDAO;
import com.ejemplo.practicaruben.entidades.Region;

public class RegionDAO implements IDAO<Integer,Region>{

	EntityManagerFactory factory;
	EntityManager manager;
	Logger log = LoggerFactory.getLogger(RegionDAO.class);
	
	@Override
	public List<Region> findAll() throws DAOException {
		
		init();
		List<Region> regiones = manager.createNamedQuery("Region.findAll",Region.class).
				getResultList();
		return regiones;
	}
	
	@Override
	public Region findOne(Integer key) throws DAOException {
		
		init();
		Region reg = manager.find(Region.class, key);
		return reg;
	}
	
	@Override
	public void create(Region item) throws DAOException {
		
		log.info("Method:[crear]");
		log.debug("Parmetros:[Region element:" + item+"]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
				manager.persist(item);
				log.debug("[crear]Commit - Creamos region");
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
	public void update(Region item) throws DAOException {

		log.info("Method:[Actualizar]");
		log.debug("Parmetros:[Region element:" + item+"]");

		try {
			init();

			try {
				log.debug("[crear]Iniciamos transaccion");
				Region reg = findOne(item.getIdRegion());
				reg.setNombre(item.getNombre());
				log.debug("[crear]Commit - Actualizamos region");
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
	public void delete(Integer key) throws DAOException {
		
		init();
		manager.remove(findOne(key));
		
	}
	
	private void init() {
		if(factory==null) factory = Persistence.createEntityManagerFactory("practica_Ruben");
		if(manager== null) manager =  factory.createEntityManager();
	}
}
