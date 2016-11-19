package br.com.headway.etl.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("filialPU");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
