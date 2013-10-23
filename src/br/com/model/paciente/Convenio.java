package br.com.model.paciente;

public class Convenio {
    private String TipoDeConvenio;
    private int codigo;
    public Convenio() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getTipoDeConvenio() {
        return TipoDeConvenio;
    }

    public void setTipoDeConvenio(String TipoDeConvenio) {
        this.TipoDeConvenio = TipoDeConvenio;
    }
    
}
