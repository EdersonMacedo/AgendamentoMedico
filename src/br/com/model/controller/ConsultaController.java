package br.com.model.controller;

import br.com.model.paciente.Consulta;
import br.com.model.persistencia.ConsultaDAOImplements;
import br.com.model.persistencia.dao.ConsultaDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ederson
 */
public class ConsultaController {
    public int salvar(Consulta p){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.salvar(p);
    }
    
    public Consulta listById(int codigo){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listById(codigo);
    }
    public List<Consulta> listarTodos(){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listAll();
    }
    public List<Consulta> listByNome(String nome){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listByNome(nome);
    }
    public boolean remove (int id){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.remove(id);
    }
    public void gerarConsultaTudo(Date data){
        ConsultaDAO dao = new ConsultaDAOImplements();
        dao.gerarConsultaTudo(data);
    }
}
