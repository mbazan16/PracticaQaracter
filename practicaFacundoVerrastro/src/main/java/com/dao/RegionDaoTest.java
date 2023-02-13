package com.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import com.dao.excepciones.DAOException;

public class RegionDaoTest{
	
	RegionDao regionDao;
	
	@Test
	@Disabled
	void regionInit() {
		regionDao = new RegionDao();
	}
	
	@Test
	@DisplayName("Encontrada la region 1")
	void testFindOne() throws DAOException
	{
		regionInit();
		assertEquals(1,regionDao.findOne(1).getRegionId());
	}
}