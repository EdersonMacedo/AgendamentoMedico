/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.model.persistencia.dao;

import br.com.model.paciente.Consulta;
import java.util.List;

/**
 *
 * @author Ederson
 */
public interface ConsultaDAO {
    int salvar(Consulta f);
    boolean remove(int codigo);
    List<Consulta> listAll();
    Consulta listById(int codigo);  
    List<Consulta> listByNome (String nome);    
}
