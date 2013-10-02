package br.com.model.paciente;

import java.util.Date;

public class MarcarConsulta extends Paciente {

    private Date dataDaConsulta;

    public Date getDataDaConsulta() {
        return dataDaConsulta;
    }
    public void setDataDaConsulta(Date dataDaConsulta) {
        this.dataDaConsulta = dataDaConsulta;
    }
    
}