package br.com.model.funcionario;

public class Secretaria extends Pessoa {

    private String login;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}