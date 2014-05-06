package br.com.model.persistencia.dao;

import br.com.model.paciente.Paciente;
import java.util.List;

/**
 *
 * @author Ederson
 */
public interface PacienteDAO {
    int salvar(Paciente p);
    boolean remove(int codigo);
    Paciente listById(int codigo); 
    List<Paciente> listAll(); 
    List<Paciente> listConsulta();
    List<Paciente> listByNome(String nome);
}
