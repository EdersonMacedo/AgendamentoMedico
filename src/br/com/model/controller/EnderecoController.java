package br.com.model.controller;

import br.com.model.funcionario.Endereco;
import br.com.model.persistencia.EnderecoDAOImplements;
import br.com.model.persistencia.dao.EnderecoDAO;

/**
 *
 * @author Ederson
 */
public class EnderecoController {
    public int salvar(Endereco e){
        EnderecoDAO dao = new EnderecoDAOImplements();
        return dao.salvar(e);
    }
}
