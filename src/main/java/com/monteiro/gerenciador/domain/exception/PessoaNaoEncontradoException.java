package com.monteiro.gerenciador.domain.exception;

public class PessoaNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PessoaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PessoaNaoEncontradoException(Long pessoaId) {
        this(String.format("Não existe um cadastro de pessoa com código %d", pessoaId));
    }

}
