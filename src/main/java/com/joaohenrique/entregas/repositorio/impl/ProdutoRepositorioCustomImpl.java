package com.joaohenrique.entregas.repositorio.impl;

import com.joaohenrique.entregas.dominio.Produto;
import com.joaohenrique.entregas.repositorio.ProdutoRepositorioCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jhenr on 12/12/2016.
 */
@Component
public class ProdutoRepositorioCustomImpl implements ProdutoRepositorioCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Produto buscarNomeExato(String nome) {
        String jpql = "SELECT x FROM Produto x WHERE x.nome = :p1";
        Query query = em.createQuery(jpql);
        query.setParameter("p1", nome);
        List<Produto> aux = query.getResultList();
        return (aux.size() > 0) ? aux.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Produto> buscarTodosOrdenadosPorNome() {
        String jpql = "SELECT x FROM Produto x ORDER BY x.nome";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Produto> buscarPorNome(String trecho) {
        String jpql = "SELECT x FROM Produto x WHERE x.nome LIKE :p1";
        Query query = em.createQuery(jpql);
        query.setParameter("p1", "%"+trecho+"%");
        return query.getResultList();
    }

}
