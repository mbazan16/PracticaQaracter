package com.prueba.pruebaSimona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.pruebaSimona.dao.interfaces.IDAO;
import com.prueba.pruebaSimona.entities.Region;

public class RegionDAO implements IDAO<Integer, Region>{

private static final Logger log = LoggerFactory.getLogger(RegionDAO.class);
	
	EntityManagerFactory factory;
	EntityManager manager;
	
	@Override
	public List<Region> findAll() {
		init();
		
		List<Region> listaRegiones = manager
				.createNamedQuery("Region.findAll", Region.class)
				.getResultList();
		
		return listaRegiones;
	}
	@Override
	public Region findOne(Integer key) {
		init();
		
		Region region = manager.find(Region.class, key);
		
		return region;
	}
	@Override
	public void create(Region item) {
		init();
		
		manager.getTransaction().begin();
		manager.persist(item);
		manager.getTransaction().commit();
		
	}
	@Override
	public void update(Region item) {
		init();
		
		manager.getTransaction().begin();
		Region region = manager.find(Region.class, item.getId());
		region.setNombre(item.getNombre());
		manager.getTransaction().commit();
	}
	@Override
	public void delete(Integer key) {
		init();
		
		Region region = this.findOne(key);
		
		manager.getTransaction().begin();
		manager.remove(region);
		manager.getTransaction().commit();
	}
	

	private void init() {
		if(factory==null) factory = Persistence.createEntityManagerFactory("prueba");
		if(manager== null) manager =  factory.createEntityManager();
	}



}
