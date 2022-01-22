package controller;

import java.util.List;

import dao.EnderecoDao;
import dao.ProdutoDao;
import model.Endereco;
import model.Produto;

public class EnderecoController {
	
	ProdutoDao dao = new ProdutoDao();
	EnderecoDao daoE = new EnderecoDao();

	public void incluir(Endereco endereco) {
		daoE.incluir(endereco);
	}
	
	public void incluir(Produto produto) {
		dao.incluir(produto);
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
