package com.paises.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paises.entities.Region;
import com.paises.exceptions.DAOException;

class RegionDAOTest {
	
	RegionDAO regionDAO;
	private static final Logger log = LoggerFactory.getLogger(RegionDAOTest.class);
	
	@BeforeEach
	public void configurarRegionDAO() {
		regionDAO = new RegionDAO();
	}
	
	@Test
	@DisplayName("Encontrar una region")
	void testFindOne() throws DAOException {
		log.info("-------------- testFindOne -----------------");
		assertEquals(1, regionDAO.findOne(1).getId());
		assertEquals("Europe", regionDAO.findOne(1).getNombre());
	}
	
	@Test
	@DisplayName("Crear region nueva")
	void testCreate() throws DAOException {
		Region region = new Region(5, "Prueba region");
		regionDAO.create(region);
		assertEquals(5, regionDAO.findOne(5).getId());
	}
	
	@Test
	@DisplayName("Actualizar una region")
	void testUpdate() throws DAOException {
		Region region = regionDAO.findOne(1);
		assertEquals("Europe", region.getNombre());
		
		region.setNombre("Europa");
		regionDAO.update(region);
		assertEquals("Europa", regionDAO.findOne(1).getNombre());
	}
	
	@Test
	@DisplayName("Encontrar todas las regiones")
	void testFindAll() throws DAOException {
		List<Region> listaRegion = regionDAO.findAll();
		assertNotNull(listaRegion);
	}
	

}
