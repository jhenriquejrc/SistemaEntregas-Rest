package com.joaohenrique.entregas.recursos;

import com.joaohenrique.entregas.dominio.Fornecedor;
import com.joaohenrique.entregas.servico.FornecedorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by jhenr on 12/12/2016.
 */
@RestController
@RequestMapping("/fornecedores")
public class FornecedorRecurso {

    @Autowired
    private FornecedorServico fs;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Fornecedor>> todos() {
        List<Fornecedor> lista = fs.buscarTodosOrdenadosPorNome();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
        Fornecedor fs = this.fs.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(fs);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@RequestBody Fornecedor fornecedor) {
        fornecedor = fs.inserir(fornecedor);
        URI uri = getUri("/{id}", fornecedor.getCodFornecedor());
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        fs.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Fornecedor fornecedor, @PathVariable("id") Integer id) {
        fornecedor.setCodFornecedor(id);
        fornecedor = fs.atualizar(fornecedor);
        return ResponseEntity.noContent().build();
    }

    private URI getUri(String nome, Integer valor) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(nome).buildAndExpand(valor).toUri();
    }
}
