package com.douineau.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class PersistenceUtil {

	private static final String PERSISTENCE_UNIT_NAME = "quizz_pu";

	public static EntityManager getEntityManager() {

		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
					PropertiesReader.getProperties("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emf.createEntityManager();
	}

}
