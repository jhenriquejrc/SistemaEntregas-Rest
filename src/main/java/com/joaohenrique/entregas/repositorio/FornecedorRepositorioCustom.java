package com.joaohenrique.entregas.repositorio;

import com.joaohenrique.entregas.dominio.Fornecedor;

import java.util.List;

/**
 * Created by jhenr on 12/12/2016.
 */
public interface FornecedorRepositorioCustom {
    public Fornecedor buscarNomeExato(String nome);
    public List<Fornecedor> buscarTodosOrdenadosPorNome();
    public List<Fornecedor> buscarPorNome (String trecho);

}
