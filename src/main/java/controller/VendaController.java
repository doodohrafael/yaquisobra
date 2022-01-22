package controller;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dao.EnderecoDao;
import dao.VendaDao;
import model.Cliente;
import model.Endereco;
import model.Produto;
import model.Venda;

public class VendaController {
	
	VendaDao dao = new VendaDao();

	public void incluir(Venda venda) {
		dao.incluir(venda);
	}
	
	public List<Venda> listarVendas() {
		return dao.listarVendas();
	}
	
	public void excluir(Venda venda) {
		dao.excluir(venda);
	}
	
	public void alterar(Venda venda) {
		dao.alterar(venda);
	}
	
	//Endereco
	EnderecoDao daoE = new EnderecoDao();
	public void incluir(Endereco endereco) {
		daoE.incluir(endereco);
	}
	
	//Cliente
	ClienteDao daoC = new ClienteDao();
	public void incluir(Cliente cliente) {
		daoC.incluir(cliente);
	}
	
	public List<Venda> listarVendasPorData(Date dataInicial, Date dataFinal) {
		return dao.listarVendasPorData(dataInicial, dataFinal);
	}
	
}
