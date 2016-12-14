package com.joaohenrique.entregas.repositorio;

import com.joaohenrique.entregas.dominio.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jhenr on 12/12/2016.
 */
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

}
