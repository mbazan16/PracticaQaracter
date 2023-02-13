package com.ejemplo.practicaruben;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejemplo.practicaruben.dao.PaisDAO;
import com.ejemplo.practicaruben.dao.excepcion.DAOException;
import com.ejemplo.practicaruben.entidades.Pais;
import com.ejemplo.practicaruben.entidades.Region;

class PaisDAOTest {
	
	PaisDAO paisDAO;
	Logger log = LoggerFactory.getLogger(PaisDAOTest.class);
	
	@BeforeEach
	public void init() {
		paisDAO = new PaisDAO();
	}

	@Test
	@DisplayName("Encontrar todas los paises")
	void testFindAll() throws DAOException {
		List<Pais> paises = paisDAO.findAll();
		assertNotNull(paises);
	}

	@Test
	@DisplayName("Encontrar el pais AU")
	void testFindOne() throws DAOException {
		Pais pais = paisDAO.findOne("AU");
		assertEquals("Australia", pais.getNombre());
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
