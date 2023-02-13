package com.ejemplo.practicaruben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejemplo.practicaruben.dao.RegionDAO;
import com.ejemplo.practicaruben.dao.excepcion.DAOException;
import com.ejemplo.practicaruben.entidades.Region;

class RegionDAOTest {
	
	RegionDAO regDAO;
	Logger log = LoggerFactory.getLogger(RegionDAOTest.class);
	
	@BeforeEach
	public void init() {
		regDAO = new RegionDAO();
	}

	@Test
	@DisplayName("Encontrar todas las regiones")
	void testFindAll() throws DAOException {
		List<Region> regs = regDAO.findAll();
		assertNotNull(regs);
	}

	@Test
	@DisplayName("Encontrar la region 2 (Americas)")
	void testFindOne() throws DAOException {
		Region reg = regDAO.findOne(2);
		assertEquals("Americas", reg.getNombre());
	}

	@Test
	@DisplayName("Crear la region con id 10 y nombre RegionTest")
	void testCreate() throws DAOException {
		Region reg = new Region(10, "RegionTest");
		regDAO.create(reg);
		assertEquals("RegionTest", regDAO.findOne(10).getNombre());
	}

	@Test
	@DisplayName("Actualizar nombre de region con id 10")
	void testUpdate() throws DAOException {
		Region reg = new Region(10, "RegionTest");
		regDAO.create(reg);
		assertEquals("RegionTest", regDAO.findOne(10).getNombre());
		reg.setNombre("RegionTestUpdated");
		regDAO.update(reg);
		assertEquals("RegionTestUpdated", regDAO.findOne(10).getNombre());
	}

	@Test
	void testDelete() throws DAOException {
		Region reg = new Region(10, "RegionTest");
		regDAO.create(reg);
		assertEquals("RegionTest", regDAO.findOne(10).getNombre());
		regDAO.delete(10);
		assertNull(regDAO.findOne(10));
	}

}
