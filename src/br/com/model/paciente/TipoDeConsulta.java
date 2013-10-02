package br.com.model.paciente;

public class TipoDeConsulta {
    private boolean consulta;
    private boolean retorno;
    private boolean encaixe;
    private boolean outro;

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public boolean isEncaixe() {
        return encaixe;
    }

    public void setEncaixe(boolean encaixe) {
        this.encaixe = encaixe;
    }

    public boolean isOutro() {
        return outro;
    }

    public void setOutro(boolean outro) {
        this.outro = outro;
    }
}