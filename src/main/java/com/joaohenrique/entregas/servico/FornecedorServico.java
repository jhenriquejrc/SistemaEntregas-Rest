package com.joaohenrique.entregas.servico;

import com.joaohenrique.entregas.dominio.Fornecedor;
import com.joaohenrique.entregas.repositorio.FornecedorRepositorio;
import com.joaohenrique.entregas.repositorio.FornecedorRepositorioCustom;
import com.joaohenrique.entregas.servico.excecoes.NaoEncontradoException;
import com.joaohenrique.entregas.servico.excecoes.ServicoException;
import com.joaohenrique.entregas.servico.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorServico {

    @Autowired
    private FornecedorRepositorio repo;

    @Autowired
    private FornecedorRepositorioCustom repoCustom;

    public void validar(Fornecedor x) {
        List<String> erros = new ArrayList<>();

        if (x.getNome()==null) {
            erros.add("Favor preencher o campo nome");
        }
        if (x.getCnpj()==null) {
            erros.add("Favor preencher o campo CNPJ");
        }
        if (x.getFone()==null) {
            erros.add("Favor preencher o campo fone");
        }
        if (x.getEmail()==null) {
            erros.add("Favor preencher o campo email");
        }
        if (x.getFaturamentoAnual()==null) {
            erros.add("Favor preencher um valor válido para o faturamento anual");
        }
        if (x.getDataCadastro()==null) {
            erros.add("Favor preencher um valor válido para a data de cadastro");
        }

        if (!erros.isEmpty()) {
            throw new ValidacaoException("Erro de validação", erros);
        }
    }

    public Fornecedor inserir(Fornecedor x) throws ServicoException {
        Fornecedor aux = repoCustom.buscarNomeExato(x.getNome());
        if (aux != null) {
            throw new ServicoException("Já existe um fornecedor com esse nome!", 1);
        }
        
         if(aux.getDataCadastro!=null && !aux.getDataCadastro.isEmpty()){
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	try {
				aux.setDataCadastro(sdf.parse(aux.getDataCadastro));
			} catch (ParseException e) {
				System.out.println("Instanciacao: data de cadastro invalido");
			}
        } else{
        	aux.setDataCadastro(Calendar.getInstance().getTime());
        }

        
        
        validar(x);
        return repo.save(x);
    }

    public Fornecedor atualizar(Fornecedor x) throws ServicoException {
        Fornecedor aux = repo.findOne(x.getCodFornecedor());
        if (aux == null) {
            throw new NaoEncontradoException("Fornecedor não encontrado!", 1);
        }
        aux = repoCustom.buscarNomeExato(x.getNome());
        if (aux != null && aux.getCodFornecedor()!=x.getCodFornecedor()) {
            throw new ServicoException("Já existe um outro fornecedor com esse nome!", 1);
        }
        validar(x);
        return repo.save(x);
    }

    public void excluir(int cod) throws ServicoException {
        Fornecedor forn = repo.findOne(cod);
        if (forn == null) {
            throw new NaoEncontradoException("Fornecedor não encontrado!", 1);
        }
        if (!forn.getProdutos().isEmpty()) {
            throw new ServicoException("Exclusão não permitida: este fornecedor possui produtos!", 2);
        }
        repo.delete(forn);
    }

    public Fornecedor buscar(int cod) {
        Fornecedor forn = repo.findOne(cod);
        if (forn == null) {
            throw new NaoEncontradoException("Forncedor não encontrado!", 1);
        }
        return forn;
    }

    public List<Fornecedor> buscarTodosOrdenadosPorNome() {
        return repoCustom.buscarTodosOrdenadosPorNome();
    }

    public List<Fornecedor> buscarPorNome(String trecho) {
        return repoCustom.buscarPorNome(trecho);
    }
}
