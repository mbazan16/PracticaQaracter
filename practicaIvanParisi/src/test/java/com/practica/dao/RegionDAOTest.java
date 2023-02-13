package com.practica.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica.dao.exceptions.DAOException;
import com.practica.entities.Region;

class RegionDAOTest {

RegionDAO regDAO;

	
	@BeforeEach
	public void configurarDepDAO() 
	{
		regDAO = new RegionDAO();
	}
	
	@Test
	@DisplayName("Encontrar la region 1")
	void testFindOne() throws DAOException 
	{
		assertEquals(1, regDAO.findOne(1).getId());
	}
	
	
	@Test
	@DisplayName("Crear una region")
	void testCreate() throws DAOException 
	{
		Region region = new Region(5,"Region X");
		regDAO.create(region);
		assertEquals(5, regDAO.findOne(5).getId());
		assertNotNull(regDAO.findOne(5));
	}
	
	@Test
	@DisplayName("Borrar una region")
	void testDelete() throws DAOException 
	{
		Region region = new Region(5,"Region X");
		regDAO.create(region);
		regDAO.delete(5);
		assertNull(regDAO.findOne(5));

	}
	
	@Test
	@DisplayName("Update region")
	void testUpdate() throws DAOException
	{
		Region region = new Region(5,"Region X");
		regDAO.create(region);
		//Region region = regDAO.findOne(1000);
		assertEquals("Depto X", regDAO.findOne(5).getNombreRegion());
		
		region.setNombreRegion("Depto X");
		regDAO.update(region);
		assertEquals("Depto B", regDAO.findOne(5).getNombreRegion());
	}
	
	@Test
	@DisplayName("Encontrar todas las regiones")
	void testFindAll() throws DAOException 
	{
		List<Region> regiones= regDAO.findAll();
		assertNotNull(regiones);
	
	}

}
