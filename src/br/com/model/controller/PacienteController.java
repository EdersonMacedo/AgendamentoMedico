package br.com.model.controller;


import br.com.model.paciente.Paciente;
import br.com.model.persistencia.PacienteDAOImplements;
import br.com.model.persistencia.dao.PacienteDAO;
import java.util.List;

/**
 *
 * @author Ederson
 */
public class PacienteController {
    public int salvar(Paciente p){
        PacienteDAO dao = new PacienteDAOImplements();
        return dao.salvar(p);
    }
    
    public Paciente listById(int codigo){
        PacienteDAO dao = new PacienteDAOImplements();
        return dao.listById(codigo);
    }
    public List<Paciente> listarTodos(){
        PacienteDAO dao = new PacienteDAOImplements();
        return dao.listAll();
    }
    public List<Paciente> listByNome(String nome){
        PacienteDAO dao = new PacienteDAOImplements();
        return dao.listByNome(nome);
    }
    public boolean remove (int id){
        PacienteDAO dao = new PacienteDAOImplements();
        return dao.remove(id);
    }    
}
