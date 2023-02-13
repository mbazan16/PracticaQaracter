package interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.DAOExceptions;

public interface IDAO<K, T> {

	List<T> findAll() throws DAOExceptions;

	T findOne(K key) throws DAOExceptions;

	void create(T item) throws DAOExceptions;

	void update(T item) throws DAOExceptions;

	void delete(K key) throws DAOExceptions, SQLException;

}
