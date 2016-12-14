package com.joaohenrique.entregas.servico;

import com.joaohenrique.entregas.dominio.Produto;
import com.joaohenrique.entregas.repositorio.ProdutoRepositorio;
import com.joaohenrique.entregas.repositorio.ProdutoRepositorioCustom;
import com.joaohenrique.entregas.servico.excecoes.NaoEncontradoException;
import com.joaohenrique.entregas.servico.excecoes.ServicoException;
import com.joaohenrique.entregas.servico.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio repo;

    @Autowired
    private ProdutoRepositorioCustom repoCustom;

    public void validar(Produto x) {
        List<String> erros = new ArrayList<>();

        if (x.getNome()==null) {
            erros.add("Favor preencher o campo nome");
        }
        if (x.getFornecedor()==null) {
            erros.add("Favor selecione um fornecedor para o produto");
        }
        if (x.getPreco()==null) {
            erros.add("Favor preencher o campo preço com um valor válido");
        }

        if (!erros.isEmpty()) {
            throw new ValidacaoException("Erro de validação", erros);
        }
    }

    public Produto inserir(Produto x) throws ServicoException {
        Produto aux = repoCustom.buscarNomeExato(x.getNome());
        if (aux != null) {
            throw new ServicoException("Já existe um produto com esse nome!", 1);
        }
        validar(x);
        return repo.save(x);
    }

    public Produto atualizar(Produto x) throws ServicoException {
        Produto aux = repo.findOne(x.getCodProduto());
        if (aux == null) {
            throw new NaoEncontradoException("Fornecedor não encontrado!", 1);
        }
        aux = repoCustom.buscarNomeExato(x.getNome());
        if (aux != null && aux.getCodProduto()!=x.getCodProduto()) {
            throw new ServicoException("Já existe um outro produto com esse nome!", 1);
        }
        validar(x);
        return repo.save(x);
    }

    public void excluir(int cod) throws ServicoException {
        Produto prod = repo.findOne(cod);
        if (prod == null) {
            throw new NaoEncontradoException("Fornecedor não encontrado!", 1);
        }
        if (!prod.getItens().isEmpty()) {
            throw new ServicoException("Exclusão não permitida: este produto possui entregas!", 2);
        }
        repo.delete(prod);
    }

    public Produto buscar(int cod) {
        Produto prod = repo.findOne(cod);
        if (prod == null) {
            throw new NaoEncontradoException("Produto não encontrado!", 1);
        }
        return prod;
    }

    public List<Produto> buscarTodosOrdenadosPorNome() {
        return repoCustom.buscarTodosOrdenadosPorNome();
    }

    public List<Produto> buscarPorNome(String trecho) {
        return repoCustom.buscarPorNome(trecho);
    }
}
