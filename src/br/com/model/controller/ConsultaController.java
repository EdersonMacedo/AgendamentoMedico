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
    
    public Consulta listByIdInsert(int codigo){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listByIdInsert(codigo);
    }

    public Consulta listByIdUpdate(int codigo){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listByIdUpdate(codigo);
    }
    
    public List<Consulta> listarTodos(){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listAll();
    }
    public List<Consulta> listByNome(String nome){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listByNome(nome);
    }
    public int remove (Consulta c){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.remove(c);
    }
    public void gerarConsultaTudo(Date data){
        ConsultaDAO dao = new ConsultaDAOImplements();
        dao.gerarConsultaTudo(data);
    }
    public List<Consulta> listPorDate(Date data){
        ConsultaDAO dao = new ConsultaDAOImplements();
        return dao.listPorDate(data);
    }
}
