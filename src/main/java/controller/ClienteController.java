package controller;

import java.util.List;

import dao.ClienteDao;
import dao.ProdutoDao;
import model.Cliente;
import model.Produto;

public class ClienteController {
	
	ProdutoDao dao = new ProdutoDao();
	ClienteDao daoC = new ClienteDao();

	public void incluir(Produto produto) {
		dao.incluir(produto);
	}
	
	public void incluir(Cliente cliente) {
		daoC.incluir(cliente);
	}
	
	public List<Produto> listarProdutos() {
		return dao.listarProdutos();
	}
	
	public void excluir(Produto produto) {
		dao.excluir(produto);
	}
	
	public void alterar(Produto produto) {
		dao.alterar(produto);
	}
	
	public boolean procurarProduto(Long codigo) {
		return dao.procurarProduto(codigo);
	}
	
}
