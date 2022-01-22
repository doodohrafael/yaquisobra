package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import connection.ConnectionFactory;
import model.Venda;

public class VendaDao {

	public void incluir(Venda venda) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(venda);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public List<Venda> listarVendas() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Venda> vendas = new ArrayList<Venda>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Venda> query = criteriaBuilder.createQuery(Venda.class);
			Root<Venda> root = query.from(Venda.class);
			query.select(root);
			query.orderBy(criteriaBuilder.desc(root.get("horario")));
			vendas = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return vendas;
	}

	public boolean procurarVenda(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeVenda;

		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select codigo from " + "Venda v where v.codigo" + " = :codigo");

			query.setParameter("codigo", codigo);

			existeVenda = query.getResultList().isEmpty();
			manager.getTransaction().commit();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

		return existeVenda;
	}

	public void excluir(Venda venda) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(venda));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public void alterar(Venda venda) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(venda));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public List<Venda> listarVendasPorData(Date dataInicial, Date dataFinal) {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Venda> vendas = null;
		try {
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Venda> c = cb.createQuery(Venda.class);
			Root<Venda> venda = c.from(Venda.class);

			// get error here; "The method get(String) in the type Path<Registry> is not
			// applicable for the arguments (String, Date, Date)"
			c.select(venda).where(cb.between(venda.get("horario"), dataInicial, dataFinal));

			vendas = manager.createQuery(c).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return vendas;
	}
}
