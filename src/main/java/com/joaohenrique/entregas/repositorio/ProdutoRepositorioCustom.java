package com.joaohenrique.entregas.repositorio;

/**
 * Created by jhenr on 14/12/2016.
 */

import com.joaohenrique.entregas.dominio.Produto;

import java.util.List;


public interface ProdutoRepositorioCustom {


    public Produto buscarNomeExato(String nome);
    public List<Produto> buscarTodosOrdenadosPorNome();
    public List<Produto> buscarPorNome(String trecho);
}

