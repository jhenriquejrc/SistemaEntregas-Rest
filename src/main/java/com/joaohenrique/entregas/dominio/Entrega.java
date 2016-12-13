package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="tb_entrega")
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codEntrega;
	private Date data;
	
	@OneToMany(mappedBy="entrega")
	@JsonIgnore
	private List<ItemEntregue> itens;
	
	@ManyToOne
	@JoinColumn(name="endereco")
	@JsonIgnore
	private Endereco endereco;
	
	public Entrega () {
		itens = new LinkedList<ItemEntregue>();
	}

	public Entrega(Integer codEntrega, Date data, Endereco endereco) {
		super();
		this.codEntrega = codEntrega;
		this.data = data;
		this.endereco = endereco;
		endereco.addEntrega(this);
		itens = new LinkedList<ItemEntregue>();
	}

	public Integer getCodEntrega() {
		return codEntrega;
	}

	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemEntregue> getItens() {
		return itens;
	}

	public void setItens(List<ItemEntregue> itens) {
		this.itens = itens;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void addItemEntregue (ItemEntregue x) {
		this.itens.add(x);
		x.setEntrega(this);
	}
	
	public void removeItemEntregue (ItemEntregue x) {
		this.itens.remove(x);
	}

	@Override
	public String toString() {
		return "Entrega [codEntrega=" + codEntrega + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEntrega == null) ? 0 : codEntrega.hashCode());
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
		Entrega other = (Entrega) obj;
		if (codEntrega == null) {
			if (other.codEntrega != null)
				return false;
		} else if (!codEntrega.equals(other.codEntrega))
			return false;
		return true;
	}
	
	public BigDecimal getValorTotalDaEntrega () {
		BigDecimal total = new BigDecimal ("0.00");
		for (ItemEntregue i : itens) {
			total = total.add(i.getSubTotalDoItem());
		}
		return total;
	}
	
	
	
}
