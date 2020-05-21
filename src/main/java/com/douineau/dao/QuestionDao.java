package com.douineau.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.douineau.entity.Question;
import com.douineau.utils.PersistenceUtil;

public class QuestionDao {

	public static void insertQuestion(Question question) {
		EntityManager em = PersistenceUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();

		em.close();
	}

	
	public static List<Question> getRandomQuestions(int nb) {
    	EntityManager em = PersistenceUtil.getEntityManager();
    	
    	Random random = new Random();
    	List<Integer> integers = new ArrayList<Integer>();
    	for(int k = 0; k < nb ; k++) {
    		//Eviter le zéro 
    		integers.add(random.nextInt(nb - 1) + 1);
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT q FROM Question q WHERE q.id in (1, 2)");
//    	sb.append("SELECT q FROM Question q WHERE q.id in (");
//    	for(Integer i : integers) {
//    		sb.append(i);	
//    		sb.append(",");
//    	}
//    	sb.deleteCharAt(sb.length() - 1);
//    	sb.append(")");
    	
		TypedQuery<Question> query = em.createQuery(sb.toString(), Question.class);
		List<Question> questions = query.getResultList();
		em.close();
		
		return questions;
	}
	
}
