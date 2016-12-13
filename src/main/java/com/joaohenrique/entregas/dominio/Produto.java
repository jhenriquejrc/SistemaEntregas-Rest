package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="tb_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codProduto;
	private String nome;
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(name="fornecedor")
	@JsonIgnore
	private Fornecedor fornecedor;

	@OneToMany(mappedBy="produto")
	@JsonIgnore
	private List<ItemEntregue> itens;
	
	public Produto() {
		itens = new LinkedList();
	}

	public Produto(Integer codProduto, String nome, BigDecimal preco, Fornecedor fornecedor) {
		super();
		this.codProduto = codProduto;
		this.nome = nome;
		this.preco = preco;
		this.fornecedor = fornecedor;
		fornecedor.addProduto(this);
		itens = new LinkedList();
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemEntregue> getItens() {
		return itens;
	}

	public void setItens(List<ItemEntregue> itens) {
		this.itens = itens;
	}
	
	public void addItemEntregue (ItemEntregue x) {
		this.itens.add(x);
		x.setProduto(this);
	}
	
	public void removeItemEntregue (ItemEntregue x) {
		this.itens.remove(x);
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", nome=" + nome + ", preco=" + preco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProduto == null) ? 0 : codProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		return true;
	}
	
	
	
}
