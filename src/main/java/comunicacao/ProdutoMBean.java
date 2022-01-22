package comunicacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.ProdutoController;
import model.Produto;
import util.Constantes;
import util.Global;
import util.Mensagens;
import util.Validacao;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class ProdutoMBean {

	public ProdutoMBean() {
		if (produto == null) {
			setProduto(new Produto());
		}
		setColecaoProdutos(produtoController.listarProdutos());
	}

	private Produto produto;
	private ProdutoController produtoController = new ProdutoController();

	private List<Produto> colecaoProdutos;

	public void salvar() {
		try {
			if (validarCampos()) {
				//produto.setIngrediente(new Ingrediente());
				produtoController.incluir(produto);
				setProduto(new Produto());
				Mensagens.adicionarMensagemInfo(Constantes.MSG_SALVO_SUCESSO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			setProduto(new Produto());
			Mensagens.adicionarMensagemInfo(Constantes.MSG_NAO_SALVO_SUCESSO);
		}
	}

	public void consultarProdutos() {
		try {
			setProduto(new Produto());
			setColecaoProdutos(produtoController.listarProdutos());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	public void excluir(Produto produto) {
		try {
			produtoController.excluir(produto);
			Mensagens.adicionarMensagemInfo(Constantes.MSG_EXCLUIDO_SUCESSO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_EXCLUIDO_SUCESSO);
		}
	}

	public String telaAlterar() {
		return Constantes.telaAlterarProduto;
	}

	public String alterar() {
		try {
			if (validarCampos()) {
				produtoController.alterar(produto);
				setProduto(new Produto());
				return Constantes.telaConsultarProduto;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_ALTERADO_SUCESSO);
		}
		return null;
	}

	public Boolean validarCampos() {
		Validacao validar = new Validacao();

		boolean nomeOK = validar.notNullnotEmpty(produto.getNome(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_NOME);

		boolean descricaoOK = validar.notNullnotEmpty(produto.getDescricao(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_DESCRICAO);
		
		boolean precoOK = validar.notNullnotEmpty(produto.getPreco(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_PRECO);
		
		boolean quantidadeOK = validar.notNullnotEmpty(produto.getQuantidade(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_QUANTIDADE);
		
		return nomeOK && descricaoOK && precoOK && quantidadeOK;
	}

	public void atualizarSessao() {
		setProduto(new Produto());
		Global.limparItens = 1;
	}
	
	// getters and setters
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getColecaoProdutos() {
		return colecaoProdutos;
	}

	public void setColecaoProdutos(List<Produto> colecaoProdutos) {
		this.colecaoProdutos = colecaoProdutos;
	}

}
