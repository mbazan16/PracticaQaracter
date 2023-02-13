package com.ejemplo.jpa1.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejemplo.jpa1.dao.exception.DAOException;
import com.ejemplo.jpa1.entitys.Departamento;

class DepartamentoDAOTest {
	DepartamentoDAO depDAO;
	Logger log = LoggerFactory.getLogger(DepartamentoDAOTest.class);

	@BeforeEach
	public void configurarDepDAO() {
		depDAO = new DepartamentoDAO();
	}

	@Test
	@Disabled
	void testInit() {
//		DepartamentoDAO depDAO = new DepartamentoDAO();
//		assertNotNull(depDAO.init());
	}

	@Test
	@DisplayName("Encontrar departamento 80")
	void testFindOne() throws DAOException {
		log.info("XXXXXXXXXXXXXXXXXXXXX testFindOne XXXXXXXXXXXXXXXXXXXXX");
//		DepartamentoDAO depDAO = new DepartamentoDAO();
		assertEquals(80, depDAO.findOne(80).getId(), "Error en findOne(key=80)");
//		assertEquals("Oxford", depDAO.findOne(80).getDireccion().getCiudad());
	}

	@Test
	@DisplayName("Crear departamento \"Nuevo departamento\"")
	void testCreate() throws DAOException {
//		DepartamentoDAO depDAO = new DepartamentoDAO();
		Departamento dep = new Departamento(1000, "Nuevo departamento", 1700, 100);
		depDAO.create(dep);
		assertNotNull(depDAO.findOne(dep.getId()), "Error en create(key=" + dep.getId() + ") No encontrado en la DB");
		assertEquals(dep.getId(), depDAO.findOne(dep.getId()).getId(),
				"Error en create(key=" + dep.getId() + ") No corresponden el ID");
	}

	@Test
	@DisplayName("Eliminar departamento")
	void testRemove() throws DAOException {
//		DepartamentoDAO depDAO = new DepartamentoDAO();
		Departamento dep = new Departamento(1001, "Nuevo departamento", 1700, 100);
		depDAO.create(dep);
		depDAO.delete(dep.getId());
		assertNull(depDAO.findOne(dep.getId()));
	}

	@Test
	@DisplayName("Actualizar departamento")
	void testUpdate() throws DAOException {
//		DepartamentoDAO depDAO = new DepartamentoDAO();
		// Create
		Departamento dep = new Departamento(1002, "Nuevo departamento", 1700, 100);
		depDAO.create(dep);

		// Modify
		dep.setNombre("Departamento Modificado");
		depDAO.update(dep);

		// Check
		Departamento depDB = depDAO.findOne(dep.getId());
		assertNotNull(depDB);
		assertEquals(dep.getId(), depDB.getId());
		assertEquals(dep.getNombre(), depDB.getNombre());
	}

	@Test
	@DisplayName("Buscar departamento por ciudad")
	void testFindCity() throws DAOException {
//		DepartamentoDAO depDAO = new DepartamentoDAO();
		// Create
		List<Departamento> ret = depDAO.findAllByCiudad("Oxford");

		assertTrue(!ret.isEmpty());
		assertEquals("Oxford", ret.get(0).getDireccion().getCiudad());
	}



	@Test
	@DisplayName("Buscar departamento por provincia")
	void testFindProvince() throws DAOException {
		List<Departamento> ret = depDAO.findAllByProvince("Texas");

		assertTrue(!ret.isEmpty());
		assertEquals("Oxford", ret.get(0).getDireccion().getCiudad());
	}
}
