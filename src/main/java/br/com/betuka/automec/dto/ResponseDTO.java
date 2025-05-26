package br.com.betuka.automec.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private int status;
    private String mensagem;
    private T dados;

    public ResponseDTO(int status, String mensagem, T dados) {
        this.status = status;
        this.mensagem = mensagem;
        this.dados = dados;
    }

    public ResponseDTO(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public T getDados() {
        return dados;
    }
}
