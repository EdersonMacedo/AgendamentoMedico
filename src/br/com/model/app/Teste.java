/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.app;

import br.com.model.controller.ConsultaController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author guest01
 */
public class Teste {
    public static void main(String[] args) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = (Date)formatter.parse("01/01/2001"); 
        ConsultaController cc = new ConsultaController();
        cc.gerarConsultaTudo(date);
    }
}
