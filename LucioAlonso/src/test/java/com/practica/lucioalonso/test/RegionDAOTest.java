package com.practica.lucioalonso.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.lucioalonso.dao.RegionDAO;
import com.practica.lucioalonso.dao.exceptions.DAOException;
import com.practica.lucioalonso.entities.Region;


class RegionDAOTest {

	RegionDAO regionDAO;
	private static final Logger log = LoggerFactory.getLogger(RegionDAOTest.class);
	@Test
	@DisplayName("Encontrar Region 2")
	void testFindOne() throws DAOException 
	{
		log.info("testFindOne --------------------AAAA");
		assertEquals(2, regionDAO.findOne(2).getRegionId());
	}
	
	@Test
	@DisplayName("Crear una Region")
	void testCreate() throws DAOException 
	{
		Region region = new Region(10,"Atlantida");
		regionDAO.create(region);
		assertEquals(10, regionDAO.findOne(10).getRegionId());
		assertNotNull(regionDAO.findOne(10));
	}
	
	@Test
	@DisplayName("Borrar un Region")
	void testDelete() throws DAOException 
	{
		Region region = new Region(10,"Atlantida");
		regionDAO.create(region);
		regionDAO.delete(10);
		assertNull(regionDAO.findOne(10));

	}
	
	@Test
	@DisplayName("Update Region")
	void testUpdate() throws DAOException
	{
		Region region = new Region(10,"Atlantida");
		regionDAO.create(region);
		assertEquals("Atlantida", regionDAO.findOne(10).getRegionNombre());
		
		region.setRegionNombre("NINO");
		regionDAO.update(region);
		assertEquals("NINO", regionDAO.findOne(10).getRegionNombre());
	}
	
	@Test
	@DisplayName("Encontrar todas las Regiones")
	void testFindAll() throws DAOException 
	{
		List<Region> region= regionDAO.findAll();
		assertNotNull(region);
	
	}

}
