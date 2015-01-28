package com.dwn.dogworld.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Hibernate CrudService
 * 
 * @author Nnanna Madu
 * @since 4th Jan, 2015
 * 
 * @param <T>, type entity
 * @param <PK>, primary key, the primary key
 *
 */
public class HibernateCrudServiceDAO implements CrudServiceDAO {
	
	@Inject
	private Session session;

	public <T> T create(T t) {
		session.persist(t);
		session.flush();
		session.refresh(t);
		return t;
	}

	public <T> T update(T t) {
		session.merge(t);
		return t;
	}

	public <T, PK extends Serializable> void delete(Class<T> type, PK id) {
		@SuppressWarnings("unchecked")
		T ref = (T) session.get(type, id);
		session.delete(ref);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findWithNamedQuery(String queryName) {
		return session.getNamedQuery(queryName).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findWithNamedQuery(String queryName,
			Map<String, Object> params) {
		Set<Entry<String, Object>> rawParameters = params.entrySet();
		Query query = session.getNamedQuery(queryName);
		
		for(Entry<String, Object> entry : rawParameters){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueWithNamedQuery(String queryName) {
		return (T) session.getNamedQuery(queryName).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueWithNamedQuery(String queryName,
			Map<String, Object> params) {
		Set<Entry<String, Object>> rawParameters = params.entrySet();
		Query query = session.getNamedQuery(queryName);
		
		for(Entry<String, Object> entry : rawParameters){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return (T) query.uniqueResult();
	}

}
