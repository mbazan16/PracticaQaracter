package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dao.excepciones.DAOException;
import com.dao.interfaces.IDAO;
import com.entities.Region;

public class RegionDao implements IDAO<Integer, Region> {

	EntityManagerFactory factory;
	EntityManager manager;
	
	@Override
	public List<Region> findAll() throws DAOException {
		init();
		List<Region> regiones = manager.createNamedQuery("Regiones.findAll",Region.class).getResultList();
		return regiones;
	}

	@Override
	public Region findOne(Integer key) throws DAOException {
		init();
		Region region = manager.find(Region.class,key);
		return region;
	}

	@Override
	public void create(Region item) throws DAOException {
		init();
		manager.persist(item);
	}

	@Override
	public void update(Region item) throws DAOException {
		init();
		Region region = manager.find(Region.class, item.getRegionId());
		region.setRegionId(item.getRegionId());
		region.setRegionName(item.getRegionName());
	}

	@Override
	public void delete(Integer key) throws DAOException {
		init();
		Region region = manager.find(Region.class, key);
		manager.remove(region);
	}
	private void init() 
	{
		if(factory==null) factory = Persistence.createEntityManagerFactory("EntityFV");
		if(manager== null) manager =  factory.createEntityManager();
	}
}
