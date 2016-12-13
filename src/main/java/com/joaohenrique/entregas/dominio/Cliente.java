package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="tb_cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codCliente;
	private String nome;
	private String email;
	private String cpf;

	@OneToMany(mappedBy="cliente")
	@JsonIgnore
	private List<Endereco> enderecos;
	
	public Cliente () {
		enderecos = new LinkedList();
	}

	public Cliente(Integer codCliente, String nome, String email, String cpf) {
		super();
		this.codCliente = codCliente;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		enderecos = new LinkedList();
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	
	public void addEndereco (Endereco x) {
		this.enderecos.add(x);
		x.setCliente(this);
	}
	
	public void removeEndereco (Endereco x) {
		this.enderecos.remove(x);
	}

	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codCliente == null) {
			if (other.codCliente != null)
				return false;
		} else if (!codCliente.equals(other.codCliente))
			return false;
		return true;
	}
	
	
}
