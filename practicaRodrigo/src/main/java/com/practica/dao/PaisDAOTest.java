package com.practica.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.data.Pais;
import com.practica.exceptions.DAOException;

class PaisDAOTest {
	PaisDAO paisDAO;
	Logger log = LoggerFactory.getLogger(PaisDAOTest.class);

	@BeforeEach
	public void configurarDepDAO() {
		paisDAO = new PaisDAO();
	}
	@Test
	void testFindAll() throws DAOException {
		List<Pais> listPais = paisDAO.findAll();
		assertTrue(!listPais.isEmpty());
	}
	@Test
	void testFindAllFrom() throws DAOException {
		List<Pais> listPais = paisDAO.findAllFromRegion(1);
		assertTrue(!listPais.isEmpty());
	}

	@Test
	void testFindOne() throws DAOException {
		Pais pais = paisDAO.findOne("AR");
		assertNotNull(pais);
		assertEquals("AR", pais.getId());
	}

	@Test
	void testCreate() throws DAOException {
		Pais pais = new Pais("ZZ", "Mi Pais", 1);

		paisDAO.create(pais);
		assertNotNull(paisDAO.findOne(pais.getId()));
		assertEquals(pais.getId(), paisDAO.findOne(pais.getId()).getId());
		assertEquals(pais.getNombre(), paisDAO.findOne(pais.getId()).getNombre());
	}

	@Test
	void testUpdate() throws DAOException {
		Pais pais = paisDAO.findOne("AR");
		pais.setNombre(pais.getNombre() + " test!");
		
		paisDAO.update(pais);
		assertEquals(pais.getId(), paisDAO.findOne(pais.getId()).getId());
		assertEquals(pais.getNombre(), paisDAO.findOne(pais.getId()).getNombre());
	}

	@Test
	void testDelete() throws DAOException {
		Pais pais = paisDAO.findOne("AR");
		
		paisDAO.delete(pais.getId());
		assertNull(paisDAO.findOne(pais.getId()));
	}

}
