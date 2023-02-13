package com.evaluacion.practicaNicolas.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.evaluacion.practicaNicolas.dao.exceptions.DAOException;
import com.evaluacion.practicaNicolas.entities.Region;

class RegionDAOTest {

	RegionDAO regionDAO;

	@BeforeEach
	public void configurarRegionDAO() {
		regionDAO = new RegionDAO();
	}

	@Test
	@DisplayName("Encontrar todas las regiones")
	void testFindAll() throws DAOException {
		List<Region> regiones = regionDAO.findAll();
		assertNotNull(regiones);
	}

	@Test
	@DisplayName("Encontrar la región 3")
	void testFindOne() throws DAOException {
		assertEquals(3, regionDAO.findOne(3).getId());
	}

	@Test
	@DisplayName("Crear una región")
	void testCreate() throws DAOException {
		Region region = new Region(10, "Region de prueba");
		regionDAO.create(region);
		assertEquals(10, regionDAO.findOne(10).getId());
		assertNotNull(regionDAO.findOne(10));
	}

	@Test
	@DisplayName("Update departamento")
	void testUpdate() throws DAOException {
		Region region = new Region(10, "Region de prueba");
		regionDAO.create(region);
		assertEquals("Region de prueba", regionDAO.findOne(10).getNombre());

		region.setNombre("Nombre cambiado");
		regionDAO.update(region);
		assertEquals("Nombre cambiado", regionDAO.findOne(10).getNombre());
	}

	@Test
	@DisplayName("Borrar un departamento")
	void testDelete() throws DAOException {
		Region region = new Region(10, "Region de prueba");
		regionDAO.create(region);
		regionDAO.delete(10);
		assertNull(regionDAO.findOne(10));

	}
}
