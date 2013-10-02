package br.com.model.funcionario;

public class Remedio {

    private String nomeRemedio;
    private String tipoRemedio;
    private String bula;
    private String restricao;
    private String laboratorio;
    private int codigo;

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }

    public String getTipoRemedio() {
        return tipoRemedio;
    }

    public void setTipoRemedio(String tipoRemedio) {
        this.tipoRemedio = tipoRemedio;
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula;
    }

    public String getRestricao() {
        return restricao;
    }

    public void setRestricao(String restricao) {
        this.restricao = restricao;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
