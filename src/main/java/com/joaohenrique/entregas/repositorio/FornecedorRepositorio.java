package com.joaohenrique.entregas.repositorio;

import com.joaohenrique.entregas.dominio.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jhenr on 12/12/2016.
 */
public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Integer> {

}
