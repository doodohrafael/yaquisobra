package model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_endereco")
	private Long codigo;
	
	@Column(name = "logradouro", length = 70, nullable = false)
	private String logradouro;
	
	@Column(name = "numero", length = 20, nullable = false)
	private String numero;
	
	@Column(name = "bairro", length = 70, nullable = false)
	private String bairro;
	
	@Column(name = "municipio", length = 70, nullable = false)
	private String municipio;
	
	@Column(name = "estado", length = 2, nullable = false)
	private String estado;
	
	@Column(name = "cep", length = 9, nullable = false)
	private String cep;
	
	@Column(name = "complemento", length = 70, nullable = true)
	private String complemento;
	
	
	// Getters e setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}