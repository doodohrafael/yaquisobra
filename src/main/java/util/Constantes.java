package util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantesMBean")
@ApplicationScoped
public class Constantes {

	// Páginas
	public static final String telaAlterarFabricante = "/paginas/alterarFabricante.xhtml?faces-redirect=true";
	public static final String telaConsultarFabricante = "/paginas/consultarFabricante.xhtml?faces-redirect=true";

	public static final String telaAlterarFuncionario = "/paginas/alterarFuncionario.xhtml?faces-redirect=true";
	public static final String telaConsultarFuncionario = "/paginas/consultarFuncionario.xhtml?faces-redirect=true";

	public static final String telaAlterarProduto = "/paginas/alterarProduto.xhtml?faces-redirect=true";
	public static final String telaConsultarProduto = "/paginas/consultarProduto.xhtml?faces-redirect=true";

	public static final String telaLogin = "/paginas/login.xhtml?faces-redirect=true";
	public static final String telaPrincipal = "/paginas/paginaPrincipal.xhtml?faces-redirect=true";

	public static final String telaCadastrarVenda = "/paginas/cadastrarVenda.xhtml?faces-redirect=true";

	// Colunas
	public static final String LABEL_CODIGO = "Código";
	public static final String LABEL_DESCRICAO = "Descrição";
	public static final String LABEL_NOME = "Nome";
	public static final String LABEL_FUNCAO = "Função";
	public static final String LABEL_CPF = "Cpf";
	public static final String LABEL_SENHA = "Senha";
	public static final String LABEL_CONFIRMAR_SENHA = "Confirmar senha";
	public static final String LABEL_OPERADORES = "Operadores";

	public static final String LABEL_FABRICANTES = "Fabricantes";
	public static final String LABEL_FABRICANTE = "Fabricante";
	public static final String LABEL_PRECO = "Preço";
	public static final String LABEL_QUANTIDADE = "Quantidade";
	public static final String LABEL_PRODUTO = "Produto";
	public static final String LABEL_VALOR_PARCIAL = "Valor Parcial";
	public static final String LABEL_TELEFONE = "Telefone";
	public static final String LABEL_LOGRADOURO = "Logradouro";
	public static final String LABEL_NUMERO = "Número";
	public static final String LABEL_BAIRRO = "Bairro";
	public static final String LABEL_MUNICIPIO = "Município";
	public static final String LABEL_ESTADO = "Estado";
	public static final String LABEL_CEP = "Cep";
	public static final String LABEL_FRETE = "Frete";
	public static final String LABEL_TIPO_PAGAMENTO = "Tipo de Pagamento";
	public static final String LABEL_FORNECEDOR = "Fornecedor";
	public static final String LABEL_MARCA = "Marca";
	public static final String LABEL_VOLUME = "Volume";
	public static final String LABEL_IMAGEM = "Imagem";
	public static final String LABEL_DATA_ENTRADA = "Data de Entrada";

	// Botões
	public static final String BOTAO_PESQUISAR = "Pesquisar";
	public static final String BOTAO_ADICIONAR = "Adicionar";
	public static final String BOTAO_NOVO_ADICIONAR = "Novo Produto";
	public static final String BOTAO_FUNCIONARIO_NOVO_ADICIONAR = "Novo Funcionário";
	public static final String BOTAO_SALVAR = "Salvar";
	public static final String BOTAO_VOLTAR = "Voltar";
	public static final String BOTAO_EXCLUIR = "Excluir";
	public static final String BOTAO_ALTERAR = "Alterar";
	public static final String BOTAO_ENTRAR = "Entrar";
	public static final String BOTAO = "NOVO";

	// Mensagens
	public static final String MSG_SALVO_SUCESSO = "Salvo com sucesso.";
	public static final String MSG_NAO_SALVO_SUCESSO = "Erro ao tentar salvar.";
	public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Nenhum registro encontrado.";
	public static final String MSG_DADOS_NAO_ENCONTRADOS = "Dados não encontrados.";
	public static final String MSG_EXCLUIDO_SUCESSO = "Excluído com sucesso.";
	public static final String MSG_NAO_EXCLUIDO_SUCESSO = "Erro ao tentar excluir.";
	public static final String MSG_ALTERADO_SUCESSO = "Alterado com sucesso.";
	public static final String MSG_NAO_ALTERADO_SUCESSO = "Erro ao tentar alterar.";
	public static final String MSG_USUARIO_LOGADO = "Este usuário está logado, não pode ser excluído.";
	public static final String MSG_VENDA_SUCESSO = "Venda feita com sucesso!";
	public static final String MSG_INSERIR_ITENS_CARRINHO = "Adicione no mínimo 1 item no para fazer sua venda.";

	// Mensagens Campos Obrigatórios
	public static final String MSG_CAMPO_DESCRICAO_OBG = "O campo descrição é obrigatório.";
	public static final String MSG_CAMPO_DESCRICO_MIN = "O campo descrição precide de no mínimo 3 caracteres.";
	public static final String MSG_CAMPO_NOME_OBG = "O campo nome é obrigatório.";
	public static final String MSG_CAMPO_CPF_OBG = "O campo cpf é obrigatório.";
	public static final String MSG_CAMPO_CPF_DUPLICADO = "O cpf informado já é cadastrado.";

	public static final String MSG_CAMPO_OBRIGATORIO = "O campo % é obrigatório.";

}
