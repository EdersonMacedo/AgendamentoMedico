package br.com.teste.consulta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.model.controller.ConsultaController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Ederson
 */
public class TesteConsulta {

    public static void main(String[] args) throws ParseException {
        ConsultaController cc = new ConsultaController();
        Date dataFormat;
        String data = "10/11/2013";

        dataFormat = (new SimpleDateFormat("dd/MM/yyyy").parse(data));
        cc.gerarConsultaTudo(dataFormat);
        JOptionPane.showMessageDialog(null, "consultar o Banco");
    }
}
