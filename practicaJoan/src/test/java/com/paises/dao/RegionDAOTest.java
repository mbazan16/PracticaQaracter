package com.paises.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paises.exceptions.DAOException;

class RegionDAOTest {
	
	RegionDAO regionDAO;
	private static final Logger log = LoggerFactory.getLogger(RegionDAOTest.class);
	
	@BeforeEach
	public void configurarRegionDAO() {
		regionDAO = new RegionDAO();
	}
	
	@Test
	@DisplayName("Encontrar una región")
	void testFindOne() throws DAOException {
		log.info("-------------- testFindOne -----------------");
		assertEquals(1, regionDAO.findOne(1).getId());
		assertEquals("Europe", regionDAO.findOne(1).getNombre());
	}

}
