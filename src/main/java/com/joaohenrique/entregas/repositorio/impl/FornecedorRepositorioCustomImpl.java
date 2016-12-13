package com.joaohenrique.entregas.repositorio.impl;

import com.joaohenrique.entregas.dominio.Fornecedor;
import com.joaohenrique.entregas.repositorio.FornecedorRepositorioCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jhenr on 12/12/2016.
 */
@Component
public class FornecedorRepositorioCustomImpl implements FornecedorRepositorioCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Fornecedor buscarNomeExato(String nome) {
        String jpql = "SELECT x FROM Fornecedor x WHERE x.nome = :p1";
        Query query = em.createQuery(jpql);
        query.setParameter("p1", nome);
        List<Fornecedor> aux = query.getResultList();
        return (aux.size() > 0) ? aux.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Fornecedor> buscarTodosOrdenadosPorNome() {
        String jpql = "SELECT x FROM Fornecedor x ORDER BY x.nome";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Fornecedor> buscarPorNome(String trecho) {
        String jpql = "SELECT x FROM Fornecedor x WHERE x.nome LIKE :p1";
        Query query = em.createQuery(jpql);
        query.setParameter("p1", "%"+trecho+"%");
        return query.getResultList();
    }

}
