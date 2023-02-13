package DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Pais;
import entities.Region;
import exceptions.DAOExceptions;
import interfaces.IDAO;

public class PaisDAO implements IDAO<Long, Pais> {

	DriverManager driverManager;

	private static final Logger log = LoggerFactory.getLogger(PaisDAO.class);
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public List<Pais> findAll() throws DAOExceptions {
		init();
		List<Pais> pais = manager.createNamedQuery("Pais.findAll", Pais.class).getResultList();
		return pais;
	}

	@Override
	public Pais findOne(Long key) throws DAOExceptions {
		init();
		Pais pais = manager.find(Pais.class, key);
		return pais;
	}

	@Override
	public void delete(Long key) throws DAOExceptions, SQLException {
		// TODO Auto-generated method stub

	}

	public void create(Pais item) throws DAOExceptions {
		// TODO Auto-generated method stub

	}

	public void update(Pais item) throws DAOExceptions {
		// TODO Auto-generated method stub

	}

	public void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("MavenPracticaHectorFernandez");
		if (manager == null)
			manager = factory.createEntityManager();
	}

}
