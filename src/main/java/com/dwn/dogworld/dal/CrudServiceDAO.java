package com.dwn.dogworld.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * CrudServiceDAO interface
 * @author Nnanna Madu
 * @since 4th Jan, 2015
 *
 */
public interface CrudServiceDAO {
	
	/**
	 * Creates a new object for the given type. After a call to this method the entity will be
	 * persisted into database and then refreshed. Also current persistent session will be flushed.
	 * 
	 * @param <T>
	 * @param t
	 * @return persisted object
	 */
	@CommitAfter
	<T> T create(T t);
	
	/**
	 * Updates the given object
	 * 
	 * @param <T>
	 * @param t
	 * @return persisted object
	 */
	@CommitAfter
	<T> T update(T t);
	
	/**
	 * Deletes the given object by id
	 * 
	 * @param <T>
	 * @param <PK> (PK=Primary Key, could be of any serializable type)
	 * @param type
	 * @param id
	 */
	@CommitAfter
	<T, PK extends Serializable> void delete(Class<T> type, PK id);
	
	/**
	 * Finds a list of objects for the given query name
	 * 
	 * @param <T>
	 * @param queryName
	 * @return returns a list of objects
	 */
	<T> List<T> findWithNamedQuery(String queryName);
	
	/**
	 * Find a query with parameters
	 * 
	 * @param <T>
	 * @param queryName
	 * @param params
	 * @return resulting list
	 */
	<T> List<T> findWithNamedQuery(String queryName, Map<String, Object> params);
	
	/**
	 * Returns one result, query without parameters
	 * 
	 * @param <T>
	 * @param queryName
	 * @return T object
	 */
	<T> T findUniqueWithNamedQuery(String queryName);
	
	/**
	 * Returns just one result with a named query and parameters
	 * 
	 * @param <T>
	 * @param queryName
	 * @param params
	 * @return T object
	 */
	<T> T findUniqueWithNamedQuery(String queryName, Map<String, Object> params);
}
