package com.front.pTipoAnt.bussines;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.front.pTipoAnt.bussines.interfaces.IServicio;
import com.front.pTipoAnt.common.exceptions.DAOException;
import com.front.pTipoAnt.common.exceptions.ServicioException;
import com.front.pTipoAnt.common.exceptions.TipoException;
import com.front.pTipoAnt.dao.DepartamentoDAO;
import com.front.pTipoAnt.dao.DirectionDAO;
import com.front.pTipoAnt.dao.interfaces.IDAO;
import com.front.pTipoAnt.data.Departamento;
import com.front.pTipoAnt.data.Direction;

public class ServDepartamento implements IServicio<Long,Departamento>{

	private static final Logger log = Logger.getLogger(ServDepartamento.class);

	IDAO<Long,Departamento> iDao ;

	IDAO<Long,Direction> iDaoDir ;
	
	
	public ServDepartamento() {
		super();
		this.iDao = new DepartamentoDAO();
		this.iDaoDir = new DirectionDAO();
	}
	
	@Override
	public List<Departamento> findAll() throws ServicioException{
		log.debug("findAll");

		try {
			List<Departamento> deptos = new ArrayList<Departamento>();
			deptos = this.iDao.findAll();
			for(Departamento depto : deptos) 
			{
				Direction dir = this.iDaoDir.findOne(depto.getDirection().getId());
				depto.setDirection(dir);
			}
			
			return deptos;
		} catch (DAOException daoe) {
			throw new ServicioException(daoe);
		}catch (Exception e) {
			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public Departamento findOne(Long key) throws ServicioException{
		log.debug("findOne");

		try {
			Departamento depto = this.iDao.findOne(key);
			Direction dir = this.iDaoDir.findOne(depto.getDirection().getId());
			depto.setDirection(dir);
			return depto;
		} catch (DAOException daoe) {
			throw new ServicioException(daoe);
		}catch (Exception e) {
			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}
	

	@Override
	public void create(Departamento item) throws ServicioException {
		// TODO Auto-generated method stub
		log.debug("Create");
		
		try {
			this.iDao.create(item);
		}catch(DAOException daoe ) {
			throw new ServicioException(daoe);
		}catch(Exception e) {
			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void update(Departamento item) throws ServicioException{
		log.debug("update");

		try {
			this.iDao.update(item);
		} catch (DAOException daoe) {
			throw new ServicioException(daoe);
		}catch (Exception e) {
			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}

	@Override
	public void delete(Long key) throws ServicioException{
		log.debug("delete");

		try {
			this.iDao.delete(key);
		} catch (DAOException daoe) {
			throw new ServicioException(daoe);
		}catch (Exception e) {
			throw new ServicioException(TipoException.EXCEPCION_GENERAL);
		}
	}
	

}
