package com.front.pTipoAnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Direction;

public class DirectionDAO implements IDAO<Long, Direction>
{
	DriverManagerOracle driverManager;
	
	private static final Logger log = Logger.getLogger(DirectionDAO.class);
	
	public DirectionDAO() 
	{

		this.driverManager = DriverManagerOracle.getInstancia();
	}
	
	@Override
	public List<Direction> findAll() throws DAOException 
	{
		log.debug("findAll");

		Connection con;
		Statement stm;
		ResultSet rs;
		
		List<Direction> direcciones= new ArrayList<Direction>();

		String sql="SELECT location_id,street_address,postal_code"
				+ ",city,state_province,country_id "
				+ "FROM locations ORDER BY location_id";
		try 
		{
			con = driverManager.getConexion();
			stm=con.createStatement();
			rs=stm.executeQuery(sql);

			while(rs.next()) 
			{
				Direction direccion = new Direction();
				direccion.setId(rs.getLong("location_id"));
				direccion.setStreet(rs.getString("street_address"));
				direccion.setPostal(rs.getString("postal_code"));
				direccion.setTown(rs.getString("city"));
				direccion.setState(rs.getString("state_province"));
				direccion.setIdCountry(rs.getString("country_id"));
				direcciones.add(direccion);
			}

			return direcciones;

		}catch(SQLException sqle) 
		{
			log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		}catch(Exception e) 
		{
			log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public Direction findOne(Long id) throws DAOException 
	{
		log.info("id:"+id);
		log.debug("findOne");

		Connection con;
		PreparedStatement pstm;
		ResultSet rs;
		
		Direction direccion=null;

		String sql="SELECT location_id,street_address,postal_code"
				+ ",city,state_province,country_id "
				+ "FROM locations WHERE location_id=?";
		try 
		{
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			rs= pstm.executeQuery();

			if(rs.next()) 
			{
				direccion = new Direction();
				direccion.setId(rs.getLong("location_id"));
				direccion.setStreet(rs.getString("street_address"));
				direccion.setPostal(rs.getString("postal_code"));
				direccion.setTown(rs.getString("city"));
				direccion.setState(rs.getString("state_province"));
				direccion.setIdCountry(rs.getString("country_id"));
			}else {
				log.error(TipoException.ELEMENTO_NO_ENCONTRADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ENCONTRADO);
			}
			if(rs.next()) {
				log.fatal(TipoException.ELEMENTO_DUPLICADO.getMensaje());			
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			rs.close();
			pstm.close();
			con.close();
			
			return direccion;

		}catch(SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		}catch(DAOException daoe) {
			throw daoe;

		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void create(Direction item) throws DAOException 
	{
		log.debug("Create");

		Connection con;
		PreparedStatement pstm;

		String sql="INSERT INTO locations "
				+ "(location_id,street_address,postal_code,city,state_province,country_id"
				+ "VALUES(?,?,?,?,?,?)";
		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, item.getId());
			pstm.setString(2, item.getStreet());
			pstm.setString(3, item.getPostal());
			pstm.setString(4, item.getTown());
			pstm.setString(5, item.getState());
			pstm.setString(6, item.getIdCountry());
			
			int i = pstm.executeUpdate();

			if(i==0) {
				log.info("Elemento creado:");
			}else if (i < 1)
			{
				log.error(TipoException.ELEMENTO_NO_CREADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_CREADO);
			}else 
			{
				log.error(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}

			pstm.close();
			con.close();


		}catch(SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		}catch(DAOException daoe) {
			log.error(daoe.getMessage(),daoe);
			throw new DAOException(daoe.getTipoExcepcion());
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void update(Direction item) throws DAOException 
	{
		log.debug("update");

		Connection con;
		PreparedStatement pstm;

		String sql = "UPDATE locations "
				+ "SET street_address=?, postal_code=?, city=?, state_province=?"
				+ ",country_id=?"
				+ "WHERE location_id=?";



		try {
			con = driverManager.getConexion();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getStreet());
			pstm.setString(2, item.getPostal());
			pstm.setString(3, item.getTown());
			pstm.setString(4, item.getState());
			pstm.setString(5, item.getIdCountry());
			pstm.setLong(6, item.getId());
			

			int i = pstm.executeUpdate();

			if(i==0) {
				log.error(TipoException.ELEMENTO_NO_ACTUALIZADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ACTUALIZADO);
			}else if(i>1) {
				log.error(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}

			pstm.close();
			con.close();

		}catch(SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		}catch(DAOException daoe) {
			throw daoe;

		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		
	}

	@Override
	public void delete(Long id) throws DAOException 
	{
		log.debug("delete");

		Connection con;
		PreparedStatement pstm;

		String sql="DELETE FROM locations WHERE location_id=?";

		con = driverManager.getConexion();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			int i =pstm.executeUpdate();

			if(i==0) {
				log.error(TipoException.ELEMENTO_NO_ELIMINADO.getMensaje());
				throw new DAOException(TipoException.ELEMENTO_NO_ELIMINADO);
			}else if(i>1) {
				log.error(TipoException.ELEMENTO_DUPLICADO.getMensaje());
				con.rollback();
				throw new DAOException(TipoException.ELEMENTO_DUPLICADO);
			}
			pstm.close();
			con.close();

		}catch(SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
			throw new DAOException(TipoException.EXCEPCION_SQL);

		}catch(DAOException daoe) {
			throw daoe;

		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new DAOException(TipoException.EXCEPCION_GENERAL);
		}
		
	}
	

}
