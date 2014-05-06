package br.com.model.controller;


import br.com.model.paciente.Convenio;
import br.com.model.persistencia.ConvenioDAOImplements;
import br.com.model.persistencia.dao.ConvenioDAO;
import java.util.List;

public class ConvenioController {
    
    
    public int salvar(Convenio f){
        ConvenioDAO dao = new ConvenioDAOImplements();
        return dao.salvar(f);
    }
    
    public Convenio listById(int codigo){
        ConvenioDAO dao = new ConvenioDAOImplements();
        return dao.listById(codigo);
    }
    
    public List<Convenio> listarTodos(){
        ConvenioDAO dao = new ConvenioDAOImplements();
        return dao.listAll();
    }
    public List<Convenio> listByNome(String nome){
        ConvenioDAO dao = new ConvenioDAOImplements();
        return dao.listByNome(nome);
    }
    public boolean remove (int id){
        ConvenioDAO dao = new ConvenioDAOImplements();
        return dao.remove(id);
    }
}
