package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="tb_fornecedor")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codFornecedor;
	private String nome;
	private String cnpj;
	private String fone;
	private String email;
	private BigDecimal faturamentoAnual;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;
	
	@OneToMany(mappedBy="fornecedor")
	@JsonIgnore
	private List<Produto> produtos;
	
	
	public Fornecedor () {
		produtos = new LinkedList();
	}

	public Fornecedor(Integer codFornecedor, String nome, String cnpj, String fone, String email,
			BigDecimal faturamentoAnual, Date dataCadastro) {
		super();
		this.codFornecedor = codFornecedor;
		this.nome = nome;
		this.cnpj = cnpj;
		this.fone = fone;
		this.email = email;
		this.faturamentoAnual = faturamentoAnual;
		this.dataCadastro = dataCadastro;
		produtos = new LinkedList();
	}

	public Integer getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(Integer codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getFaturamentoAnual() {
		return faturamentoAnual;
	}

	public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
		this.faturamentoAnual = faturamentoAnual;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	public void addProduto (Produto x) {
		this.produtos.add(x);
		x.setFornecedor(this);
	}
	
	public void removeProduto (Produto x) {
		this.produtos.remove(x);
	}

	@Override
	public String toString() {
		return "Fornecedor [codFornecedor=" + codFornecedor + ", nome=" + nome + ", cnpj=" + cnpj + ", fone=" + fone
				+ ", email=" + email + ", faturamentoAnual=" + faturamentoAnual + ", dataCadastro=" + dataCadastro
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFornecedor == null) ? 0 : codFornecedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (codFornecedor == null) {
			if (other.codFornecedor != null)
				return false;
		} else if (!codFornecedor.equals(other.codFornecedor))
			return false;
		return true;
	}

	
}
