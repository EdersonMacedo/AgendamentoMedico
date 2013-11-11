package br.com.model.paciente;
import br.com.model.funcionario.Pessoa;

public class Paciente extends Pessoa {
    private Convenio convenio;

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
    
}