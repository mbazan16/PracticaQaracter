package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Region;
import exceptions.DAOExceptions;
import exceptions.TipoExceptions;
import interfaces.IDAO;

public class RegionDAO implements IDAO<Long, Region>{

	DriverManager driverManager;

	private static final Logger log = LoggerFactory.getLogger(RegionDAO.class);
	EntityManagerFactory factory;
	EntityManager manager;

	public List<Region> findAll() throws DAOExceptions {
		init();
		List<Region> reg = manager.createNamedQuery("Region.findAll", Region.class).getResultList();
		return reg;
	}

	public Region findOne(Integer key) throws DAOExceptions {
		init();
		Region reg = manager.find(Region.class, key);
		return reg;
	}

	public void create(Region item) throws DAOExceptions {
		Connection con;
		PreparedStatement pstm;

		String sql = "INSERT INTO REGIONS (REGION_ID, REGION_NAME) VALUES(?,?)";

		try {
			con = driverManager.getConnection(sql);
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, item.getId());
			pstm.setString(2, item.getRegion_Name());

			int i = pstm.executeUpdate();

			if (i == 0) {
				log.info("Elemento creado:");
			} else {
				log.error(TipoExceptions.ELEMENTO_NO_CREADO.getMensaje());
				throw new DAOExceptions(TipoExceptions.ELEMENTO_NO_CREADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_SQL);

		} catch (DAOExceptions daoe) {
			log.error(daoe.getMessage(), daoe);
			throw new DAOExceptions(daoe.getTipoExcepcion());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_GENERAL);
		}

	}

	public void update(Region item) throws DAOExceptions {
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE REGIONS SET REGION_NAME=? WHERE REGION_ID=?";

		try {
			con = driverManager.getConnection(sql);
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getRegion_Name());
			pstm.setLong(2, item.getId());

			int i = pstm.executeUpdate();

			if (i == 1) {
				log.error(TipoExceptions.ELEMENTO_NO_ACTUALIZADO.getMensaje());
				throw new DAOExceptions(TipoExceptions.ELEMENTO_NO_ACTUALIZADO);
			} else if (i > 1) {
				log.error(TipoExceptions.ELEMENTO_DUPLICADO.getMensaje());
				throw new DAOExceptions(TipoExceptions.ELEMENTO_DUPLICADO);
			}

			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_SQL);

		} catch (DAOExceptions daoe) {
			throw daoe;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_GENERAL);
		}

	}

	public void delete(int j) throws DAOExceptions, SQLException {
		log.debug("delete");

		Connection con;
		PreparedStatement pstm;

		String sql = "DELETE FROM REGIONS WHERE DEPARTMENT_ID=?";

		con = driverManager.getConnection(sql);

		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, j);
			int i = pstm.executeUpdate();

			if (i == 0) {
				log.error(TipoExceptions.ELEMENTO_NO_ELIMINADO.getMensaje());
				throw new DAOExceptions(TipoExceptions.ELEMENTO_NO_ELIMINADO);
			} else if (i > 1) {
				log.error(TipoExceptions.ELEMENTO_DUPLICADO.getMensaje());
				con.rollback();
				throw new DAOExceptions(TipoExceptions.ELEMENTO_DUPLICADO);
			}
			pstm.close();
			con.close();

		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_SQL);

		} catch (DAOExceptions daoe) {
			throw daoe;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DAOExceptions(TipoExceptions.EXCEPCION_GENERAL);
		}

	}

	public void init() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("MavenPracticaHectorFernandez");
		if (manager == null)
			manager = factory.createEntityManager();
	}

	@Override
	public Region findOne(Long key) throws DAOExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long key) throws DAOExceptions, SQLException {
		// TODO Auto-generated method stub
		
	}

}
