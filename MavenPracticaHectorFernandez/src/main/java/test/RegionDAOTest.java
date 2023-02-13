package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.RegionDAO;
import entities.Region;
import exceptions.DAOExceptions;

class RegionDAOTest {

	RegionDAO regDAO;

	private static final Logger log = LoggerFactory.getLogger(RegionDAOTest.class);

	@BeforeEach
	public void configurarDepDao() {
		regDAO = new RegionDAO();
	}

	@Test
//	@Disabled
	@DisplayName("Mostrar todas las regiones")
	void testFindAll() throws DAOExceptions {
		List<Region> dep = regDAO.findAll();
		assertNotNull(dep);
	}

	@Test
//	@Disabled
	@DisplayName("Encontrar una region")
	void testFindOne() throws DAOExceptions {
		log.info("--------------Region: TestFindOne--------------");
		assertEquals(1, regDAO.findOne(1).getRegion_Name());
		assertEquals("Europe", regDAO.findOne(1).getRegion_Name());
	}

	@Test
	@DisplayName("Comprobar creacion")
	void testCreate() throws DAOExceptions {
		Region reg = new Region(1, "Europe");
		regDAO.create(reg);
		assertNotNull(regDAO.findOne(1));
		assertEquals("Europe", regDAO.findOne(1).getRegion_Name().toString());
	}

	@Test
//	@Disabled
	@DisplayName("Actualizar Region")
	void testUpdate() throws DAOExceptions {
		// DepartamentoDAO depDAO = new DepartamentoDAO();
		Region reg = new Region(1, "europe");
		// depDAO.create(dep);
		assertEquals("Europe", regDAO.findOne(1).getRegion_Name());
		reg.setRegion_Name("Americas");
		regDAO.update(reg);
		assertEquals("Americas", regDAO.findOne(1).getRegion_Name());
	}

	@Test
//	@Disabled
	@DisplayName("Comprobar borrado")
	void testDelete() throws DAOExceptions, SQLException {
		RegionDAO regDAO = new RegionDAO();
		Region reg = new Region(1, "Europe");
		regDAO.create(reg);
		regDAO.delete(1);
		assertNull(regDAO.findOne(1000));
	}

}
