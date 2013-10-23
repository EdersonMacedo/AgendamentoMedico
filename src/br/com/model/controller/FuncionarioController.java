package br.com.model.controller;

import br.com.model.funcionario.Funcionario;
import br.com.model.persistencia.FuncionarioDAOImplements;
import br.com.model.persistencia.dao.FuncionarioDAO;
import java.util.List;

public class FuncionarioController {
    public int salvar(Funcionario f){
        FuncionarioDAO dao = new FuncionarioDAOImplements();
        return dao.salvar(f);
    }
    
    public List<Funcionario> listarTodos(){
        FuncionarioDAO dao = new FuncionarioDAOImplements();
        return dao.listAll();
    }
    public List<Funcionario> listByNome(String nome){
        FuncionarioDAO dao = new FuncionarioDAOImplements();
        return dao.listByNome(nome);
    }
    public boolean remove (int id){
        FuncionarioDAO dao = new FuncionarioDAOImplements();
        return dao.remove(id);
        
    }
}
