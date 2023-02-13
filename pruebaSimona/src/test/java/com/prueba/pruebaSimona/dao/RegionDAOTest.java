package com.prueba.pruebaSimona.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.pruebaSimona.dao.RegionDAO;
import com.prueba.pruebaSimona.entities.Region;



class RegionDAOTest {

	private static final Logger log = LoggerFactory.getLogger(RegionDAOTest.class);
	RegionDAO region;
	
	@BeforeEach
	public void configurarDepDAO() {
		region = new RegionDAO();
	}
	
	@Test
	void testFindAll() {
		List<Region> listaRegiones = region.findAll();
		assertNotNull(listaRegiones);
	}

	@Test
	void testFindOne() {
		RegionDAO reg = new RegionDAO();
		assertEquals(2, region.findOne(2));
	}

	@Test
	void testCreate() {
		Region reg = new Region(5, "Africa");
		region.create(reg);
		assertEquals(5, region.findOne(5));
	}

	@Test
	void testUpdate() {
		Region reg = region.findOne(5);
		reg.setNombre("Australia");
		region.update(reg);
		assertEquals("Australia", region.findOne(5).getNombre());
	}

	@Test
	void testDelete() {
		region.delete(1000);
		assertNull(region.findOne(5));
	}

}
