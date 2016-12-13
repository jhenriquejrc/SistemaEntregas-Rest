package com.joaohenrique.entregas.servico.excecoes;

/**
 * Created by jhenr on 12/12/2016.
 */
public class ServicoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer codigo;

    public ServicoException(String msg, Integer codigo) {
        super(msg);
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
