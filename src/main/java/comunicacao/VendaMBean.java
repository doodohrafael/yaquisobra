package comunicacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;

import controller.ItemController;
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente;
import model.Endereco;
import model.Item;
import model.Produto;
import model.Venda;
import util.Constantes;
import util.Global;
import util.Mensagens;
import util.Validacao;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class VendaMBean {

	public VendaMBean() {
		if (produto == null) {
			setProduto(new Produto());
		}
		setColecaoProdutos(produtoController.listarProdutos());
		setColecaoItens(new ArrayList<Item>());
		setVenda(new Venda());
		venda.setValorTotal(new BigDecimal("0.00"));
		venda.setFrete(new BigDecimal("0.00"));
	}

	private Produto produto;
	private ProdutoController produtoController = new ProdutoController();

	private ItemController itemController = new ItemController();

	private List<Produto> colecaoProdutos;
	private List<Produto> colecaoProdutosItens;
	private List<Venda> colecaoVendas;
	private List<Venda> vendasPorDatas;
	// = new ArrayList<Venda>();

	private Venda venda;
	private VendaController vendaController = new VendaController();

	private Endereco endereco;

	private Date dataInicial;
	private Date dataFinal;
	private Integer vendasPorData = 0;
	private boolean teste;
	private List<Item> colecaoItens;

	public void consultarVendas() {
		try {
			setVenda(new Venda());
			if (vendasPorData == 1) {
				setColecaoVendas(vendaController.listarVendasPorData(dataInicial, dataFinal));
			} else {
				setColecaoVendas(vendaController.listarVendas());
			}
			Global.limparItens = 1;
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	public void adicionarItens(Produto produto) {

		int posicaoEncontrada = -1;
		for (int p = 0; p < colecaoItens.size() && posicaoEncontrada < 0; p++) {
			Item itemTemporario = colecaoItens.get(p);
			if (itemTemporario.getProduto().equals(produto)) {
				posicaoEncontrada = p;
			}
		}

		Item item = new Item();
		item.setProduto(produto);
		if (posicaoEncontrada < 0 && produto.getQuantidade() > 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			colecaoItens.add(item);
		} else if (produto.getQuantidade() > 0) {
			Item itemTemporario = colecaoItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemporario.getQuantidade() + 1);
			item.setValorParcial(itemTemporario.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
			colecaoItens.set(posicaoEncontrada, item);

		}

		if (produto.getQuantidade() > 0) {
			produto.setQuantidade(produto.getQuantidade() - 1);
			if (venda.getQuantidadeTotal() == null) {
				venda.setQuantidadeTotal(1);
			} else {
				venda.setQuantidadeTotal(venda.getQuantidadeTotal() + 1);
			}

			produtoController.alterar(produto);
			if (venda.getValorTotal() == null) {
				venda.setValorTotal(new BigDecimal("0.00"));
			}
			venda.setValorTotal(venda.getValorTotal().add(produto.getPreco()));

		} else if (produto.getQuantidade() == 0) {
			Mensagens.adicionarMensagemAlerta("Produto deste tipo sem estoque.");
		}

		System.out.println("VALOR TOTAL: " + venda.getValorTotal());

	}

	public void removerItem(Item item) {
		int posicaoEncontrada = -1;
		for (int p = 0; p < colecaoItens.size() && posicaoEncontrada < 0; p++) {
			Item itemDaLista = colecaoItens.get(p);
			if (itemDaLista.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = p;
			}
		}
		colecaoItens.remove(posicaoEncontrada);

		// if (item.getProduto().getQuantidade() >= 0) {//
		item.getProduto().setQuantidade(item.getProduto().getQuantidade() + item.getQuantidade());
		produtoController.alterar(item.getProduto());
		venda.setValorTotal((venda.getValorTotal().subtract(item.getValorParcial())));
		if (venda.getQuantidadeTotal() != null) {
			venda.setQuantidadeTotal(venda.getQuantidadeTotal() - item.getQuantidade()); // removo o objeto item todo
		} else if (venda.getQuantidadeTotal() != null && venda.getQuantidadeTotal() == 0) {
			// venda.setQuantidadeTotal(null);
			Mensagens.adicionarMensagemAlerta("Você precisa por algo no carrinho");
		}

	}

	public void listarProdutos() {
		try {
			// setProduto(new Produto());
			vendasPorData = 0;
			limparCamposDatas();
			setColecaoProdutos(produtoController.listarProdutos());
			/*
			 * if (Global.limparItens == 1) { Global.limparItens = 0; colecaoItens = new
			 * ArrayList<Item>(); }
			 */

		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	// MELHORAR ESSSA PORRA
	public void resetarCarrinho() {
		venda.setHorario(new Date());
		venda.setFuncionario(LoginMBean.funcionarioLogado);
		venda.setCliente(new Cliente());
		venda.getCliente().setEndereco(new Endereco());
		venda.setFrete(new BigDecimal("0.00"));
	}

	/*
	 * public void verificarCarrinhoVazio() { if
	 * (venda.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
	 * PrimeFaces.current().dialog().closeDynamic(null);
	 * Mensagens.adicionarMensagemErro(Constantes.MSG_INSERIR_ITENS_CARRINHO); }
	 * PrimeFaces.current().dialog().openDynamic("dialog-finalizar"); }
	 */

	public void abrirDialog() {

		if (venda.getQuantidadeTotal() == null || venda.getQuantidadeTotal() == 0) {
			Mensagens.adicionarMensagemAlerta("Você precisa por algo no carrinho");
		} else {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("closable", true);
			options.put("draggable", true);
			options.put("resizable", true);
			options.put("contentHeight", 700);
			options.put("contentWidth", 720);
			options.put("showEffect", "explode");
			PrimeFaces.current().dialog().openDynamic("dialog-finalizar", options, null);
		}
	}

	public void fecharDialog() {
		// venda.setQuantidadeTotal(null);
		PrimeFaces.current().dialog().closeDynamic(null);
	}

	public void fecharDialogFinalizar() {
		resetarCarrinho();
		setVenda(new Venda());
		PrimeFaces.current().dialog().closeDynamic("dialog-finalizar");
	}
	
	public void salvar() {
		try {
			if (validarCampos()) {

				vendaController.incluir(venda.getCliente().getEndereco());
				vendaController.incluir(venda.getCliente());
				vendaController.incluir(venda);

				// entender isso aqui - DEGUGAR
				if (venda.getCodigo() != null) {
					for (Item item : colecaoItens) {
						item.setVenda(venda);
						itemController.incluir(item);
					}
				}

				// resetarCarrinho();
				setColecaoItens(new ArrayList<Item>());
				setVenda(new Venda());
				venda.setValorTotal(new BigDecimal("0.00"));
				venda.setFrete(new BigDecimal("0.00"));

				// fecharDialog();
				Mensagens.adicionarMensagemInfo(Constantes.MSG_VENDA_SUCESSO); // mudar posição da msg
				listarProdutos();
				//fecharDialogFinalizar();
				
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_SALVO_SUCESSO);

		}
	}

	public void filtrarPorData() {
		vendaController.listarVendasPorData(dataInicial, dataFinal);
		vendasPorData = 1;
	}

	public Boolean validarCampos() {
		Validacao validar = new Validacao();

		boolean nomeOK = validar.notNullnotEmpty(venda.getCliente().getNome(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_NOME);

		boolean telefoneOK = validar.notNullnotEmpty(venda.getCliente().getTelefone(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_TELEFONE);

		boolean freteOK = validar.notNullnotEmptyFrete(venda.getFrete(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_FRETE);

		boolean tipoPagamentoOK = validar.notNullnotEmpty(venda.getTipoPagamento(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_TIPO_PAGAMENTO);

		boolean logradouroOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getLogradouro(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_LOGRADOURO);

		boolean numeroOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getNumero(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_NUMERO);

		boolean bairroOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getBairro(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_BAIRRO);

		boolean municipioOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getMunicipio(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_MUNICIPIO);

		boolean estadoOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getEstado(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_ESTADO);

		boolean cepOK = validar.notNullnotEmpty(venda.getCliente().getEndereco().getCep(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_CEP);

		return nomeOK && telefoneOK && freteOK
				&& tipoPagamentoOK & logradouroOK & numeroOK & bairroOK & municipioOK & estadoOK & cepOK;
	}

	public void limparCamposDatas() {
		setDataInicial(null);
		setDataFinal(null);
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

	public List<Item> getColecaoItens() {
		return colecaoItens;
	}

	public void setColecaoItens(List<Item> colecaoItens) {
		this.colecaoItens = colecaoItens;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Venda> getColecaoVendas() {
		return colecaoVendas;
	}

	public void setColecaoVendas(List<Venda> colecaoVendas) {
		this.colecaoVendas = colecaoVendas;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Venda> getVendasPorDatas() {
		return vendasPorDatas;
	}

	public void setVendasPorDatas(List<Venda> vendasPorDatas) {
		this.vendasPorDatas = vendasPorDatas;
	}

	public Integer getVendasPorData() {
		return vendasPorData;
	}

	public void setVendasPorData(Integer vendasPorData) {
		this.vendasPorData = vendasPorData;
	}

	public List<Produto> getColecaoProdutosItens() {
		return colecaoProdutosItens;
	}

	public void setColecaoProdutosItens(List<Produto> colecaoProdutosItens) {
		this.colecaoProdutosItens = colecaoProdutosItens;
	}

	public boolean isTeste() {
		return teste;
	}

	public void setTeste(boolean teste) {
		this.teste = teste;
	}
}
