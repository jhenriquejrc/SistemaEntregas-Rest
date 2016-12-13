package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="tb_itens")
public class ItemEntregue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codItemEntregue;
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name="produto")
	@JsonIgnore
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="entrega")
	@JsonIgnore
	private Entrega entrega;
	
	public ItemEntregue () {
		
	}

	public ItemEntregue(Integer codItemEntregue, Integer quantidade, Produto produto, Entrega entrega) {
		super();
		this.codItemEntregue = codItemEntregue;
		this.quantidade = quantidade;
		this.produto = produto;
		produto.addItemEntregue(this);
		this.entrega = entrega;
		entrega.addItemEntregue(this);
	}

	public Integer getCodItemEntregue() {
		return codItemEntregue;
	}

	public void setCodItemEntregue(Integer codItemEntregue) {
		this.codItemEntregue = codItemEntregue;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "ItemEntregue [codItemEntregue=" + codItemEntregue + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codItemEntregue == null) ? 0 : codItemEntregue.hashCode());
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
		ItemEntregue other = (ItemEntregue) obj;
		if (codItemEntregue == null) {
			if (other.codItemEntregue != null)
				return false;
		} else if (!codItemEntregue.equals(other.codItemEntregue))
			return false;
		return true;
	}
	
	public BigDecimal getSubTotalDoItem () {
		return produto.getPreco().multiply(new BigDecimal(quantidade));
	}
	
	
	
}
