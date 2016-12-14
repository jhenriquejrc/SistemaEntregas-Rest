package com.joaohenrique.entregas.recursos;


import com.joaohenrique.entregas.dominio.Produto;
import com.joaohenrique.entregas.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;




@RestController
@RequestMapping("/produtos")
public class ProdutoRecurso {


    @Autowired
    private ProdutoRepositorio repo;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> todos() {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
        Produto produto = repo.findOne(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criar(@RequestBody Produto produto) {
        produto = repo.save(produto);
        URI uri = getUri("/{id}", produto.getCodProduto());
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Produto produto, @PathVariable("id") Integer id) {
        produto.setCodProduto(id);
        repo.save(produto);
        return ResponseEntity.noContent().build();
    }

    private URI getUri(String nome, Integer valor) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(nome).buildAndExpand(valor).toUri();
    }


}
