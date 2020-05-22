package com.douineau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.utils.PersistenceUtil;

public class DaoUtil {

	public static void trunctateTable(String table) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(table);
		sb.append(" WHERE id > 0");
		Query query = em.createNativeQuery(sb.toString());
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static Long insertObject(Question question) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(question);
		
		em.getTransaction().commit();
		em.close();
		
		return question.getId();
		
	}
	
	public static void insertObject(Reponse reponse) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(reponse);
		
		em.getTransaction().commit();
		em.close();
				
	}
	
}
