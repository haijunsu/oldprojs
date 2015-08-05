/*
 *
 */
package org.cuny.sensors.dao;

import java.util.List;

import javax.sql.DataSource;

/**
 *
 * @author Haijun Su
 * Created date: Jan 4, 2014
 */
public interface IDao<T> {
	public static final String ID = "id";

	public void setDataSource(DataSource dataSource);

	public T save(T obj) throws Exception;

	public T update(T obj) throws Exception;

	public T remove(T obj) throws Exception;

	public void remove(long id) throws Exception;

	public T get(long id) throws Exception;

	public List<T> findAll() throws Exception;

}
