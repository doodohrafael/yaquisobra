package controller;

import java.util.List;

import dao.EstoqueDao;
import model.Estoque;
import model.Ingrediente;

public class EstoqueController {
	
	EstoqueDao dao = new EstoqueDao();

	public void incluir(Estoque estoque) {
		dao.incluir(estoque);
	}
	
	public List<Estoque> listarEstoques() {
		return dao.listarEstoques();
	}
	
	public void excluir(Estoque estoque) {
		dao.excluir(estoque);
	}
	
	public void alterar(Estoque estoque) {
		dao.alterar(estoque);
	}
	
	public boolean procurarEstoque(String cpf) {
		return dao.procurarEstoque(cpf);
	}
	
	public Estoque estoquePorCodigo(Long codigo) {
		return dao.estoquePorCodigo(codigo);
	}
	
	public void incluir(Ingrediente ingrediente) {
		dao.incluir(ingrediente);
	}
	
}
