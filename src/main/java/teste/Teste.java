package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("yaquisobra");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trs = manager.getTransaction();
	
		
		/*
		 * String string = "abc%def"; StringBuilder stringBuilder = new
		 * StringBuilder(string); stringBuilder.insert(string.lastIndexOf("%"), ',');
		 * stringBuilder.insert(string.lastIndexOf("%"), "");
		 * System.out.println(stringBuilder.toString());
		 */

	}

}
