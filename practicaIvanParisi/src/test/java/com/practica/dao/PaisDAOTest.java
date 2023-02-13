package com.practica.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica.dao.exceptions.DAOException;
import com.practica.entities.Pais;
import com.practica.entities.Region;

class PaisDAOTest {

PaisDAO paisDAO;

	
	@BeforeEach
	public void configurarDepDAO() 
	{
		paisDAO = new PaisDAO();
	}
	
	@Test
	@DisplayName("Encontrar la pais 25")
	void testFindOne() throws DAOException 
	{
		assertEquals(25, paisDAO.findOne(25).getId());
	}
	
	
	@Test
	@DisplayName("Crear una pais")
	void testCreate() throws DAOException 
	{
		Pais pais = new Pais(26,"Argentina",new Region(7,"Argentina REGION"));
		paisDAO.create(pais);
		assertEquals(26, paisDAO.findOne(26).getId());
		assertNotNull(paisDAO.findOne(26));
	}
	
	@Test
	@DisplayName("Borrar una pais")
	void testDelete() throws DAOException 
	{
		Pais pais = new Pais(26,"Argentina",new Region(7,"Argentina REGION"));
		paisDAO.create(pais);
		paisDAO.delete(26);
		assertNull(paisDAO.findOne(26));

	}
	
	@Test
	@DisplayName("Update pais")
	void testUpdate() throws DAOException
	{
		Pais pais = new Pais(26,"Argentina",new Region(7,"Argentina REGION"));
		paisDAO.create(pais);
		//Pais pais = paisDAO.findOne(1000);
		assertEquals("Argentina", paisDAO.findOne(26).getPaisNombre());
		
		pais.setPaisNombre("Argentina b");
		paisDAO.update(pais);
		assertEquals("Argentina b", paisDAO.findOne(26).getPaisNombre());
	}
	
	@Test
	@DisplayName("Encontrar todas los paises")
	void testFindAll() throws DAOException 
	{
		List<Pais> regiones= paisDAO.findAll();
		assertNotNull(regiones);
	
	}

}
