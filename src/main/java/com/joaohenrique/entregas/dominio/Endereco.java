package com.joaohenrique.entregas.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="tb_endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codEndereco;
	private String logadouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@OneToMany(mappedBy="endereco")
	@JsonIgnore
	private List<Entrega> entregas;
	
	
    @ManyToOne
    @JoinColumn(name="cliente")
    @JsonIgnore
	private Cliente cliente;
	
	public Endereco () {
		entregas = new LinkedList();
	}

	public Endereco(Integer codEndereco, String logadouro, Integer numero, String complemento, String bairro,
			String cep, Cliente cliente) {
		super();
		this.codEndereco = codEndereco;
		this.logadouro = logadouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cliente = cliente;
		cliente.addEndereco(this);
		entregas = new LinkedList();
	}

	public Integer getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Integer codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getLogadouro() {
		return logadouro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addEntrega (Entrega x) {
		this.entregas.add(x);
		x.setEndereco(this);
	}
	
	public void removeEntrega (Entrega x) {
		this.entregas.remove(x);
	}

	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", logadouro=" + logadouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEndereco == null) ? 0 : codEndereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (codEndereco == null) {
			if (other.codEndereco != null)
				return false;
		} else if (!codEndereco.equals(other.codEndereco))
			return false;
		return true;
	}
	
	
	
	
}
