package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connection.ConnectionFactory;
import model.Estoque;
import model.Ingrediente;

public class EstoqueDao {

	public void incluir(Estoque estoque) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(estoque);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}
	
	public void incluir(Ingrediente ingrediente) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(ingrediente);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public List<Estoque> listarEstoques() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Estoque> estoques = new ArrayList<Estoque>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Estoque> query = criteriaBuilder.createQuery(Estoque.class);
			Root<Estoque> root = query.from(Estoque.class);
			query.select(root);
			query.orderBy(criteriaBuilder.asc(root.get("nome")));
			estoques = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return estoques;
	}

	public boolean procurarEstoque(String cpf) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeEstoque;

		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select cpf from " + "Estoque f where f.cpf" + " = :cpf");

			query.setParameter("cpf", cpf);

			existeEstoque = !query.getResultList().isEmpty();
			manager.getTransaction().commit();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

		return existeEstoque;
	}

	public void excluir(Estoque estoque) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(estoque));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public void alterar(Estoque estoque) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(estoque));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public Estoque estoquePorCodigo(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Estoque> estoques = new ArrayList<Estoque>();
		Estoque estoque = new Estoque();

		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Estoque> query = criteriaBuilder.createQuery(Estoque.class);
			Root<Estoque> root = query.from(Estoque.class);

			query.select(root);
			query.where(criteriaBuilder.equal(root.get("codigo"), codigo));

			estoques = manager.createQuery(query).getResultList();

			for (Estoque fun : estoques) {
				if (fun.getCodigo().equals(codigo)) {
					estoque = fun;
					return estoque;
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}

		return estoque;
	}



}
