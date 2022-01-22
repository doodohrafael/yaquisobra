package comunicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.EstoqueController;
import model.Cliente;
import model.Endereco;
import model.Estoque;
import model.Ingrediente;
import util.Constantes;
import util.Mensagens;
import util.Validacao;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class EstoqueMBean {

	private Estoque estoque;
	private Ingrediente ingrediente;
	private List<Estoque> colecaoEstoque;
	private EstoqueController estoqueController = new EstoqueController();

	public EstoqueMBean() {
		if (estoque == null) {
			setEstoque(new Estoque());
			estoque.setIngrediente(new Ingrediente());
			estoque.setDataEntrada(new Date());
		}
		// setColecaoEstoque(estoqueController.listarEstoques());
	}

	public void salvar() {
		try {
			if (validarCampos()) {
				//estoque.setDataEntrada(new Date());
				estoqueController.incluir(estoque.getIngrediente());
				estoqueController.incluir(estoque);
				setEstoque(new Estoque());
				estoque.setIngrediente(new Ingrediente());
				Mensagens.adicionarMensagemInfo(Constantes.MSG_SALVO_SUCESSO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			setEstoque(new Estoque());
			estoque.setIngrediente(new Ingrediente());
			Mensagens.adicionarMensagemInfo(Constantes.MSG_NAO_SALVO_SUCESSO);
		}
	}

	public void atualizarSessao() {
		setEstoque(new Estoque());
		estoque.setIngrediente(new Ingrediente());
	}

	public Boolean validarCampos() {
		Validacao validar = new Validacao();

		boolean fornecedorOK = validar.notNullnotEmpty(estoque.getFornecedor(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_NOME);

		boolean dataEntradaOK = validar.notNullnotEmpty(estoque.getDataEntrada(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_DESCRICAO);

		boolean descricaoOK = validar.notNullnotEmpty(estoque.getIngrediente().getDescricao(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_DATA_ENTRADA);

		boolean marcaOK = validar.notNullnotEmpty(estoque.getIngrediente().getMarca(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_MARCA);

		boolean nomeOK = validar.notNullnotEmpty(estoque.getIngrediente().getNome(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_NOME);

		boolean precoOK = validar.notNullnotEmpty(estoque.getIngrediente().getPreco(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_PRECO);

		boolean volumeOK = validar.notNullnotEmpty(estoque.getIngrediente().getVolume(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_VOLUME);
		
		return nomeOK && descricaoOK && precoOK && fornecedorOK && dataEntradaOK && marcaOK && volumeOK;
	}

	public void consultarEstoque() {
		try {
			setEstoque(new Estoque());
			setColecaoEstoque(estoqueController.listarEstoques());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	// Getters and Setters
	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Estoque> getColecaoEstoque() {
		return colecaoEstoque;
	}

	public void setColecaoEstoque(List<Estoque> colecaoEstoque) {
		this.colecaoEstoque = colecaoEstoque;
	}

}
