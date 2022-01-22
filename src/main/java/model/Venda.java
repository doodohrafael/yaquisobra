package model;

import java.math.BigDecimal;
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
@Table(name = "venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_venda")
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "horario", nullable = false)
	private Date horario;

	@Column(name = "valor_total", precision = 7, scale = 2 , nullable = false)
	private BigDecimal valorTotal;
	
	@Column(name = "valor_investido", precision = 7, scale = 2 , nullable = false)
	private BigDecimal valorInvestido;
	
	@Column(name = "valor_ganho", precision = 7, scale = 2 , nullable = false)
	private BigDecimal valorGanho;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionario_codigo_funcionario", referencedColumnName = "codigo_funcionario", nullable = false)
	private Funcionario funcionario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_codigo_cliente", referencedColumnName = "codigo_cliente", nullable = false)
	private Cliente cliente;
	
	@Column(name = "quantidadeTotal", nullable = false)
	private Integer quantidadeTotal;
	
	@Column(name = "frete", precision = 7, scale = 2 , nullable = false)
	private BigDecimal frete;
	
	@Column(name = "tipo_pagamento", length = 20, nullable = false)
	private String tipoPagamento;
	
	// Getters e setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getFrete() {
		return frete;
	}

	public void setFrete(BigDecimal frete) {
		this.frete = frete;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public BigDecimal getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(BigDecimal valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public BigDecimal getValorGanho() {
		return valorGanho;
	}

	public void setValorGanho(BigDecimal valorGanho) {
		this.valorGanho = valorGanho;
	}

}