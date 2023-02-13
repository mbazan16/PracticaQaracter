package com.prueba.pruebaSimona.dao.interfaces;

import java.util.List;


public interface IDAO<K,T> {
	
	List<T> findAll();

	T findOne(K key);

	void create(T item);

	void update(T item);

	void delete(K key);
}
