package br.com.model.funcionario;

import java.util.Date;

public class HorarioDeAgendamento {
    private Date entradaManha;
    private Date saidaManhã;
    
    private Date entradaTarde;
    private Date saidaTarde;

    public Date getEntradaManha() {
        return entradaManha;
    }

    public void setEntradaManha(Date entradaManha) {
        this.entradaManha = entradaManha;
    }

    public Date getSaidaManhã() {
        return saidaManhã;
    }

    public void setSaidaManhã(Date saidaManhã) {
        this.saidaManhã = saidaManhã;
    }

    public Date getEntradaTarde() {
        return entradaTarde;
    }

    public void setEntradaTarde(Date entradaTarde) {
        this.entradaTarde = entradaTarde;
    }

    public Date getSaidaTarde() {
        return saidaTarde;
    }

    public void setSaidaTarde(Date saidaTarde) {
        this.saidaTarde = saidaTarde;
    }
    
}