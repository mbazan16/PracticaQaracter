package com.pratica.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pratica.jpa.dao.RegiaoDAO;
import com.pratica.jpa.dao.entities.Regiao;
import com.pratica.jpa.dao.exceptions.DAOException;

class RegiaoDAOTest {

	RegiaoDAO regiaoDAO;
	Logger log = LoggerFactory.getLogger(RegiaoDAOTest.class);

	@BeforeEach
	void init() {
		regiaoDAO = new RegiaoDAO();
	}

	@Test
	void testInit() {
		assertNotNull(regiaoDAO);
	}

	@Test
	void testFindOne() throws DAOException {
		log.info("testFindOne -------" + regiaoDAO.findOne(1));
		assertEquals(1, regiaoDAO.findOne(1).getId());
	}

	@Test
	void testCreate() throws DAOException {
		log.info("testCreate ---- ");
		regiaoDAO.create(mockRegiao());
		assertNotNull(regiaoDAO.findOne(321));
	}

	@Test
	void testUpdate() throws DAOException {
		log.info("testUpdate ---- ");
		regiaoDAO.create(mockRegiao());
		assertEquals("Latin America",regiaoDAO.findOne(321).getNome());
		Regiao regiaoUpdate = mockRegiao();
		regiaoUpdate.setNome("Brazil");
		regiaoDAO.update(regiaoUpdate);
		assertEquals("Brazil",regiaoDAO.findOne(321).getNome());
	}

	@Test
	void testDelete() throws DAOException {
		log.info("testDelete ---- ");
		regiaoDAO.create(mockRegiao());
		regiaoDAO.delete(mockRegiao().getId());
		assertNull(regiaoDAO.findOne(321));
	}

	private Regiao mockRegiao() {
		Regiao regiao = new Regiao();
		regiao.setId(321);
		regiao.setNome("Latin America");

		return regiao;
	}

}
