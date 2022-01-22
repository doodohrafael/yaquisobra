package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_estoque")
	private Long codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada", nullable = false)
	private Date dataEntrada;

	@Column(name = "fornecedor", length = 255, nullable = false)
	private String fornecedor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ingrediente_codigo_ingrediente", referencedColumnName = "codigo_ingrediente", nullable = false)
	private Ingrediente ingrediente;
	
	// Getters e setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

}
