package teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoJunitTeste {

	@Test
	@Ignore
	public void salvar() {
		Produto produto = new Produto();
		ProdutoDao dao = new ProdutoDao();
		
		produto.setDescricao("Altamente nutritivo");
		produto.setPreco(new BigDecimal(23.55D));
		produto.setNome("Nescaf�");
		produto.setQuantidade(10);
		
		dao.incluir(produto);
		System.out.println("Inserido");
		
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDao dao = new ProdutoDao();
		List<Produto> produtos = dao.listarProdutos();
		
		for(Produto produto : produtos) {
			System.out.println(produto.getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void procurarProduto() {
		ProdutoDao dao = new ProdutoDao();
		boolean teste = dao.procurarProduto(2L);
		if(teste == true) {
			System.out.println("Produto N�O EXISTE!");
		}else {
			System.out.println("Produto EXISTE!");
		}
		
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDao dao = new ProdutoDao();
		Produto produto = new Produto();
		produto.setCodigo(41L);
	
		dao.excluir(produto);
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutoDao dao = new ProdutoDao();
		Produto produto = new Produto();
		
		produto.setCodigo(600L);
		produto.setDescricao("Nutritivo e da vaca");;
		produto.setNome("Leite Integral");
		produto.setPreco(new BigDecimal(4.60D));
		produto.setQuantidade(2);
		
		dao.alterar(produto);
		System.out.println("Depois: "+produto.getNome());
	}
}
