package com.practica.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.data.Region;
import com.practica.exceptions.DAOException;


class RegionDAOTest {
	RegionDAO regDAO;
	Logger log = LoggerFactory.getLogger(RegionDAOTest.class);

	@BeforeEach
	public void configurarDepDAO() {
		regDAO = new RegionDAO();
	}
	
	@Test
	@DisplayName("Find all")
	void testFindAll() throws DAOException {
		List<Region> listReg = regDAO.findAll();
		assertTrue(!listReg.isEmpty());
	}

	@Test
	@DisplayName("Find id=1")
	void testFindOne() throws DAOException {
		assertEquals(1, regDAO.findOne(1).getId());
	}

	@Test
	@DisplayName("Create id=1000")
	void testCreate() throws DAOException {
		Region reg = new Region();
		reg.setId(1000);
		reg.setNombre("Mi Region");
		
		regDAO.create(reg);
		assertEquals(reg.getId(), regDAO.findOne(reg.getId()).getId());
		assertEquals(reg.getNombre(), regDAO.findOne(reg.getId()).getNombre());
		
	}

	@Test
	@DisplayName("Update 1")
	void testUpdate() throws DAOException {
		Region reg = regDAO.findOne(1);
		reg.setNombre(reg.getNombre() + " modified!");
		
		regDAO.update(reg);
		assertEquals(reg.getNombre(), regDAO.findOne(reg.getId()).getNombre());
	}

	@Test
	@DisplayName("Delete 1")
	void testDelete() throws DAOException {
		regDAO.delete(1);
		assertNull(regDAO.findOne(1));
	}

}
