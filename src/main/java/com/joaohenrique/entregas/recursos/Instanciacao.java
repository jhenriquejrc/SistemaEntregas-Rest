package com.joaohenrique.entregas.recursos;

import com.joaohenrique.entregas.dominio.Fornecedor;
import com.joaohenrique.entregas.dominio.Produto;
import com.joaohenrique.entregas.repositorio.FornecedorRepositorio;
import com.joaohenrique.entregas.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by jhenr on 12/12/2016.
 */

@RestController
@RequestMapping("/instanciacao")
public class Instanciacao {

    @Autowired
    private FornecedorRepositorio fornecedorRepo;
    private ProdutoRepositorio produtoRepo;

    @RequestMapping(method= RequestMethod.GET)
    public String todos() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


            Fornecedor f1 = new Fornecedor(null, "Casas Bahia", "13245665", "3003 5504", "zezinho.logistica@casabahia.com.br", new BigDecimal("20300000.00"), sdf.parse("11/11/2014"));
            Fornecedor f2 = new Fornecedor(null, "Ponto Frio", "12398764", "3003 4012", "peitim.vendas@pontofrio.com.br", new BigDecimal("7600000.00"), sdf.parse("02/12/2016"));
            Fornecedor f3 = new Fornecedor(null, "Ricardo Eletro", "98736422", "4004 5504", "ricardao@ricardoeletro.com.br", new BigDecimal("80000000.00"), sdf.parse("06/07/2013"));
            Fornecedor f4 = new Fornecedor(null, "Submarino", "873645234", "4004 2332", "juarez.silva@submarino.com.br", new BigDecimal("1420000.00"), sdf.parse("08/12/2008"));
            Produto p1 = new Produto(null, "Guarda-Roupa Gigantão", new BigDecimal("899.00"), f1);
            Produto p2 = new Produto(null, "Cama box do cachorro", new BigDecimal("599.00"), f1);
            Produto p3 = new Produto(null, "Smartphone Samsung Galaxy Ace69", new BigDecimal("499.00"), f2);
            Produto p4 = new Produto(null, "Smart TV '14 LG UHD 4K C/ Antena Pé de Galinha", new BigDecimal("399.00"), f4);

            fornecedorRepo.save(f1);
            fornecedorRepo.save(f2);
            fornecedorRepo.save(f3);
            fornecedorRepo.save(f4);
            produtoRepo.save(p1);
            produtoRepo.save(p2);
            produtoRepo.save(p3);
            produtoRepo.save(p4);

        } catch (ParseException e) {
            return e.getMessage();

        }
return "Pronto";
    }
}
