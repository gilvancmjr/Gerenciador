package com.monteiro.gerenciador.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EnderecoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EnderecoNaoEncontradoException(Long pessoaId) {
        this(String.format("Não existe um cadastro de pessoa com código %d", pessoaId));
    }

}
