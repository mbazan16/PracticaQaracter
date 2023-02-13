package com.front.pTipoAnt.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.front.pTipoAnt.common.exceptions.DAOException;

class DepartamentoDAOTest {

   DepartamentoDAO dao;
	
	@Test
	void testFindOne() throws DAOException {
		dao= new DepartamentoDAO();	
		Long idEsperado=80l;
		 assertEquals(idEsperado, dao.findOne(80l).getId());
	}
	
	@Test
	void testFindAll() throws DAOException {
		dao= new DepartamentoDAO();	
		Long idEsperado=80l;
		 assertEquals(idEsperado, dao.findOne(80l).getId());
	}

}
